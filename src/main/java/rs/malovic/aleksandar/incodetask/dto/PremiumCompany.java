package rs.malovic.aleksandar.incodetask.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class PremiumCompany {
    private String companyIdentificationNumber;

    private String companyName;

    private String registrationDate;

    @JsonAlias({"companyFullAddress", "fullAddress"})
    private String companyFullAddress;

    private boolean isActive;

    public PremiumCompany() {}

    public PremiumCompany(String companyIdentificationNumber, String companyName, String registrationDate, String companyFullAddress, boolean isActive) {
        this.companyIdentificationNumber = companyIdentificationNumber;
        this.companyName = companyName;
        this.registrationDate = registrationDate;
        this.companyFullAddress = companyFullAddress;
        this.isActive = isActive;
    }

    public String getCompanyIdentificationNumber() {
        return companyIdentificationNumber;
    }

    public void setCompanyIdentificationNumber(String companyIdentificationNumber) {
        this.companyIdentificationNumber = companyIdentificationNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getCompanyFullAddress() {
        return companyFullAddress;
    }

    public void setCompanyFullAddress(String companyFullAddress) {
        this.companyFullAddress = companyFullAddress;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }
}
