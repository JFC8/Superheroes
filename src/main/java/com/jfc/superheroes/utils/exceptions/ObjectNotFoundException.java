package com.jfc.superheroes.utils.exceptions;

public class ObjectNotFoundException extends Exception {
    public static final String ERROR_CODE = "object_not_found";

    public ObjectNotFoundException(String objectName) {
        this(objectName, (String)null);
    }

    public ObjectNotFoundException(String objectName, String objectId) {
        this("object_not_found", objectName, objectId);
    }

    public ObjectNotFoundException(String errorCode, String objectName, String objectId) {
        super(errorCode, objectId != null
                ? String.format("%s [%s] not found", objectName, objectId)
                : String.format("%s not found", objectName));
    }
}