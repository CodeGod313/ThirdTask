package edu.epam.thirdtask.builder.impl;

import edu.epam.thirdtask.builder.MedicineBuilder;
import edu.epam.thirdtask.entity.Medicine;
import edu.epam.thirdtask.exception.XMLProcessException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class MedicineBuilderSAX implements MedicineBuilder {
    static Logger logger = LogManager.getLogger(MedicineBuilderSAX.class);
    private MedicineHandler handler;
    private XMLReader reader;

    public MedicineBuilderSAX() throws XMLProcessException {
        handler = new MedicineHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            reader = parser.getXMLReader();
            reader.setContentHandler(handler);
            reader.setErrorHandler(new MedicineErrorHandler());
        } catch (ParserConfigurationException | SAXException e) {
            logger.error("Unable to configure SAX parser");
            throw new XMLProcessException("Unable to configure SAX parser", e);
        }
    }

    @Override
    public List<Medicine> buildAllMedicines(String xmlFilePath) throws XMLProcessException {
        try {
            reader.parse(xmlFilePath);
        } catch (IOException | SAXException e) {
            logger.error("Can not parse XML file");
            throw new XMLProcessException("Can not parse XML file", e);
        }
        return handler.getMedicines();
    }
}
