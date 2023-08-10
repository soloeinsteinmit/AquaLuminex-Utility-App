package com.example.aqualuminexapp.dashboard;

import com.example.aqualuminexapp.LoginController;
import com.example.aqualuminexapp.database_utils.LoginDataAccess;
import com.example.aqualuminexapp.utils.AppSettings;
import com.example.aqualuminexapp.utils.ChangingScenes;
import com.example.aqualuminexapp.utils.ShowToastMessage;
import io.github.gleidson28.GNAvatarView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Label emailLabel;

    @FXML
    private StackPane dashboardRootStackPane;

    @FXML
    private GNAvatarView profileImage;

    @FXML
    private Label usernameLabel;



    AppSettings appSettings = AppSettings.getAppSettings();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ShowToastMessage.parentToastStack = dashboardRootStackPane;
        /*
         * if *loadFromConfig* returns true, read data from config.json
         * otherwise retrieve data from database
         * */
        if (LoginController.loadFromConfig){
            usernameLabel.setText(appSettings.getUserName());
            emailLabel.setText(appSettings.getEmail());
            System.out.println(appSettings.getProfileImagePath() + " = solooo");
            Image image = new Image(appSettings.getProfileImagePath());
            profileImage.setImage(image);
        }else {
            usernameLabel.setText(LoginDataAccess.loggedInUserDetails.get(0)); // username
            emailLabel.setText(LoginDataAccess.loggedInUserDetails.get(1)); // email
            Image image = new Image(LoginDataAccess.loggedInUserDetails.get(2)); // image path
            profileImage.setImage(image);
        }
    }

    @FXML
    void logout(MouseEvent event) {
        ChangingScenes.changeWindow(event, LoginController.class, "login");
    }
}