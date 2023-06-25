package com.example.aqualuminexapp.register;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserDetailsController implements Initializable {
    public static AnchorPane userDetailsAnchorPanePublic;
    @FXML
    private AnchorPane userDetailsAnchorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userDetailsAnchorPanePublic = userDetailsAnchorPane;
    }
}