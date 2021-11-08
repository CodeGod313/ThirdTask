package edu.epam.thirdtask.entity;

import java.util.List;

public class HumanMedicine extends Medicine{
    private HumanAge humanAge;


    public HumanMedicine() {
        super();
    }

    public HumanMedicine(String name, String pharm, Group group, List<String> analogs, Certificate certificate, MedicinePackage medicinePackage, Dosage dosage, HumanAge humanAge) {
        super(name, pharm, group, analogs, certificate, medicinePackage, dosage);
        this.humanAge = humanAge;
    }

    public HumanAge getHumanAge() {
        return humanAge;
    }

    public void setHumanAge(HumanAge humanAge) {
        this.humanAge = humanAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        HumanMedicine that = (HumanMedicine) o;

        return humanAge == that.humanAge;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (humanAge != null ? humanAge.hashCode() : 0);
        return result;
    }
}
