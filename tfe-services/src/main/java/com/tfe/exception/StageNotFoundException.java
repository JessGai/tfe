package com.tfe.exception;

public class StageNotFoundException extends RuntimeException {
    public StageNotFoundException(int id) {

        super("Stage not found with ID: " + id);
    }
}
