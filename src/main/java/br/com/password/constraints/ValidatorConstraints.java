package br.com.password.constraints;

public class ValidatorConstraints {

    public static final String SPACE_BETWEEN = "(?=.*\\s).*";
    public static final String DIGIT = "(?=.*\\d).*";
    public static final String LOWER_CASE = "(?=.*[a-z]).*";
    public static final String UPPER_CASE = "(?=.*[A-Z]).*";
    public static final String SPECIAL_CHARACTER = "(?=.*[!@#$%^&*()-+]).*";
    public static final String LENGTH = ".{9,}";
    public static final String REPETITION_CHARACTER = "^.*(.).*\\1.*$";
}
