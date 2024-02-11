package com.jfc.superheroes.utils.Validations;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import java.util.HashSet;
import java.util.Set;

public class Validations
{
    public static final String INVALID = "Invalid";
    public static final String REQUIRED = "Required";
    public static final String NOT_FOUND = "NotFound";
    public static final String ALREADY_EXISTS = "AlreadyExists";
    private final Set<ConstraintViolation<?>> constraints = new HashSet();

    public Validations() {
    }

    public void addValidations(Set<ConstraintViolation<?>> validations) {
        validations.forEach((validation) -> {
            ConstraintViolationFactory.addConstraintViolation(this.constraints, validation);
        });
    }

    public void addValidation(Class<?> beanClass, String messageTemplate, String property, Object value, String message) {
        ConstraintViolationFactory.addConstraintViolation(this.constraints, messageTemplate, beanClass, property, value, message);
    }

    public void addInvalidValidation(Class<?> beanClass, String property, Object value) {
        this.addValidation(beanClass, "Invalid", property, value, "is invalid");
    }

    public void addRequiredValidation(Class<?> beanClass, String property, Object value) {
        this.addValidation(beanClass, "Required", property, value, "is required");
    }

    public void addNotFoundValidation(Class<?> beanClass, String property, Object value) {
        this.addValidation(beanClass, "NotFound", property, value, "is not found");
    }

    public void addAlreadyExistsValidation(Class<?> beanClass, String property, Object value) {
        this.addValidation(beanClass, "AlreadyExists", property, value, "already exists");
    }

    public void validate() throws ValidationException {
        if (!this.constraints.isEmpty()) {
            throw new ValidationException((Throwable) this.constraints);
        }
    }

    public static ValidationsBuilder builder() {
        return new ValidationsBuilder();
    }

    public static class ValidationsBuilder {
        private final Validations validations = new Validations();

        public ValidationsBuilder() {
        }

        public ValidationsBuilder addValidations(Set<ConstraintViolation<?>> validations) {
            this.validations.addValidations(validations);
            return this;
        }

        public ValidationsBuilder validation(String messageTemplate, String property, Object value, String message) {
            this.validations.addValidation((Class)null, messageTemplate, property, value, message);
            return this;
        }

        public ValidationsBuilder validation(String messageTemplate, String message) {
            this.validations.addValidation((Class)null, messageTemplate, (String)null, (Object)null, message);
            return this;
        }

        public ValidationsBuilder invalid(String property, Object value) {
            this.validations.addInvalidValidation((Class)null, property, value);
            return this;
        }

        public ValidationsBuilder required(String property, Object value) {
            this.validations.addRequiredValidation((Class)null, property, value);
            return this;
        }

        public ValidationsBuilder notFound(String property, Object value) {
            this.validations.addNotFoundValidation((Class)null, property, value);
            return this;
        }

        public ValidationsBuilder alreadyExists(String property, Object value) {
            this.validations.addAlreadyExistsValidation((Class)null, property, value);
            return this;
        }

        public Validations build() {
            return this.validations;
        }
    }
}
