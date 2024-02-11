package com.jfc.superheroes.utils.Validations;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.validation.FieldError;

public class ValidationsException extends ConstraintViolationException
{
    public ValidationsException(String message, Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(message, constraintViolations);
    }

    public ValidationsException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        this(constraintViolations != null ? toString(constraintViolations) : null, constraintViolations);
    }

    public static String toString(Set<? extends ConstraintViolation<?>> constraintViolations) {
        StringBuilder sb = new StringBuilder();

        ConstraintViolation constraintViolation;
        for(Iterator var2 = constraintViolations.iterator(); var2.hasNext(); sb.append(String.format("%s [%s]: %s", constraintViolation.getPropertyPath(), constraintViolation.getInvalidValue(), constraintViolation.getMessage()))) {
            constraintViolation = (ConstraintViolation)var2.next();
            if (!sb.isEmpty()) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    public static String toString(List<FieldError> fieldErrors) {
        StringBuilder sb = new StringBuilder();

        FieldError fieldError;
        for(Iterator var2 = fieldErrors.iterator(); var2.hasNext(); sb.append(String.format("%s [%s]: %s", fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage()))) {
            fieldError = (FieldError)var2.next();
            if (!sb.isEmpty()) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}
