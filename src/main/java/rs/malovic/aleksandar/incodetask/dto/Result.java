package rs.malovic.aleksandar.incodetask.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CompanyResult.class),
        @JsonSubTypes.Type(value = InfoResult.class)
})
public class Result {

}
