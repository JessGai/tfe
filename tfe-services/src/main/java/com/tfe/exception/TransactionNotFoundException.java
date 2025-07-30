package com.tfe.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(int id) {
        super("Transaction not found with ID: " + id);
    }
}
