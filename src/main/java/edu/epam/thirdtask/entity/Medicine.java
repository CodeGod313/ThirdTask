package edu.epam.thirdtask.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Medicine {
    private static final String DEFAULT_WEBSITE = "www.kuku.com";
    private String id;
    private String name;
    private String pharm;
    private Group group;
    private List<String> analogs;
    private Versions version;
    private Certificate certificate;
    private MedicinePackage medicinePackage;
    private Dosage dosage;
    private String website;


    public Medicine() {
        website = DEFAULT_WEBSITE;
        analogs = new ArrayList<>();
        certificate = new Certificate();
        medicinePackage = new MedicinePackage();
        dosage = new Dosage();
    }

    public Medicine(String name, String pharm, Group group, List<String> analogs, Certificate certificate, MedicinePackage medicinePackage, Dosage dosage) {
        this.name = name;
        this.pharm = pharm;
        this.group = group;
        this.analogs = analogs;
        this.certificate = certificate;
        this.medicinePackage = medicinePackage;
        this.dosage = dosage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Versions getVersion() {
        return version;
    }

    public void setVersion(Versions version) {
        this.version = version;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public String getPharm() {
        return pharm;
    }

    public Group getGroup() {
        return group;
    }

    public List<String> getAnalogs() {
        return analogs;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public MedicinePackage getMedicinePackage() {
        return medicinePackage;
    }

    public Dosage getDosage() {
        return dosage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPharm(String pharm) {
        this.pharm = pharm;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setAnalogs(List<String> analogs) {
        this.analogs = analogs;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public void setMedicinePackage(MedicinePackage medicinePackage) {
        this.medicinePackage = medicinePackage;
    }

    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Medicine medicine = (Medicine) o;

        if (name != null ? !name.equals(medicine.name) : medicine.name != null) return false;
        if (pharm != null ? !pharm.equals(medicine.pharm) : medicine.pharm != null) return false;
        if (group != medicine.group) return false;
        if (analogs != null ? !analogs.equals(medicine.analogs) : medicine.analogs != null) return false;
        if (certificate != null ? !certificate.equals(medicine.certificate) : medicine.certificate != null)
            return false;
        if (medicinePackage != null ? !medicinePackage.equals(medicine.medicinePackage) : medicine.medicinePackage != null)
            return false;
        return dosage != null ? dosage.equals(medicine.dosage) : medicine.dosage == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (pharm != null ? pharm.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (analogs != null ? analogs.hashCode() : 0);
        result = 31 * result + (certificate != null ? certificate.hashCode() : 0);
        result = 31 * result + (medicinePackage != null ? medicinePackage.hashCode() : 0);
        result = 31 * result + (dosage != null ? dosage.hashCode() : 0);
        return result;
    }
}
