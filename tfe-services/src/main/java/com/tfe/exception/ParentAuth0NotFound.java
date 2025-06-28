package com.tfe.exception;

public class ParentAuth0NotFound extends RuntimeException{

        public ParentAuth0NotFound(String id) {
            super("Parent not found with ID: " + id);
        }

}
