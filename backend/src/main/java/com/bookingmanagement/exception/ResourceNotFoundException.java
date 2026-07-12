package com.bookingmanagement.exception;

// Eksairesh otan den vrethei mia eggrafh sth vash
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
