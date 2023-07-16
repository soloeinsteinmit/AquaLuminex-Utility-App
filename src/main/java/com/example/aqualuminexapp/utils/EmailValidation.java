package com.example.aqualuminexapp.utils;

import io.github.palexdev.materialfx.controls.MFXTextField;

public class EmailValidation {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9+_.-]+$";

    public void validateEmail(MFXTextField emailField) {
        String email = emailField.getText();
        if (email.matches(EMAIL_REGEX)) {
            emailField.setStyle("-fx-border-color: #7a0ed9");
        } else {
            emailField.setStyle("-fx-border-color: #EF6E6B");
        }
    }


}