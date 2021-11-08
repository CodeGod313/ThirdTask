package edu.epam.thirdtask.validator;

import edu.epam.thirdtask.exception.XMLProcessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MedicineValidatorTest {

    MedicineValidator medicineValidator;

    @BeforeAll
    public void setUp() {
        medicineValidator = new MedicineValidator();
    }

    @Test
    void validateXML() throws XMLProcessException {
        boolean actual = medicineValidator.validateXML("xml/medicinesTest.xml");
        Assertions.assertTrue(actual);
    }

    @Test
    void validateXMLNotMatch() throws XMLProcessException {
        boolean actual = medicineValidator.validateXML("xml/medicinesBadFile.xml");
        Assertions.assertFalse(actual);
    }
}