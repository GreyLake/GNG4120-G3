package gng4120.group3.project.infrastructure.exceptions;

public enum ErrorCode {
    OK(1, "OK"),
    INVALID_GENDER(2, "Invalid user gender"),
    INVALID_USER_FORM(3, "Invalid user form"),
    CLIENT_ERROR(4, "Client side error"),
    INTERNAL_ERROR(5, "Internal server error"),
    SENDER_SERVICE_ERROR(6, "Email Sender Service error"),
    INVALID_ACTIVATION_REQUEST(7, "Invalid Activation request"),
    RESOURCE_NOT_FOUND(8, "Resource Not Found");

    ErrorCode(int index, String message) {
        this.index = index;
        this.message = message;
    }

    public final String message;
    public final int index;
}
