package com.tfe.exception;

public class PanierNotFoundException extends RuntimeException{
    public PanierNotFoundException(int id) {
        super("Panier not found with ID: " + id);
    }
}
