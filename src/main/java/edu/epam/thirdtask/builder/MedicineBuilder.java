package edu.epam.thirdtask.builder;

import edu.epam.thirdtask.entity.Medicine;
import edu.epam.thirdtask.exception.XMLProcessException;

import java.util.List;

public interface MedicineBuilder {
    List<Medicine> buildAllMedicines(String xmlFilePath) throws XMLProcessException;
}
