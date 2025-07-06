package com.tfe.exception;

public class ChildNotFoundException extends RuntimeException{
    public ChildNotFoundException(int id) {
        super("Child not found with ID: " + id);
    }
}
