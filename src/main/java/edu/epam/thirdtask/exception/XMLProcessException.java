package edu.epam.thirdtask.exception;

public class XMLProcessException extends Exception {
    public XMLProcessException() {
    }

    public XMLProcessException(String message) {
        super(message);
    }

    public XMLProcessException(String message, Throwable cause) {
        super(message, cause);
    }

    public XMLProcessException(Throwable cause) {
        super(cause);
    }
}
