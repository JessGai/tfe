package com.tfe.exception;

public class ParentNotFoundException extends RuntimeException{
    public ParentNotFoundException(int id) {
        super("Parent not found with ID: " + id);
    }
}
