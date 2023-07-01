package com.example.aqualuminexapp;

import com.example.aqualuminexapp.register.RegisterMainController;
import com.example.aqualuminexapp.utils.ChangingScenes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public static StackPane stackContainer;

    @FXML
    private AnchorPane loginAnchorPane;

    @FXML
    private StackPane parentStackContainer;

    @FXML
    private Label registerButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stackContainer = parentStackContainer;
    }


    /**
     * This method takes you to the register user scene
     */
    @FXML
    void registerUser(MouseEvent event) {
        ChangingScenes.translateScene(RegisterMainController.class, parentStackContainer, loginAnchorPane,
                "register-main", 'f');
    }

}