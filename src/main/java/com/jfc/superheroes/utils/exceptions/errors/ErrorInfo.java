package com.jfc.superheroes.utils.exceptions.errors;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ErrorInfo {
    private String errorId;
    private String errorCode;
    private String message;
    private List<ValidationErrorInfo> validations;
    private String method;
    private String path;
    private LocalDateTime timestamp;

    protected Map<String, Object> toMap() {
        Map<String, Object> map = new LinkedHashMap();
        map.put("errorId", this.errorId);
        map.put("errorCode", this.errorCode);
        map.put("message", this.message);
        map.put("validations", this.validations);
        map.put("method", this.method);
        map.put("path", this.path);
        map.put("timestamp", this.timestamp);
        return map;
    }

    public static ErrorInfoBuilder builder() {
        return new ErrorInfoBuilder();
    }

    public String getErrorId() {
        return this.errorId;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    public List<ValidationErrorInfo> getValidations() {
        return this.validations;
    }

    public String getMethod() {
        return this.method;
    }

    public String getPath() {
        return this.path;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setErrorId(final String errorId) {
        this.errorId = errorId;
    }

    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setValidations(final List<ValidationErrorInfo> validations) {
        this.validations = validations;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ErrorInfo)) {
            return false;
        } else {
            ErrorInfo other = (ErrorInfo)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label95: {
                    Object this$errorId = this.getErrorId();
                    Object other$errorId = other.getErrorId();
                    if (this$errorId == null) {
                        if (other$errorId == null) {
                            break label95;
                        }
                    } else if (this$errorId.equals(other$errorId)) {
                        break label95;
                    }

                    return false;
                }

                Object this$errorCode = this.getErrorCode();
                Object other$errorCode = other.getErrorCode();
                if (this$errorCode == null) {
                    if (other$errorCode != null) {
                        return false;
                    }
                } else if (!this$errorCode.equals(other$errorCode)) {
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

                label74: {
                    Object this$validations = this.getValidations();
                    Object other$validations = other.getValidations();
                    if (this$validations == null) {
                        if (other$validations == null) {
                            break label74;
                        }
                    } else if (this$validations.equals(other$validations)) {
                        break label74;
                    }

                    return false;
                }

                label67: {
                    Object this$method = this.getMethod();
                    Object other$method = other.getMethod();
                    if (this$method == null) {
                        if (other$method == null) {
                            break label67;
                        }
                    } else if (this$method.equals(other$method)) {
                        break label67;
                    }

                    return false;
                }

                Object this$path = this.getPath();
                Object other$path = other.getPath();
                if (this$path == null) {
                    if (other$path != null) {
                        return false;
                    }
                } else if (!this$path.equals(other$path)) {
                    return false;
                }

                Object this$timestamp = this.getTimestamp();
                Object other$timestamp = other.getTimestamp();
                if (this$timestamp == null) {
                    if (other$timestamp != null) {
                        return false;
                    }
                } else if (!this$timestamp.equals(other$timestamp)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ErrorInfo;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $errorId = this.getErrorId();
        result = result * 59 + ($errorId == null ? 43 : $errorId.hashCode());
        Object $errorCode = this.getErrorCode();
        result = result * 59 + ($errorCode == null ? 43 : $errorCode.hashCode());
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        Object $validations = this.getValidations();
        result = result * 59 + ($validations == null ? 43 : $validations.hashCode());
        Object $method = this.getMethod();
        result = result * 59 + ($method == null ? 43 : $method.hashCode());
        Object $path = this.getPath();
        result = result * 59 + ($path == null ? 43 : $path.hashCode());
        Object $timestamp = this.getTimestamp();
        result = result * 59 + ($timestamp == null ? 43 : $timestamp.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getErrorId();
        return "ErrorInfo(errorId=" + var10000 + ", errorCode=" + this.getErrorCode() + ", message=" + this.getMessage() + ", validations=" + this.getValidations() + ", method=" + this.getMethod() + ", path=" + this.getPath() + ", timestamp=" + this.getTimestamp() + ")";
    }

    public ErrorInfo() {
    }

    public ErrorInfo(final String errorId, final String errorCode, final String message, final List<ValidationErrorInfo> validations, final String method, final String path, final LocalDateTime timestamp) {
        this.errorId = errorId;
        this.errorCode = errorCode;
        this.message = message;
        this.validations = validations;
        this.method = method;
        this.path = path;
        this.timestamp = timestamp;
    }

    public static class ErrorInfoBuilder {
        private String errorId;
        private String errorCode;
        private String message;
        private List<ValidationErrorInfo> validations;
        private String method;
        private String path;
        private LocalDateTime timestamp;

        ErrorInfoBuilder() {
        }

        public ErrorInfoBuilder errorId(final String errorId) {
            this.errorId = errorId;
            return this;
        }

        public ErrorInfoBuilder errorCode(final String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public ErrorInfoBuilder message(final String message) {
            this.message = message;
            return this;
        }

        public ErrorInfoBuilder validations(final List<ValidationErrorInfo> validations) {
            this.validations = validations;
            return this;
        }

        public ErrorInfoBuilder method(final String method) {
            this.method = method;
            return this;
        }

        public ErrorInfoBuilder path(final String path) {
            this.path = path;
            return this;
        }

        public ErrorInfoBuilder timestamp(final LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErrorInfo build() {
            return new ErrorInfo(this.errorId, this.errorCode, this.message, this.validations, this.method, this.path, this.timestamp);
        }

        public String toString() {
            return "ErrorInfo.ErrorInfoBuilder(errorId=" + this.errorId + ", errorCode=" + this.errorCode + ", message=" + this.message + ", validations=" + this.validations + ", method=" + this.method + ", path=" + this.path + ", timestamp=" + this.timestamp + ")";
        }
    }
}