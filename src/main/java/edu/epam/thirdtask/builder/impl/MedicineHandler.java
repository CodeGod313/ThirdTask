package edu.epam.thirdtask.builder.impl;

import edu.epam.thirdtask.entity.*;
import edu.epam.thirdtask.util.MedicineXMLTagUtil;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.YearMonth;
import java.util.List;

import static edu.epam.thirdtask.util.MedicineXMLTagUtil.*;

public class MedicineHandler extends DefaultHandler {
    public static final String NEW_LINE = "\n";
    public static final String EMPTY_STRING = "";
    private List<Medicine> medicines;
    private String lastElement;
    private Medicine currentMedicine;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case HUMAN_MEDICINE_TAG -> {
                currentMedicine = new HumanMedicine();
            }
            case VETERINARY_MEDICINE_TAG -> {
                currentMedicine = new VeterinaryMedicine();
            }
        }
        lastElement = qName;
        parseAttributes(attributes);
    }

    private void parseAttributes(Attributes attributes) {
        for (int i = 0; i < attributes.getLength(); i++) {
            switch (attributes.getQName(i)) {
                case ID_TAG:
                    currentMedicine.setId(attributes.getValue(i));
                    break;
                case WEBSITE_TAG:
                    currentMedicine.setWebsite(attributes.getValue(i));
                    break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (lastElement.equals(qName) && (lastElement.equals("veterinary-medicine") || lastElement.equals("human-medicine"))) {
            medicines.add(currentMedicine);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String info = new String(ch, start, length);
        info.replace(NEW_LINE, EMPTY_STRING).trim();
        switch (lastElement) {
            case NAME_TAG -> {
                currentMedicine.setName(info);
            }
            case PHARM_TAG -> {
                currentMedicine.setPharm(info);
            }
            case ANALOG_TAG -> {
                currentMedicine.getAnalogs()
                        .add(info);
            }
            case GROUP_TAG -> {
                String groupString = info.toUpperCase();
                currentMedicine.setGroup(Enum.valueOf(Group.class, groupString));
            }
            case VERSIONS_TAG -> {
                String versionString = info.toUpperCase();
                currentMedicine.setVersion(Enum.valueOf(Versions.class, versionString));
            }
            case NUMBER_TAG -> {
                currentMedicine
                        .getCertificate()
                        .setNumber(Integer.parseInt(info));
            }
            case DATE_OF_ISSUE_TAG -> {
                currentMedicine
                        .getCertificate()
                        .setDateOfIssue(YearMonth.parse(info));
            }
            case EXPIRATION_DATE_TAG -> {
                currentMedicine
                        .getCertificate()
                        .setExpirationDate(YearMonth.parse(info));
            }
            case REGISTERING_ORGANISATION_TAG -> {
                currentMedicine
                        .getCertificate()
                        .setRegisteringOrganisation(info);
            }
            case TYPE_TAG -> {
                MedicineXMLTagUtil medicineXMLTagUtil = new MedicineXMLTagUtil();
                String packageTypeString = medicineXMLTagUtil.fromTagToEnumString(info);
                currentMedicine
                        .getMedicinePackage()
                        .setPackageType(Enum.valueOf(PackageType.class, packageTypeString));
            }
            case COUNT_TAG -> {
                currentMedicine
                        .getMedicinePackage()
                        .setCount(Integer.parseInt(info));
            }
            case PRICE_TAG -> {
                currentMedicine
                        .getMedicinePackage()
                        .setPrice(Integer.parseInt(info));
            }
            case PERIOD_TAG -> {
                currentMedicine.getDosage().setPeriod(info);
            }
            case DOSE_TAG -> {
                currentMedicine.getDosage().setDose(info);
            }
            case AGE_TAG -> {
                String ageString = info.toUpperCase();
                HumanMedicine humanMedicine = (HumanMedicine) currentMedicine;
                humanMedicine.setHumanAge(Enum.valueOf(HumanAge.class, info));
            }
            case TYPE_OF_ANIMAL_TAG -> {
                String typeOfAnimalString = info.toUpperCase();
                VeterinaryMedicine veterinaryMedicine = (VeterinaryMedicine) currentMedicine;
                veterinaryMedicine.setTypeOfAnimal(Enum.valueOf(TypeOfAnimal.class, info));
            }

        }
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }
}
