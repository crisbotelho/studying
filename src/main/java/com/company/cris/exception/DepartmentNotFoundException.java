package com.company.cris.exception;

public class DepartmentNotFoundException extends RuntimeException{

    private String message;

    public DepartmentNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public DepartmentNotFoundException() {
    }
}
