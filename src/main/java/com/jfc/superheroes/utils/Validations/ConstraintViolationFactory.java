package com.jfc.superheroes.utils.Validations;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.metadata.ConstraintDescriptor;
import java.util.Map;
import java.util.Set;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;

public class ConstraintViolationFactory {
    private static final String ERROR_CODE_PREFIX = "constraints.";
    private static final String ERROR_CODE_SUFFIX = ".message";

    public ConstraintViolationFactory() {
    }

    public static String getErrorCode(ConstraintViolation<?> constraintViolation) {
        String errorCode = constraintViolation != null ? constraintViolation.getMessageTemplate() : null;
        if (errorCode != null) {
            int index1 = errorCode.indexOf("constraints.");
            if (index1 >= 0) {
                index1 += "constraints.".length();
                int index2 = errorCode.indexOf(".message");
                if (index2 >= 0) {
                    errorCode = errorCode.substring(index1, index2);
                }
            }
        }

        return errorCode;
    }

    public static void addConstraintViolation(Set<ConstraintViolation<?>> constraintViolations, ConstraintViolation<?> violation) {
        if (constraintViolations != null && violation != null) {
            constraintViolations.add(violation);
        }

    }

    public static void addConstraintViolation(Set<ConstraintViolation<?>> constraintViolations, Class<?> beanClass, String property, String message) {
        addConstraintViolation(constraintViolations, (String)null, beanClass, property, (Object)null, message);
    }

    public static void addConstraintViolation(Set<ConstraintViolation<?>> constraintViolations, String messageTemplate, Class<?> beanClass, String property, Object value, String message) {
        if (constraintViolations != null) {
            constraintViolations.add( createConstraintViolation(messageTemplate, beanClass, property, value, message) );
        }

    }

    public static <T> ConstraintViolation createConstraintViolation(String messageTemplate, Class<T> beanClass, String property, Object value, String message) {
        if (property == null) {
            property = "";
        }

        return ConstraintViolationImpl.forBeanValidation( messageTemplate, (Map)null, (Map)null, message, beanClass, (T)null, (Object)null, value, PathImpl.createPathFromString(property), (ConstraintDescriptor)null, (Object)null);
    }

}