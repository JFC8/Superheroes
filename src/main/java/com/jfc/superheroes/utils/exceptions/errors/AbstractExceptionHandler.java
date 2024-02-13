package com.jfc.superheroes.utils.exceptions.errors;

import com.jfc.superheroes.utils.Utils;
import com.jfc.superheroes.utils.exceptions.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public abstract class AbstractExceptionHandler {
    protected static final HttpHeaders emptyHeaders = new HttpHeaders();
    protected final ErrorAttributes errorAttributes;

    public AbstractExceptionHandler(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    protected String getErrorId() {
        return Utils.generateUUID();
    }

    protected HttpServletRequest getHttpRequest(WebRequest webRequest) {
        return ((ServletWebRequest)webRequest).getRequest();
    }

    protected String getPah(WebRequest webRequest) {
        ErrorAttributeOptions errorAttributeOptions = ErrorAttributeOptions.defaults();
        Map<String, Object> errorAttributesMap = this.errorAttributes.getErrorAttributes(webRequest, errorAttributeOptions);
        String path = (String)errorAttributesMap.get("path");
        if (path == null) {
            path = this.getHttpRequest(webRequest).getRequestURI();
        }

        return path;
    }

    protected ErrorInfo getErrorInfo(String errorId, String errorCode, String message, List<ValidationErrorInfo> validations, WebRequest webRequest) {
        return ErrorInfo.builder().errorId(errorId).errorCode(errorCode).message(message).validations(validations).method(this.getHttpRequest(webRequest).getMethod()).path(this.getPah(webRequest)).timestamp(LocalDateTime.now()).build();
    }

    protected ErrorInfo getErrorInfo(CustomException aswException, WebRequest webRequest) {
        return ErrorInfo.builder().errorId(aswException.getErrorId()).errorCode(aswException.getErrorCode()).message(aswException.getMessage()).validations((List)null).method(this.getHttpRequest(webRequest).getMethod()).path(this.getPah(webRequest)).timestamp(aswException.getTimestamp()).build();
    }

    protected Writer getStackTraceWriter(Throwable throwable) {
        StringWriter stack = new StringWriter();
        PrintWriter pw = new PrintWriter(stack, true);
        throwable.printStackTrace(pw);
        return stack;
    }

    protected Throwable getCause(Throwable th) {
        while(th.getCause() != null) {
            th = th.getCause();
        }

        return th;
    }
}