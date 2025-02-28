package com.overengineeredecommerce.application.exception;

public class UniqueInsertConstraint extends RuntimeException {
    public UniqueInsertConstraint(String message) {
        super(message);
    }
}