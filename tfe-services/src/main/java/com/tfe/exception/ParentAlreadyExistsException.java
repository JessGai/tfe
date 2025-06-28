package com.tfe.exception;

public class ParentAlreadyExistsException extends RuntimeException{
    public ParentAlreadyExistsException(String id) {
        super("Parent with ID: " + id + " already exist");
    }
}
