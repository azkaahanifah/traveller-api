package com.project.traveller.exception;

public class BusinessException extends RuntimeException {

    /**
     * Why we extend RuntimeException?
     * Because this is Unchecked Exception
     * Unchecked Exception are errors or exceptions that are known during the compilation process
     * They are typically used to indicate program errors related to business logic or incorrect
     * usage of an API
     * @param message
     */
    public BusinessException(String message) {
        super(message);
    }
}
