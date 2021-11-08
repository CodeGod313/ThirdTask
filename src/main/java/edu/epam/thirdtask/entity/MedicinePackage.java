package edu.epam.thirdtask.entity;

public class MedicinePackage {
    private PackageType packageType;
    private Integer count;
    private Integer price;

    public MedicinePackage() {
    }

    public MedicinePackage(PackageType packageType, Integer count, Integer price) {
        this.packageType = packageType;
        this.count = count;
        this.price = price;
    }

    public PackageType getPackageType() {
        return packageType;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPackageType(PackageType packageType) {
        this.packageType = packageType;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicinePackage aMedicinePackage = (MedicinePackage) o;

        if (packageType != aMedicinePackage.packageType) return false;
        if (count != null ? !count.equals(aMedicinePackage.count) : aMedicinePackage.count != null) return false;
        return price != null ? price.equals(aMedicinePackage.price) : aMedicinePackage.price == null;
    }

    @Override
    public int hashCode() {
        int result = packageType != null ? packageType.hashCode() : 0;
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
