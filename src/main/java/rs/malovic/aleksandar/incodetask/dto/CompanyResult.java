package rs.malovic.aleksandar.incodetask.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class CompanyResult extends Result {
    private String cin;

    private String name;

    private String registrationDate;

    private String address;

    private boolean isActive;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Company[] otherResults;

    public CompanyResult() {}

    public CompanyResult(List<Company> companies) {
        Company firstResult = companies.removeFirst();
        cin = firstResult.getCin();
        name = firstResult.getName();
        registrationDate = firstResult.getRegistrationDate();
        address = firstResult.getAddress();
        isActive = firstResult.isActive();
        otherResults = !companies.isEmpty() ? companies.toArray(new Company[0]) : null;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Company[] getOtherResults() {
        return otherResults;
    }

    public void setOtherResults(Company[] otherResults) {
        this.otherResults = otherResults;
    }
}
