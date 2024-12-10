package ru.perminov.carpool.exceptions.errors;

public class BadCredentailsException extends RuntimeException {
    public BadCredentailsException(final String message) {
        super(message);
    }
}
