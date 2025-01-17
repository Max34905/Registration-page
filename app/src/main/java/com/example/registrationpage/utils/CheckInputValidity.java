package com.example.registrationpage.utils;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class CheckInputValidity {

    public static boolean areRegistrationFieldsEmpty(ArrayList<String> inputFields) {
        for (String field : inputFields) {
            if (field.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isParentFieldEmpty(String parentName) {
        return parentName.isEmpty();
    }

    public static boolean isInputAlphabetical(String name) {
        for (char c : name.toCharArray()) {
            if (!Character.isAlphabetic(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean areUsersNameAndSurnameValid(ArrayList<String> input) {
        return isInputAlphabetical(input.get(0)) && isInputAlphabetical(input.get(1));
    }

    public static boolean isParentNameValid(String parentName) {
        return isInputAlphabetical(parentName);
    }

    public static boolean isPasswordReliable(String password) {
        if (password.length() < 8) {
            return false;
        }

        String lowerCaseRegex = ".*[a-z].*";
        String upperCaseRegex = ".*[A-Z].*";
        String digitRegex = ".*[0-9].*";

        return Pattern.matches(lowerCaseRegex, password) &&
                Pattern.matches(upperCaseRegex, password) &&
                Pattern.matches(digitRegex, password);
    }
}
