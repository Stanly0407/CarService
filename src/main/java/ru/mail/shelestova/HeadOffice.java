package ru.mail.shelestova;

public class HeadOffice extends Entity {
    private String companyTitle;
    private String companyAdress;

    public HeadOffice(int id, String companyTitle, String companyAdress){
        super (id);
        this.companyTitle = companyTitle;
        this.companyAdress = companyAdress;
    }

    public String getCompanyTitle() {
        return companyTitle;
    }

    public void setCompanyTitle(String companyTitle) {
        this.companyTitle = companyTitle;
    }

    public String getCompanyAdress() {
        return companyAdress;
    }

    public void setCompanyAdress(String companyAdress) {
        this.companyAdress = companyAdress;
    }

    @Override
    public String toString() {
        return "Адрес компании " + companyTitle + " (id: " + id + ") - " +
                 companyAdress + ".";
    }
}
