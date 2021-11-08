package edu.epam.thirdtask.builder.impl;

import edu.epam.thirdtask.builder.MedicineBuilder;
import edu.epam.thirdtask.entity.*;
import edu.epam.thirdtask.exception.XMLProcessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.YearMonth;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MedicineBuilderSAXTest {

    MedicineBuilder medicineBuilder;
    HumanMedicine expected;

    @BeforeAll
    public void setUp() throws XMLProcessException {
        medicineBuilder = new MedicineBuilderSAX();
        expected = new HumanMedicine();
        expected.setName("Pilecium");
        expected.setGroup(Group.ANTIBIOTIC);
        expected.getAnalogs().add("Signecium");
        expected.getAnalogs().add("Filecium");
        expected.getAnalogs().add("Stroncium");
        expected.setVersion(Versions.PILLS);
        expected.getCertificate().setNumber(123);
        expected.getCertificate().setDateOfIssue(YearMonth.of(2020, 9));
        expected.getCertificate().setExpirationDate(YearMonth.of(2021, 10));
        expected.getMedicinePackage().setPackageType(PackageType.PILL_PLATE);
        expected.getMedicinePackage().setCount(22);
        expected.getMedicinePackage().setPrice(187);
        expected.getDosage().setPeriod("16");
        expected.getDosage().setDose("3 pills");
        expected.setHumanAge(HumanAge.ADULT);
    }

    @Test
    void buildAllMedicines() throws XMLProcessException {
        List<Medicine> actual = medicineBuilder.buildAllMedicines("xml/medicinesTest.xml");
        Assertions.assertEquals(expected, actual.get(0));
    }
}