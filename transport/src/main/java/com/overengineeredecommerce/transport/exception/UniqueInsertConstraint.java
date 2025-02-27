package com.overengineeredecommerce.transport.exception;

public class UniqueInsertConstraint extends RuntimeException {
    public UniqueInsertConstraint(String message) {
        super(message);
    }
}