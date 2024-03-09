package gng4120.group3.project.payload.response;

import gng4120.group3.project.infrastructure.exceptions.WebAPIException;
import gng4120.group3.project.infrastructure.exceptions.WebException;

import java.util.Date;

public class ErrorResponse {

    private int statusCode;
    private String status;
    private WebException.ErrorCode errorCode;
    private String message;
    private String path;
    private Date timestamp;

    public ErrorResponse() {
        this.timestamp = new Date();
    }

    public ErrorResponse(WebAPIException exception) {
        this();
        this.statusCode = exception.getStatus().value();
        this.status = exception.getStatus().toString();
        this.errorCode = exception.getErrorCode();
        this.message = exception.getMessage();
        this.path = exception.getPath();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public WebException.ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(WebException.ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}