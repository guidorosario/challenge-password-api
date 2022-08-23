package br.com.password.constraints;

import br.com.password.service.ValidatorService;
import br.com.password.service.impl.DigitValidatorImpl;
import br.com.password.service.impl.LengthValidatorImpl;
import br.com.password.service.impl.LowerCaseValidatorImpl;
import br.com.password.service.impl.RepetitionCharacterValidatorImpl;
import br.com.password.service.impl.SpaceBetweenValidatorImpl;
import br.com.password.service.impl.SpecialCharacterValidatorImpl;
import br.com.password.service.impl.UpperCaseValidatorImpl;

import java.util.Arrays;

public enum ValidatorEnum {

    SPACE_BETWEEN  ("SPACE_BETWEEN", SpaceBetweenValidatorImpl.class),
    DIGIT ("DIGIT", DigitValidatorImpl.class),
    LOWER_CASE ("LOWER_CASE", LowerCaseValidatorImpl.class),
    UPPER_CASE ( "UPPER_CASE", UpperCaseValidatorImpl.class),
    SPECIAL_CHARACTER ("SPECIAL_CHARACTER", SpecialCharacterValidatorImpl.class),
    LENGTH ( "LENGTH", LengthValidatorImpl.class),
    REPETITION_CHARACTER  ("REPETITION_CHARACTER", RepetitionCharacterValidatorImpl.class);


    private final String description;
    private final Class<? extends ValidatorService> validatorService;

    public String getDescription() {
        return description;
    }


    public Class<? extends ValidatorService> getValidatorService() {
        return validatorService;
    }

    ValidatorEnum(String description, Class<? extends ValidatorService> validatorService) {
        this.validatorService = validatorService;
        this.description = description;
    }


    public static ValidatorEnum fromEnum(String value) {
        return Arrays.stream(values())
                .filter(at -> at.description.equals(value)).findFirst()
                .orElseThrow();
    }
}
