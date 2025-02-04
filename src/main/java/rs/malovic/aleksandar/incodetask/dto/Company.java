package rs.malovic.aleksandar.incodetask.dto;

public class Company {

    private String cin;

    private String name;

    private String registrationDate;

    private String address;

    private boolean isActive;

    public Company() {}

    public Company(String cin, String name, String registrationDate, String address, boolean isActive) {
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

    public static Company fromFreeCompany(FreeCompany fc) {
        return new Company(fc.getCin(), fc.getName(), fc.getRegistrationDate(), fc.getAddress(), fc.isActive());
    }

    public static Company fromPremiumCompany(PremiumCompany pc) {
        return new Company(pc.getCompanyIdentificationNumber(), pc.getCompanyName(), pc.getRegistrationDate(), pc.getCompanyFullAddress(), pc.getIsActive());
    }
}
