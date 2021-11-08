package edu.epam.thirdtask.builder.impl;

import edu.epam.thirdtask.builder.MedicineBuilder;
import edu.epam.thirdtask.entity.*;
import edu.epam.thirdtask.exception.XMLProcessException;
import edu.epam.thirdtask.util.MedicineXMLTagUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static edu.epam.thirdtask.util.MedicineXMLTagUtil.*;


public class MedicineBuilderStAx implements MedicineBuilder {
    static Logger logger = LogManager.getLogger(MedicineBuilderStAx.class);
    private List<Medicine> medicines;
    private Medicine currentMedicine;

    public MedicineBuilderStAx() {
        medicines = new ArrayList<>();
    }

    private void parseXMLFile(String filePath) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(filePath);

            XMLEventReader reader;
            reader = xmlInputFactory.createXMLEventReader(fileInputStream);
            while (reader.hasNext()) {
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    String elementName = startElement.getName().getLocalPart();
                    switch (elementName) {
                        case HUMAN_MEDICINE_TAG -> {
                            currentMedicine = new HumanMedicine();
                            parseAttributes(startElement);
                        }
                        case VETERINARY_MEDICINE_TAG -> {
                            currentMedicine = new VeterinaryMedicine();
                            parseAttributes(startElement);
                        }
                        case NAME_TAG -> {
                            xmlEvent = reader.nextEvent();
                            String name = xmlEvent.asCharacters().getData();
                            currentMedicine.setName(name);
                        }
                        case PHARM_TAG -> {
                            xmlEvent = reader.nextEvent();
                            String pharmName = xmlEvent.asCharacters().getData();
                            currentMedicine.setPharm(pharmName);
                        }
                        case GROUP_TAG -> {
                            xmlEvent = reader.nextEvent();
                            String groupString = xmlEvent.asCharacters().getData();
                            MedicineXMLTagUtil medicineXMLTagUtil = new MedicineXMLTagUtil();
                            String groupEnumString = medicineXMLTagUtil.fromTagToEnumString(groupString);
                            currentMedicine.setGroup(Enum.valueOf(Group.class, groupEnumString));
                        }
                        case ANALOG_TAG -> {
                            xmlEvent = reader.nextEvent();
                            String analog = xmlEvent.asCharacters().getData();
                            List<String> analogs = currentMedicine.getAnalogs();
                            analogs.add(analog);
                        }
                        case VERSIONS_TAG -> {
                            xmlEvent = reader.nextEvent();
                            String versionString = xmlEvent.asCharacters().getData();
                            MedicineXMLTagUtil medicineXMLTagUtil = new MedicineXMLTagUtil();
                            String groupEnumString = medicineXMLTagUtil.fromTagToEnumString(versionString);
                            currentMedicine.setVersion(Enum.valueOf(Versions.class, groupEnumString));
                        }
                        case NUMBER_TAG -> {
                            xmlEvent = reader.nextEvent();
                            Certificate certificate = currentMedicine.getCertificate();
                            String numberString = xmlEvent.asCharacters().getData();
                            Integer numberInt = Integer.parseInt(numberString);
                            certificate.setNumber(numberInt);
                        }
                        case DATE_OF_ISSUE_TAG -> {
                            xmlEvent = reader.nextEvent();
                            Certificate certificate = currentMedicine.getCertificate();
                            String dateOfIssueString = xmlEvent.asCharacters().getData();
                            YearMonth dateOfIssue = YearMonth.parse(dateOfIssueString);
                            certificate.setDateOfIssue(dateOfIssue);
                        }
                        case EXPIRATION_DATE_TAG -> {
                            xmlEvent = reader.nextEvent();
                            Certificate certificate = currentMedicine.getCertificate();
                            String expirationDateString = xmlEvent.asCharacters().getData();
                            YearMonth expirationDate = YearMonth.parse(expirationDateString);
                            certificate.setExpirationDate(expirationDate);
                        }
                        case REGISTERING_ORGANISATION_TAG -> {
                            xmlEvent = reader.nextEvent();
                            Certificate certificate = currentMedicine.getCertificate();
                            String registeringOrganisationName = xmlEvent.asCharacters().getData();
                            certificate.setRegisteringOrganisation(registeringOrganisationName);
                        }
                        case TYPE_TAG -> {
                            xmlEvent = reader.nextEvent();
                            MedicinePackage medicinePackage = currentMedicine.getMedicinePackage();
                            String packageTypeString = xmlEvent.asCharacters().getData();
                            MedicineXMLTagUtil medicineXMLTagUtil = new MedicineXMLTagUtil();
                            String PackageTypeEnumTag = medicineXMLTagUtil.fromTagToEnumString(packageTypeString);
                            medicinePackage.setPackageType(Enum.valueOf(PackageType.class, PackageTypeEnumTag));
                        }
                        case COUNT_TAG -> {
                            xmlEvent = reader.nextEvent();
                            MedicinePackage medicinePackage = currentMedicine.getMedicinePackage();
                            String countString = xmlEvent.asCharacters().getData();
                            Integer countInt = Integer.parseInt(countString);
                            medicinePackage.setCount(countInt);
                        }
                        case PRICE_TAG -> {
                            xmlEvent = reader.nextEvent();
                            MedicinePackage medicinePackage = currentMedicine.getMedicinePackage();
                            String priceString = xmlEvent.asCharacters().getData();
                            Integer priceInt = Integer.parseInt(priceString);
                            medicinePackage.setCount(priceInt);
                        }
                        case AGE_TAG -> {
                            xmlEvent = reader.nextEvent();
                            String ageString = xmlEvent.asCharacters().getData();
                            MedicineXMLTagUtil medicineXMLTagUtil = new MedicineXMLTagUtil();
                            String ageEnumString = medicineXMLTagUtil.fromTagToEnumString(ageString);
                            HumanMedicine humanMedicine = (HumanMedicine) currentMedicine;
                            humanMedicine.setHumanAge(Enum.valueOf(HumanAge.class, ageEnumString));
                        }
                        case TYPE_OF_ANIMAL_TAG -> {
                            xmlEvent = reader.nextEvent();
                            String typeOfAnimalString = xmlEvent.asCharacters().getData();
                            MedicineXMLTagUtil medicineXMLTagUtil = new MedicineXMLTagUtil();
                            String typeOfAnimalEnumString = medicineXMLTagUtil.fromTagToEnumString(typeOfAnimalString);
                            VeterinaryMedicine veterinaryMedicine = (VeterinaryMedicine) currentMedicine;
                            veterinaryMedicine.setTypeOfAnimal(Enum.valueOf(TypeOfAnimal.class, typeOfAnimalEnumString));
                            currentMedicine = veterinaryMedicine;
                        }
                        case PERIOD_TAG -> {
                            xmlEvent = reader.nextEvent();
                            String period = xmlEvent.asCharacters().getData();
                            Dosage dosage = currentMedicine.getDosage();
                            dosage.setPeriod(period);
                        }
                        case DOSE_TAG -> {
                            xmlEvent = reader.nextEvent();
                            String dose = xmlEvent.asCharacters().getData();
                            Dosage dosage = currentMedicine.getDosage();
                            dosage.setPeriod(dose);
                        }
                    }
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    String nameOfEndElement = endElement.getName().getLocalPart();
                    if (nameOfEndElement.equals(HUMAN_MEDICINE_TAG) || nameOfEndElement.equals(VETERINARY_MEDICINE_TAG)) {
                        medicines.add(currentMedicine);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("File not found: " + filePath, e);
        } catch (XMLStreamException e) {
            logger.error("Error while parsing file", e);
        }
    }


    private void parseAttributes(StartElement startElement) {
        Iterator<Attribute> attributeIterator = startElement.getAttributes();
        while (attributeIterator.hasNext()) {
            Attribute tagAttribute = attributeIterator.next();
            String attributeName = tagAttribute.getName().toString();
            switch (attributeName) {
                case ID_TAG -> {
                    currentMedicine.setId(tagAttribute.getValue());
                }
                case WEBSITE_TAG -> {
                    currentMedicine.setWebsite(tagAttribute.getValue());
                }
            }
        }
    }

    @Override
    public List<Medicine> buildAllMedicines(String xmlFilePath) throws XMLProcessException {
        parseXMLFile(xmlFilePath);
        return medicines;
    }
}
