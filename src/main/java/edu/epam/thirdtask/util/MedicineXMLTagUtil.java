package edu.epam.thirdtask.util;

public class MedicineXMLTagUtil {
    public static final String HUMAN_MEDICINE_TAG = "human-medicine";
    public static final String VETERINARY_MEDICINE_TAG = "veterinary-medicine";
    public static final String ID_TAG = "id";
    public static final String WEBSITE_TAG = "pharm-website";
    public static final String NAME_TAG = "name";
    public static final String PHARM_TAG = "pharm";
    public static final String ANALOG_TAG = "analog";
    public static final String GROUP_TAG = "group";
    public static final String VERSIONS_TAG = "versions";
    public static final String NUMBER_TAG = "number";
    public static final String DATE_OF_ISSUE_TAG = "date-of-issue";
    public static final String EXPIRATION_DATE_TAG = "expiration-date";
    public static final String REGISTERING_ORGANISATION_TAG = "registering-organisation";
    public static final String TYPE_TAG = "type";
    public static final String COUNT_TAG = "count";
    public static final String AGE_TAG = "age";
    public static final String TYPE_OF_ANIMAL_TAG = "type-of-animal";
    public static final String PRICE_TAG = "price";
    public static final String PERIOD_TAG = "period";
    public static final String DOSE_TAG = "dose";

    public static final char HYPHEN = '-';
    public static final char UNDERSCORE = '_';

    public String fromTagToEnumString(String stringTag) {
        return stringTag.toUpperCase().replace(HYPHEN, UNDERSCORE);
    }
}
