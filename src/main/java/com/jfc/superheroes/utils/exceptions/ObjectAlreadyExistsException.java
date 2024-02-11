package com.jfc.superheroes.utils.exceptions;

public class ObjectAlreadyExistsException extends Exception {
    public static final String ERROR_CODE = "object_already_exists";

    public ObjectAlreadyExistsException(String objectName) {
        this(objectName, (String) null);
    }

    public ObjectAlreadyExistsException(String objectName, String objectId) {
        this("object_already_exists", objectName, objectId);
    }

    public ObjectAlreadyExistsException(String errorCode, String objectName, String objectId) {
        super(errorCode, objectId != null
                ? String.format("%s [%s] already exists", objectName, objectId)
                : String.format("%s already exists", objectName));
    }

}