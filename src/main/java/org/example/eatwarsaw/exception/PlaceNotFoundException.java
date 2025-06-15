package org.example.eatwarsaw.exception;

public class PlaceNotFoundException extends RuntimeException {
    public PlaceNotFoundException(Long id) {
        super("Place with id " + id + " not found");
    }

    public PlaceNotFoundException(String message) {
        super(message);
    }
}