package com.example.aqualuminexapp;

import com.example.aqualuminexapp.dashboard.DashboardController;
import com.example.aqualuminexapp.database_utils.LoginDataAccess;
import com.example.aqualuminexapp.register.RegisterMainController;
import com.example.aqualuminexapp.utils.AppSettings;
import com.example.aqualuminexapp.utils.ChangingScenes;
import com.example.aqualuminexapp.utils.ShowToastMessage;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    //public static StackPane stackContainer;
    @FXML
    private MFXTextField accountIdTextField;

    @FXML
    private AnchorPane loginAnchorPane;

    @FXML
    private StackPane parentStackContainer;

    @FXML
    private MFXTextField passwordField;

    @FXML
    private Label registerButton;
    AppSettings appSettings;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        stackContainer = parentStackContainer;
        RegisterMainController.parentStack = parentStackContainer;
        ShowToastMessage.parentToastStack = parentStackContainer;

        appSettings = AppSettings.getAppSettings();

        passwordField.setText("kofi.t");
        accountIdTextField.setText("UF-477523");
    }


    /**
     * This method takes you to the register user scene
     */
    @FXML
    void registerUser(MouseEvent event) {
        ChangingScenes.translateScene(RegisterMainController.class, parentStackContainer, loginAnchorPane,
                "register-main", 'f');
    }
    public static boolean loadFromConfig;
    @FXML
    void loginUser(MouseEvent event) throws SQLException {
        String enteredPassword = passwordField.getText();
        String accId = accountIdTextField.getText();
        if (appSettings.getAccount_id().equals(accId)){
            if (appSettings.getPassword().equals(DigestUtils.sha256Hex(enteredPassword))){
                loadFromConfig = true;
                LoginDataAccess.getLoggedUserId(accId); // gets account id and stores it in loggedAccountID
                ChangingScenes.changeWindow(event, DashboardController.class, "dashboard");
                ShowToastMessage.showToast(AquaLuminexMain.messageToastStage, "Login Successful", 3, 4, 3);
            }
        }
        else if (LoginDataAccess.checkLoginCredential(accountIdTextField.getText(), DigestUtils.sha256Hex(enteredPassword))){

            ChangingScenes.changeWindow(event, DashboardController.class, "dashboard");
            ShowToastMessage.showToast(AquaLuminexMain.messageToastStage, "Login Successful", 3, 4, 3);

        }else{
            //System.err.println("Invalid user credential");
            if (accId.isEmpty() || enteredPassword.isEmpty()){

                ShowToastMessage.showToast(AquaLuminexMain.messageToastStage, "Fill in all the forms", 3, 4, 3);
            }


        }



    }

}