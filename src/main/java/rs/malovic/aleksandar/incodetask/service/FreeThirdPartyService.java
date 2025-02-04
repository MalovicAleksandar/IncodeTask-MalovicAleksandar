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
import rs.malovic.aleksandar.incodetask.dto.FreeCompany;

import java.util.List;
import java.util.Random;

@RestController
public class FreeThirdPartyService {

    private Resource companiesResource = new ClassPathResource("data/free_service_companies-1.json");

    private final List<FreeCompany> companies;

    private final Random random = new Random();

    public FreeThirdPartyService() {
        companies = CompanyLoader.loadCompanies(companiesResource, new PropertyNamingStrategies.SnakeCaseStrategy(), new TypeReference<>() {});
    }

    @GetMapping("/free-third-party")
    @ResponseBody
    public List<FreeCompany> freeThirdParty(@RequestParam("query") String query) {
        if (random.nextInt(100) < 40) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
        }

        if (StringUtils.isNotBlank(query)) {
            return companies.stream().filter(company -> company.getCin().contains(query)).toList();
        } else {
            return companies;
        }
    }
}
