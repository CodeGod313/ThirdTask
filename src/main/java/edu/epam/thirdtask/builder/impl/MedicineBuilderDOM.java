package edu.epam.thirdtask.builder.impl;

import edu.epam.thirdtask.builder.MedicineBuilder;
import edu.epam.thirdtask.entity.*;
import edu.epam.thirdtask.exception.XMLProcessException;
import edu.epam.thirdtask.util.MedicineXMLTagUtil;
import edu.epam.thirdtask.validator.MedicineValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static edu.epam.thirdtask.util.MedicineXMLTagUtil.*;

public class MedicineBuilderDOM implements MedicineBuilder {
    static Logger logger = LogManager.getLogger(MedicineBuilderDOM.class);
    private List<Medicine> medicines;
    private Medicine currentMedicine;

    public MedicineBuilderDOM() {
        medicines = new ArrayList<>();
    }

    @Override
    public List<Medicine> buildAllMedicines(String xmlFilePath) throws XMLProcessException {
        File file = new File(xmlFilePath);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();
            NodeList nodeHumanMedicineList = document.getElementsByTagName(HUMAN_MEDICINE_TAG);
            NodeList nodeVeterinaryMedicineList = document.getElementsByTagName(VETERINARY_MEDICINE_TAG);
            parseNodeListToMedicines(nodeHumanMedicineList);
            parseNodeListToMedicines(nodeVeterinaryMedicineList);
        } catch (ParserConfigurationException | SAXException e) {
            logger.error("Parser internal error", e);
            throw new XMLProcessException("Parser internal error", e);
        } catch (IOException e) {
            logger.error("Error with xml file: " + xmlFilePath, e);
            throw new XMLProcessException("Error with xml file: " + xmlFilePath, e);
        }
        return medicines;
    }

    private void parseNodeListToMedicines(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            String nodeName = node.getNodeName();
            switch (nodeName) {
                case HUMAN_MEDICINE_TAG -> {
                    currentMedicine = new HumanMedicine();
                    String ageString = element.getElementsByTagName(AGE_TAG).item(0).getTextContent();
                    MedicineXMLTagUtil medicineXMLTagUtil = new MedicineXMLTagUtil();
                    String ageEnumString = medicineXMLTagUtil.fromTagToEnumString(ageString);
                    HumanMedicine humanMedicine = (HumanMedicine) currentMedicine;
                    humanMedicine.setHumanAge(Enum.valueOf(HumanAge.class, ageEnumString));
                    currentMedicine = humanMedicine;
                }
                case VETERINARY_MEDICINE_TAG -> {
                    currentMedicine = new VeterinaryMedicine();
                    String typeOfAnimalString = element.getElementsByTagName(TYPE_OF_ANIMAL_TAG).item(0).getTextContent();
                    MedicineXMLTagUtil medicineXMLTagUtil = new MedicineXMLTagUtil();
                    String typeOfAnimalEnumString = medicineXMLTagUtil.fromTagToEnumString(typeOfAnimalString);
                    VeterinaryMedicine veterinaryMedicine = (VeterinaryMedicine) currentMedicine;
                    veterinaryMedicine.setTypeOfAnimal(Enum.valueOf(TypeOfAnimal.class, typeOfAnimalEnumString));
                }
            }
            parseAttributes(node);
            String name = parseTextContentFromTagName(element, NAME_TAG);
            currentMedicine.setName(name);
            String pharm = parseTextContentFromTagName(element, PHARM_TAG);
            currentMedicine.setName(pharm);
            String groupString = parseTextContentFromTagName(element, GROUP_TAG);
            MedicineXMLTagUtil medicineXMLTagUtil = new MedicineXMLTagUtil();
            String groupEnumString = medicineXMLTagUtil.fromTagToEnumString(groupString);
            currentMedicine.setGroup(Enum.valueOf(Group.class, groupEnumString));
            NodeList analogs = element.getElementsByTagName(ANALOG_TAG);
            for (int j = 0; j < analogs.getLength(); j++) {
                String analogName = analogs.item(j).getTextContent();
                currentMedicine.getAnalogs().add(analogName);
            }
            String versionString = parseTextContentFromTagName(element, VERSIONS_TAG);
            String versionEnumString = medicineXMLTagUtil.fromTagToEnumString(versionString);
            currentMedicine.setVersion(Enum.valueOf(Versions.class, versionEnumString));
            Certificate certificate = currentMedicine.getCertificate();
            String numberString = parseTextContentFromTagName(element, NUMBER_TAG);
            Integer numberInt = Integer.parseInt(numberString);
            certificate.setNumber(numberInt);
            String dateOfIssueString = parseTextContentFromTagName(element, DATE_OF_ISSUE_TAG);
            YearMonth dateOfIssue = YearMonth.parse(dateOfIssueString);
            certificate.setDateOfIssue(dateOfIssue);
            String expirationDateString = parseTextContentFromTagName(element, EXPIRATION_DATE_TAG);
            YearMonth expirationDate = YearMonth.parse(expirationDateString);
            certificate.setExpirationDate(expirationDate);
            String registeringOrganisation = parseTextContentFromTagName(element, REGISTERING_ORGANISATION_TAG);
            certificate.setRegisteringOrganisation(registeringOrganisation);
            MedicinePackage medicinePackage = currentMedicine.getMedicinePackage();
            String packageTypeString = parseTextContentFromTagName(element, TYPE_TAG);
            String packageTypeEnumString = medicineXMLTagUtil.fromTagToEnumString(packageTypeString);
            medicinePackage.setPackageType(Enum.valueOf(PackageType.class, packageTypeEnumString));
            String dosagePeriod = parseTextContentFromTagName(element, DOSE_TAG);
            Dosage dosage = currentMedicine.getDosage();
            dosage.setPeriod(dosagePeriod);
            String dose = parseTextContentFromTagName(element, DOSE_TAG);
            dosage.setDose(dose);
            medicines.add(currentMedicine);
        }
    }

    private String parseTextContentFromTagName(Element element, String tag) {
        return element
                .getElementsByTagName(tag)
                .item(0)
                .getTextContent();
    }

    private void parseAttributes(Node node) {
        NamedNodeMap attributes = node.getAttributes();
        String id = attributes.getNamedItem(ID_TAG).getTextContent();
        currentMedicine.setId(id);
        if (attributes.getLength() == 2) {
            String website = attributes.getNamedItem(WEBSITE_TAG).getTextContent();
            currentMedicine.setWebsite(website);
        }
    }
}
