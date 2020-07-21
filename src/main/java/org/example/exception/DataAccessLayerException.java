package org.example.exception;

public class DataAccessLayerException extends RuntimeException {
    public DataAccessLayerException() {
    }

    public DataAccessLayerException(String message) {
        super(message);
    }
}
