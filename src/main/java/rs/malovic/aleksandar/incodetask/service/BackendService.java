package rs.malovic.aleksandar.incodetask.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import rs.malovic.aleksandar.incodetask.dto.Source;
import rs.malovic.aleksandar.incodetask.db.VerificationResult;
import rs.malovic.aleksandar.incodetask.db.Verification;
import rs.malovic.aleksandar.incodetask.db.VerificationRepository;
import rs.malovic.aleksandar.incodetask.dto.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class BackendService {

    private static class ThirdPartyResponse {
        public List<Company> companies;
        public Source source;

        public ThirdPartyResponse(List<Company> companies, Source source) {
            this.companies = companies;
            this.source = source;
        }
    }

    @Autowired
    private VerificationRepository verificationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private <T> List<T> callThirdParty(String url, String query, ParameterizedTypeReference<List<T>> type) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("query", query);
        ResponseEntity<List<T>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, type, queryParameters);
        return responseEntity.getBody();
    }

    private <T> ThirdPartyResponse getCompanies(
        String url,
        String query,
        ParameterizedTypeReference<List<T>> type,
        Predicate<T> filter,
        Function<T, Company> constructor,
        Source source
    ) {
        try {
            List<T> companies = callThirdParty(url, query, type);
            return new ThirdPartyResponse(
                companies.stream()
                    .filter(filter)
                    .map(constructor::apply)
                    .collect(Collectors.toCollection(ArrayList::new)),
                source);
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == 503) {
                return null;
            } else {
                throw ex;
            }
        }
    }

    @GetMapping("/backend-service")
    @ResponseBody
    public BackendServiceResponse backendServiceEndpoint(
        @RequestParam("verificationId") UUID verificationId,
        @RequestParam("query") String query
    ) {

        try {
            ThirdPartyResponse response = null;
            Source source = null;
            response = getCompanies(
                    "http://localhost:8080/free-third-party?query={query}",
                    query,
                    new ParameterizedTypeReference<>() {
                    },
                    FreeCompany::isActive,
                    Company::fromFreeCompany,
                    Source.FREE);
            if (response == null) {
                response = getCompanies(
                        "http://localhost:8080/premium-third-party?query={query}",
                        query,
                        new ParameterizedTypeReference<>() {
                        },
                        PremiumCompany::isActive,
                        Company::fromPremiumCompany,
                        Source.PREMIUM);
            }
            Result result;
            if (response == null) {
                result = new InfoResult("Third party services are down");
            } else if (response.companies.isEmpty()) {
                result = new InfoResult("No companies match the provided query");
            } else {
                result = new CompanyResult(response.companies);
            }
            Verification vs = new Verification();
            vs.setVerificationId(verificationId);
            vs.setVerification(
                objectMapper.writeValueAsString(
                    new VerificationResult(
                        verificationId,
                        query,
                        LocalDateTime.now(),
                        result,
                        response != null ? response.source : null
                    )
                )
            );
            verificationRepository.save(vs);
            return new BackendServiceResponse(
                verificationId,
                query,
                result
            );
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping("/verification")
    @ResponseBody
    public VerificationResult getVerification(@RequestParam("verificationId") UUID verificationId) {
        Verification verification = verificationRepository.findByVerificationId(verificationId);
        if (verification == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
        try {
            return objectMapper.readValue(verification.getVerification(), VerificationResult.class);
        } catch (JsonProcessingException ex) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }
    }

}
