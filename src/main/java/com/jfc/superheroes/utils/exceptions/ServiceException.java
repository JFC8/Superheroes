package com.jfc.superheroes.utils.exceptions;


public class ServiceException extends Exception {
    public static final String ERROR_CODE = "service_unavailable";

    public ServiceException() {
        this((Throwable)null);
    }

    public ServiceException(Throwable cause) {
        super("service_unavailable", "Service Unavailable", cause);
    }

}