package ru.perminov.carpool.exceptions.errors;

public class ClientException extends RuntimeException {
    public ClientException(final String message) {
        super(message);
    }
}
