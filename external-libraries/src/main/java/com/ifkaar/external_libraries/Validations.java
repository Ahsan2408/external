package com.ifkaar.external_libraries;

public class Validations {
    private static final String TAG = "Validations";
    public static boolean validateEmptyField(String text) {
        return !text.isEmpty();
    }

    public static boolean validateName(String nameText) {
        return validateEmptyField(nameText) && nameText.length() >= 3;
    }

    public static boolean validateEmail(String emailText) {
        String regex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";
        return validateEmptyField(emailText) && emailText.matches(regex);
    }

    public static boolean validatePassword(String passwordText) {

        return validateEmptyField(passwordText) && passwordText.length() >= 8 && StringConverter.isAlphanumeric(passwordText);
    }

    public static boolean validatePhoneNumber(String phoneNumberText) {
        String regex = "^\\+.{4,}$";
        return validateEmptyField(phoneNumberText) && phoneNumberText.matches(regex);
    }
}
