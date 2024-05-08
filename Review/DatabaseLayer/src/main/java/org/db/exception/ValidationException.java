package org.db.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String info) {
        super(info);
    }
}
