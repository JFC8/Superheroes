package com.jfc.superheroes.utils.exceptions.errors;


public class ValidationErrorInfo {
    private String field;
    private String error;
    private Object value;
    private String message;

    public static ValidationErrorInfoBuilder builder() {
        return new ValidationErrorInfoBuilder();
    }

    public String getField() {
        return this.field;
    }

    public String getError() {
        return this.error;
    }

    public Object getValue() {
        return this.value;
    }

    public String getMessage() {
        return this.message;
    }

    public void setField(final String field) {
        this.field = field;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public void setValue(final Object value) {
        this.value = value;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ValidationErrorInfo)) {
            return false;
        } else {
            ValidationErrorInfo other = (ValidationErrorInfo)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$field = this.getField();
                    Object other$field = other.getField();
                    if (this$field == null) {
                        if (other$field == null) {
                            break label59;
                        }
                    } else if (this$field.equals(other$field)) {
                        break label59;
                    }

                    return false;
                }

                Object this$error = this.getError();
                Object other$error = other.getError();
                if (this$error == null) {
                    if (other$error != null) {
                        return false;
                    }
                } else if (!this$error.equals(other$error)) {
                    return false;
                }

                Object this$value = this.getValue();
                Object other$value = other.getValue();
                if (this$value == null) {
                    if (other$value != null) {
                        return false;
                    }
                } else if (!this$value.equals(other$value)) {
                    return false;
                }

                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    if (other$message != null) {
                        return false;
                    }
                } else if (!this$message.equals(other$message)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ValidationErrorInfo;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $field = this.getField();
        result = result * 59 + ($field == null ? 43 : $field.hashCode());
        Object $error = this.getError();
        result = result * 59 + ($error == null ? 43 : $error.hashCode());
        Object $value = this.getValue();
        result = result * 59 + ($value == null ? 43 : $value.hashCode());
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getField();
        return "ValidationErrorInfo(field=" + var10000 + ", error=" + this.getError() + ", value=" + this.getValue() + ", message=" + this.getMessage() + ")";
    }

    public ValidationErrorInfo() {
    }

    public ValidationErrorInfo(final String field, final String error, final Object value, final String message) {
        this.field = field;
        this.error = error;
        this.value = value;
        this.message = message;
    }

    public static class ValidationErrorInfoBuilder {
        private String field;
        private String error;
        private Object value;
        private String message;

        ValidationErrorInfoBuilder() {
        }

        public ValidationErrorInfoBuilder field(final String field) {
            this.field = field;
            return this;
        }

        public ValidationErrorInfoBuilder error(final String error) {
            this.error = error;
            return this;
        }

        public ValidationErrorInfoBuilder value(final Object value) {
            this.value = value;
            return this;
        }

        public ValidationErrorInfoBuilder message(final String message) {
            this.message = message;
            return this;
        }

        public ValidationErrorInfo build() {
            return new ValidationErrorInfo(this.field, this.error, this.value, this.message);
        }

        public String toString() {
            return "ValidationErrorInfo.ValidationErrorInfoBuilder(field=" + this.field + ", error=" + this.error + ", value=" + this.value + ", message=" + this.message + ")";
        }
    }
}