package edu.tntech.jemma.exceptions;

public class ApiException extends RuntimeException {

    private final int errorCode;

    public ApiException(int errorCode, String message) {
        super(errorCode + ": " + message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
