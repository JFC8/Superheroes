package com.jfc.superheroes.utils.exceptions;

import com.jfc.superheroes.utils.Utils;

import java.time.LocalDateTime;

public class Exception extends RuntimeException{
    private final String errorId;
    private final LocalDateTime timestamp;
    private final String errorCode;
    private final String message;

    protected Exception() {
        this((String)null);
    }

    protected Exception(String errorCode) {
        this(errorCode, (String)null, (Throwable)null);
    }

    protected Exception(String errorCode, String message) {
        this(errorCode, message, (Throwable)null);
    }

    protected Exception(String errorCode, String message, Throwable cause) {
        super(cause);
        this.errorId = Utils.generateUUID();
        this.timestamp = LocalDateTime.now();
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorId() {
        return this.errorId;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }

}
