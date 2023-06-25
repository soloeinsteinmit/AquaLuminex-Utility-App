package com.example.aqualuminexapp.register;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PasswordSecurityController implements Initializable {
    public static AnchorPane pA;
    @FXML
    private AnchorPane passwordAnchorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pA = passwordAnchorPane;
    }
}