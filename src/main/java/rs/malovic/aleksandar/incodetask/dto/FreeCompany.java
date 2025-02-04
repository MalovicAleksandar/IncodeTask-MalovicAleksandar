package rs.malovic.aleksandar.incodetask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FreeCompany {

    private String cin;

    private String name;

    @JsonProperty("registration_date")
    private String registrationDate;

    private String address;

    @JsonProperty("is_active")
    private boolean isActive;

    public FreeCompany() {}

    public FreeCompany(String cin, String name, String registrationDate, String address, boolean isActive) {
        this.cin = cin;
        this.name = name;
        this.registrationDate = registrationDate;
        this.address = address;
        this.isActive = isActive;
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
}
