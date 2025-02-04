package rs.malovic.aleksandar.incodetask.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CompanyLoader {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> List<T> loadCompanies(Resource resource, PropertyNamingStrategy strategy, TypeReference<List<T>> type) {
        try (InputStream in = resource.getInputStream()) {
            return mapper.setPropertyNamingStrategy(strategy).readValue(in, type);
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException("Unable to read resource " + resource.getFilename());
        }
    }
}
