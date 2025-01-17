package com.example.registrationpage;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import com.example.registrationpage.utils.CheckInputValidity;

import java.util.ArrayList;

public class RegistrationUnitTests {
    @Test
    public void areRegistrationFieldsEmpty_ReturnsFalse_ForNonEmptyInput() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Maksym");
        arr.add("Petrov");
        arr.add("qwertY54391");
        assertFalse(CheckInputValidity.areRegistrationFieldsEmpty(arr));
    }

    @Test
    public void areRegistrationFieldsEmpty_NameFieldEmpty_ReturnsTrue_ForNonEmptyInput() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("");
        arr.add("Petrov");
        arr.add("qwertY54391");
        assertTrue(CheckInputValidity.areRegistrationFieldsEmpty(arr));
    }

    @Test
    public void areRegistrationFieldsEmpty_SurnameFieldEmpty_ReturnsTrue_ForNonEmptyInput() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Maksym");
        arr.add("");
        arr.add("qwertY54391");
        assertTrue(CheckInputValidity.areRegistrationFieldsEmpty(arr));
    }

    @Test
    public void areRegistrationFieldsEmpty_PasswordFieldEmpty_ReturnsTrue_ForNonEmptyInput() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Maksym");
        arr.add("Petrov");
        arr.add("");
        assertTrue(CheckInputValidity.areRegistrationFieldsEmpty(arr));
    }

    @Test
    public void areUsersNameAndSurnameValid_ValidNames_ReturnsTrue() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Maksym");
        arr.add("Petrov");
        assertTrue(CheckInputValidity.areUsersNameAndSurnameValid(arr));
    }

    @Test
    public void areUsersNameAndSurnameValid_InvalidNames_ReturnsFalse() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("   Maksym");
        arr.add("Petrov");
        assertFalse(CheckInputValidity.areUsersNameAndSurnameValid(arr));
    }

    @Test
    public void isPasswordReliable_WeakPassword_ReturnsFalse() {
        String password = "weak";
        assertFalse(CheckInputValidity.isPasswordReliable(password));
    }

    @Test
    public void isPasswordReliable_ShortPassword_ReturnsFalse() {
        String password = "Strong1";
        assertFalse(CheckInputValidity.isPasswordReliable(password));
    }

    @Test
    public void isPasswordReliable_StrongPassword_ReturnsTrue() {
        String password = "SStronggggg1";
        assertTrue(CheckInputValidity.isPasswordReliable(password));
    }
}

