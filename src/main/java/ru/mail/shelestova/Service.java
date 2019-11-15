package ru.mail.shelestova;

public class Service extends Cost {
    private String serviceName;
    private String companyOffice;


    public Service (String companyOffice, String serviceName, int cost) {
        super (cost);
        this.serviceName = serviceName;
        this.companyOffice = companyOffice;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCompanyOffice() {
        return companyOffice;
    }

    public void setCompanyOffice(String companyOffice) {
        this.companyOffice = companyOffice;
    }


    @Override
    public String toString() {
        return "Service{" +
                "serviceName='" + serviceName + '\'' +
                ", companyOffice='" + companyOffice + '\'' +
                ", cost=" + cost +
                '}';
    }
}
