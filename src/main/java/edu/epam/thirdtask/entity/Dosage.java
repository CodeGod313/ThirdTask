package edu.epam.thirdtask.entity;

public class Dosage {
    private String period;
    private String dose;

    public Dosage() {
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public Dosage(String period, String dose) {
        this.period = period;
        this.dose = dose;
    }

    public String getPeriod() {
        return period;
    }

    public String getDose() {
        return dose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dosage dosage = (Dosage) o;

        if (period != null ? !period.equals(dosage.period) : dosage.period != null) return false;
        return dose != null ? dose.equals(dosage.dose) : dosage.dose == null;
    }

    @Override
    public int hashCode() {
        int result = period != null ? period.hashCode() : 0;
        result = 31 * result + (dose != null ? dose.hashCode() : 0);
        return result;
    }
}
