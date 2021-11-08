package edu.epam.thirdtask.validator;

import edu.epam.thirdtask.exception.XMLProcessException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MedicineValidator {
    private static Logger logger = LogManager.getLogger(MedicineValidator.class);
    private static final String SCHEMA_NAME = "schema/schema.xsd";
    private static final String SCHEMA_PATH;

    static {
        ClassLoader loader = MedicineValidator.class.getClassLoader();
        URL resource = loader.getResource(SCHEMA_NAME);
        SCHEMA_PATH = new File(resource.getFile()).getAbsolutePath();
    }

    public boolean validateXML(String xmlFilePath) throws XMLProcessException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory schemaFactory = SchemaFactory.newInstance(language);
        File schemaFile = new File(SCHEMA_PATH);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFilePath);
            validator.validate(source);
        } catch (SAXException e) {
            logger.warn("File " + xmlFilePath + "is not valid" ,e);
            return false;
        } catch (IOException e) {
            logger.error("Can not open XML file " + xmlFilePath, e);
            throw new XMLProcessException("Can not open XML file " + xmlFilePath, e);
        }
        return true;
    }
}
