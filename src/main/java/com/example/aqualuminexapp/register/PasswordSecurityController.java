package com.example.aqualuminexapp.register;

import com.example.aqualuminexapp.database_utils.RegisterDataAccess;
import com.example.aqualuminexapp.utils.AppSettings;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class PasswordSecurityController implements Initializable {
    private static final int WEAK_THRESHOLD = 6;
    private static final int MEDIUM_THRESHOLD = 8;
    public static AnchorPane pA;
    @FXML
    private AnchorPane passwordAnchorPane;
    @FXML
    private MFXButton createPasswordButton;

    @FXML
    private MFXPasswordField newPasswordField;

    @FXML
    private MFXPasswordField repeatPasswordField;
    @FXML
    private Label passwordMismatchLabel;

    @FXML
    private Label strengthPasswordNew;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pA = passwordAnchorPane;
        strengthPasswordNew.setText("");
        passwordMismatchLabel.setText("");
        System.out.println(RegisterMainController.isPasswordCreated + " !clicked");

        // Register a listener on the textProperty of the PasswordField
        newPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Calculate the password strength
            String strength = calculatePasswordStrength(newValue);

            if (newValue.length() == 0)
                strengthPasswordNew.setText("");
            else
                // Update the strengthLabel with the result
                strengthPasswordNew.setText("Password Strength: " + strength);

            //set createPasswordButton disable if strength returns 'weak'
            createPasswordButton.setDisable(strength.equalsIgnoreCase("weak"));

            if (repeatPasswordField.getText().length()!=0 && !Objects.equals(repeatPasswordField.getText(),newValue)){
                passwordMismatchLabel.setStyle("-fx-text-fill: red;");
                passwordMismatchLabel.setText("Password Mismatch");
            }else {
                passwordMismatchLabel.setText("");
            }


        });

        //check if there is a password mismatch
        checkPasswordMismatch();


    }

    // Method to calculate the password strength based on length
    private String calculatePasswordStrength(String password) {

        if (password.length() < WEAK_THRESHOLD) {
            strengthPasswordNew.setStyle("-fx-text-fill: red;");
            return "Weak";
        } else if (password.length() < MEDIUM_THRESHOLD) {
            strengthPasswordNew.setStyle("-fx-text-fill: #1670ad;");
            return "Medium";
        } else {
            strengthPasswordNew.setStyle("-fx-text-fill: #2baf2b;");
            return "Strong";
        }




    }

    private void checkPasswordMismatch(){
        //check if there is a password mismatch
        repeatPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {

            if((newPasswordField.getText().length()!=0) && (!Objects.equals(newValue, newPasswordField.getText()))){
                // Update the password mismatch with the result
                passwordMismatchLabel.setStyle("-fx-text-fill: red;");
                passwordMismatchLabel.setText("Password Mismatch");
            }else {
                passwordMismatchLabel.setText("");
            }

        });
    }

    @FXML
    public void createPassword() throws SQLException {
        //RegisterMainController.password = newPasswordField.getText();

        String password = newPasswordField.getText();
        System.out.println(newPasswordField.getText() + " -/ before hash");

        RegisterMainController.appSettings.setPassword(password);
        AppSettings.writeAppSettingsToConfig(RegisterMainController.appSettings);


        /*
        * asserts isPasswordCreated to true for Done button to be disabled
        * in the register controller class
        * */
        RegisterMainController.isPasswordCreated = true;
        RegisterMainController.next_button.setDisable(false);
        System.out.println(RegisterMainController.isPasswordCreated + " clicked");
        //TODO: storing password into database
        Thread dbDataThread = new Thread(()->{
            try {
                RegisterDataAccess.insertAccountId();
                RegisterDataAccess.insertPassword();
                RegisterDataAccess.insertPersonalDetails();

                System.out.println("Inserting Data");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        dbDataThread.start();


        System.out.println("Inserting Data");

    }
}