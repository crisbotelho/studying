package com.company.cris.exception;

public class EmployeeAlreadyExistException extends RuntimeException {
    private String message;

    public EmployeeAlreadyExistException(String message) {
        super(message);
        this.message = message;
    }

    public EmployeeAlreadyExistException() {
    }
}
