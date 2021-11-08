package edu.epam.thirdtask.builder.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.util.StringJoiner;


public class MedicineErrorHandler implements ErrorHandler {
    static Logger logger = LogManager.getLogger(MedicineErrorHandler.class);

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        logger.warn(composeErrorPosition(exception), exception);
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        logger.error(composeErrorPosition(exception), exception);
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        logger.fatal(composeErrorPosition(exception), exception);
    }

    private String composeErrorPosition(SAXParseException e) {
        return e.getLineNumber() + ":" + e.getColumnNumber();
    }
}
