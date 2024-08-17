package com.hotel.HotelServices.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String exception) {
        super(exception);
    }
    public ResourceNotFoundException() {
        super("Resource Not Found !!");
    }
}
