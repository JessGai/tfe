package com.tfe.exception;

public class EmptyStageListException extends RuntimeException {
    public EmptyStageListException() {
        super("Stage description list is empty.");
    }
}
