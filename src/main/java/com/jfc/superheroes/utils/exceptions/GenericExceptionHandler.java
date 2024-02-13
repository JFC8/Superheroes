package com.jfc.superheroes.utils.exceptions;

import com.jfc.superheroes.utils.Utils;
import com.jfc.superheroes.utils.Validations.ConstraintViolationFactory;
import com.jfc.superheroes.utils.Validations.ValidationsException;
import com.jfc.superheroes.utils.exceptions.errors.AbstractExceptionHandler;
import com.jfc.superheroes.utils.exceptions.errors.ErrorInfo;
import com.jfc.superheroes.utils.exceptions.errors.ValidationErrorInfo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ControllerAdvice
public class GenericExceptionHandler extends AbstractExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GenericExceptionHandler.class);

    public GenericExceptionHandler(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @ExceptionHandler({MaxUploadSizeExceededException.class})
    public ResponseEntity<ErrorInfo> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exception, WebRequest webRequest) {
        ErrorInfo errorInfo = this.getErrorInfo(this.getErrorId(), "max_upload_size_exceeded", exception.getMessage(), (List)null, webRequest);
        log.error("{}: {}", Utils.getCurrentMethodName(), errorInfo);
        return new ResponseEntity(errorInfo, emptyHeaders, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MultipartException.class})
    public ResponseEntity<ErrorInfo> handleMultipartException(MultipartException exception, WebRequest webRequest) {
        ErrorInfo errorInfo = this.getErrorInfo(this.getErrorId(), "multipart_bad_request", exception.getMessage(), (List)null, webRequest);
        log.error("{}: {}", Utils.getCurrentMethodName(), errorInfo);
        return new ResponseEntity(errorInfo, emptyHeaders, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageConversionException.class})
    public ResponseEntity<ErrorInfo> handleHttpMessageConversionException(HttpMessageConversionException exception, WebRequest webRequest) {
        ErrorInfo errorInfo = this.getErrorInfo(this.getErrorId(), "parse_error", exception.getMessage(), (List)null, webRequest);
        log.error("{}: {}", Utils.getCurrentMethodName(), errorInfo);
        return new ResponseEntity(errorInfo, emptyHeaders, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<ErrorInfo> handleMethodArgumentNotValidException(BindException exception, WebRequest webRequest) {
        StringBuilder message = new StringBuilder();
        List<ValidationErrorInfo> validations = new ArrayList();
        Iterator var5 = exception.getFieldErrors().iterator();

        while(var5.hasNext()) {
            FieldError fieldError = (FieldError)var5.next();
            message.append(String.format(" %s [%s]: %s.", fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage()));
            validations.add(ValidationErrorInfo.builder().field(fieldError.getField()).error(fieldError.getCode()).value(fieldError.getRejectedValue()).message(fieldError.getDefaultMessage()).build());
        }

        ErrorInfo errorInfo = this.getErrorInfo(this.getErrorId(), "validation_error", message.toString(), validations, webRequest);
        log.error("{}: {}", Utils.getCurrentMethodName(), errorInfo);
        return new ResponseEntity(errorInfo, emptyHeaders, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorInfo> handleConstraintViolationException(ConstraintViolationException exception, WebRequest webRequest) {
        StringBuilder message = new StringBuilder();
        List<ValidationErrorInfo> validations = new ArrayList();
        Iterator var5 = exception.getConstraintViolations().iterator();

        while(var5.hasNext()) {
            ConstraintViolation<?> constraintViolation = (ConstraintViolation)var5.next();
            String errorCode = ConstraintViolationFactory.getErrorCode(constraintViolation);
            message.append(String.format(" %s [%s]: %s.", constraintViolation.getPropertyPath(), constraintViolation.getInvalidValue(), constraintViolation.getMessage()));
            validations.add(ValidationErrorInfo.builder().field(constraintViolation.getPropertyPath().toString()).error(errorCode).value(constraintViolation.getInvalidValue()).message(constraintViolation.getMessage()).build());
        }

        ErrorInfo errorInfo = this.getErrorInfo(this.getErrorId(), "validation_error", message.toString(), validations, webRequest);
        log.error("{}: {}", Utils.getCurrentMethodName(), errorInfo);
        return new ResponseEntity(errorInfo, emptyHeaders, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ObjectNotFoundException.class, ObjectAlreadyExistsException.class})
    public ResponseEntity<ErrorInfo> handleObjectNotFoundException(CustomException exception, WebRequest webRequest) {
        ErrorInfo errorInfo = this.getErrorInfo(exception, webRequest);
        log.error("{}: {}", Utils.getCurrentMethodName(), errorInfo);
        return new ResponseEntity(errorInfo, emptyHeaders, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<ErrorInfo> handleServiceException(ServiceException exception, WebRequest webRequest) {
        Throwable th = this.getCause(exception);
        if (th instanceof ValidationsException aswValidationException) {
            return this.handleConstraintViolationException(aswValidationException, webRequest);
        } else {
            ErrorInfo errorInfo = this.getErrorInfo(exception, webRequest);
            log.error("{}: {}: {}", new Object[]{Utils.getCurrentMethodName(), errorInfo, this.getStackTraceWriter(th)});
            return new ResponseEntity(errorInfo, emptyHeaders, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, HttpMediaTypeNotSupportedException.class, HttpMediaTypeNotAcceptableException.class, MissingPathVariableException.class, MissingServletRequestParameterException.class, MissingServletRequestPartException.class, ServletRequestBindingException.class, NoHandlerFoundException.class, AsyncRequestTimeoutException.class, ErrorResponseException.class, ConversionNotSupportedException.class, TypeMismatchException.class})
    public final ResponseEntity<Object> handleException(Exception exception, WebRequest webRequest) {
        ErrorInfo errorInfo = this.getErrorInfo(this.getErrorId(), "bad_request", exception.getMessage(), (List)null, webRequest);
        log.error("{}: {}", Utils.getCurrentMethodName(), errorInfo);
        return new ResponseEntity(errorInfo, emptyHeaders, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorInfo> handleUnexpectedException(Exception exception, WebRequest webRequest) throws Exception {
        if (exception instanceof ClientAbortException clientAbortException) {
            log.warn(clientAbortException.toString());
            return null;
        } else if (exception instanceof ServiceException serviceException) {
            return this.handleServiceException(serviceException, webRequest);
        } else {
            return this.handleServiceException(new ServiceException(exception), webRequest);
        }
    }
}