package com.bookingmanagement.exception;

// Εξαίρεση όταν δεν βρεθεί μια εγγραφή στη βάση δεδομένων.
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
