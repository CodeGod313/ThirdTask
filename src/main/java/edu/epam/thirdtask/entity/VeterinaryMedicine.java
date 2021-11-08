package edu.epam.thirdtask.entity;

import java.util.List;

public class VeterinaryMedicine extends Medicine {
    private TypeOfAnimal typeOfAnimal;

    public VeterinaryMedicine() {
        super();
    }

    public VeterinaryMedicine(String name, String pharm, Group group, List<String> analogs, Certificate certificate, MedicinePackage medicinePackage, Dosage dosage, TypeOfAnimal typeOfAnimal) {
        super(name, pharm, group, analogs, certificate, medicinePackage, dosage);
        this.typeOfAnimal = typeOfAnimal;
    }

    public TypeOfAnimal getTypeOfAnimal() {
        return typeOfAnimal;
    }

    public void setTypeOfAnimal(TypeOfAnimal typeOfAnimal) {
        this.typeOfAnimal = typeOfAnimal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        VeterinaryMedicine that = (VeterinaryMedicine) o;

        return typeOfAnimal == that.typeOfAnimal;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (typeOfAnimal != null ? typeOfAnimal.hashCode() : 0);
        return result;
    }
}
