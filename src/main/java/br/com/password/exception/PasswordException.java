package br.com.password.exception;

public class PasswordException extends RuntimeException {

    private int httpStatus;
    private String error;

    public PasswordException(int httpStatus, String message, String error) {
        super(message);
        this.httpStatus = httpStatus;
        this.error = error;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "PasswordException{" + "httpStatus=" + httpStatus +
                ", error='" + error + '\'' +
                '}';
    }
}

