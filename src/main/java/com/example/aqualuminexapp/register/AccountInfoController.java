package com.example.aqualuminexapp.register;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountInfoController implements Initializable {
    public static AnchorPane aP;
    @FXML
    private AnchorPane accountInfoAnchorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aP = accountInfoAnchorPane;
    }
}