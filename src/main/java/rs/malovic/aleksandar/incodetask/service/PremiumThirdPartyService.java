package rs.malovic.aleksandar.incodetask.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.micrometer.common.util.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import rs.malovic.aleksandar.incodetask.dto.PremiumCompany;

import java.util.List;
import java.util.Random;

@RestController
public class PremiumThirdPartyService {

    private Resource companiesResource = new ClassPathResource("data/premium_service_companies-1.json");

    private final List<PremiumCompany> companies;

    private final Random random = new Random();

    public PremiumThirdPartyService() {
        companies = CompanyLoader.loadCompanies(companiesResource, new PropertyNamingStrategies.LowerCamelCaseStrategy(), new TypeReference<>() {});
    }

    @GetMapping("/premium-third-party")
    @ResponseBody
    public List<PremiumCompany> premiumThirdParty(@RequestParam("query") String query){
        if (random.nextInt(100) < 10) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
        }

        if (StringUtils.isNotBlank(query)) {
            return companies.stream().filter(company -> company.getCompanyIdentificationNumber().contains(query)).toList();
        } else {
            return companies;
        }
    }
}
