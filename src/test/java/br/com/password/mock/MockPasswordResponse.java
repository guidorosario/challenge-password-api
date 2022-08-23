package br.com.password.mock;

import br.com.password.model.PasswordResponse;

import static br.com.password.constraints.Messages.ERROR_DIGIT;
import static br.com.password.constraints.Messages.ERROR_LENGTH;
import static br.com.password.constraints.Messages.ERROR_LOWER_CASE;
import static br.com.password.constraints.Messages.ERROR_SPACE_BETWEEN;
import static br.com.password.constraints.Messages.ERROR_SPECIAL_CHARACTER;
import static br.com.password.constraints.Messages.ERROR_UPPER_CASE;
import static br.com.password.constraints.Messages.ERROR_REPETITION_CHARACTER;

public class MockPasswordResponse {

    public static PasswordResponse errorsLengthSpaceBetweenDigitLowerAndUpperCaseSpecialCharacter() {
        var response = new PasswordResponse();
        response.getErrorPassword().add(ERROR_LENGTH);
        response.getErrorPassword().add(ERROR_SPACE_BETWEEN);
        response.getErrorPassword().add(ERROR_DIGIT);
        response.getErrorPassword().add(ERROR_LOWER_CASE);
        response.getErrorPassword().add(ERROR_UPPER_CASE);
        response.getErrorPassword().add(ERROR_SPECIAL_CHARACTER);
        return response;
    }

    public static PasswordResponse errorsLengthDigitLowerCaseSpecialCharacter() {
        var response = new PasswordResponse();
        response.getErrorPassword().add(ERROR_LENGTH);
        response.getErrorPassword().add(ERROR_DIGIT);
        response.getErrorPassword().add(ERROR_LOWER_CASE);
        response.getErrorPassword().add(ERROR_SPECIAL_CHARACTER);

        return response;
    }

    public static PasswordResponse errorsBetweenSpace() {
        var response = new PasswordResponse();
        response.getErrorPassword().add(ERROR_SPACE_BETWEEN);

        return response;

    }

    public static PasswordResponse errorsUpperCase() {
        var response = new PasswordResponse();
        response.getErrorPassword().add(ERROR_UPPER_CASE);
        return response;
    }

    public static PasswordResponse errorsLowerCase() {
        var response = new PasswordResponse();
        response.getErrorPassword().add(ERROR_LOWER_CASE);
        return response;
    }

    public static PasswordResponse errorsNoDigit() {
        var response = new PasswordResponse();
        response.getErrorPassword().add(ERROR_DIGIT);
        return response;
    }

    public static PasswordResponse errorsNoSpecialCharacter() {
        var response = new PasswordResponse();
        response.getErrorPassword().add(ERROR_SPECIAL_CHARACTER);
        return response;
    }

    public static PasswordResponse errorsRepetitionCharacter() {
        var response = new PasswordResponse();
        response.getErrorPassword().add(ERROR_REPETITION_CHARACTER);
        return response;
    }

}
