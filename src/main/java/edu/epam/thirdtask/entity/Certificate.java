package edu.epam.thirdtask.entity;

import java.time.YearMonth;
import java.util.Date;

public class Certificate {
    private int number;
    private YearMonth dateOfIssue;
    private YearMonth expirationDate;
    private String registeringOrganisation;

    public Certificate() {
    }

    public Certificate(int number, YearMonth dateOfIssue, YearMonth expirationDate, String registeringOrganisation) {
        this.number = number;
        this.dateOfIssue = dateOfIssue;
        this.expirationDate = expirationDate;
        this.registeringOrganisation = registeringOrganisation;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDateOfIssue(YearMonth dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public void setExpirationDate(YearMonth expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setRegisteringOrganisation(String registeringOrganisation) {
        this.registeringOrganisation = registeringOrganisation;
    }

    public int getNumber() {
        return number;
    }

    public YearMonth getDateOfIssue() {
        return dateOfIssue;
    }

    public YearMonth getExpirationDate() {
        return expirationDate;
    }

    public String getRegisteringOrganisation() {
        return registeringOrganisation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Certificate that = (Certificate) o;

        if (number != that.number) return false;
        if (dateOfIssue != null ? !dateOfIssue.equals(that.dateOfIssue) : that.dateOfIssue != null) return false;
        if (expirationDate != null ? !expirationDate.equals(that.expirationDate) : that.expirationDate != null)
            return false;
        return registeringOrganisation != null ? registeringOrganisation.equals(that.registeringOrganisation) : that.registeringOrganisation == null;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (dateOfIssue != null ? dateOfIssue.hashCode() : 0);
        result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
        result = 31 * result + (registeringOrganisation != null ? registeringOrganisation.hashCode() : 0);
        return result;
    }
}
