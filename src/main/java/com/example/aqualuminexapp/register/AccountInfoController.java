package com.example.aqualuminexapp.register;

import com.example.aqualuminexapp.utils.SendEmail;
import com.example.aqualuminexapp.utils.TimerClass;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class AccountInfoController implements Initializable {
    private static final String ACCOUNT_ID_PATTERN = "[A-Z]{2}-\\d{6}"; // req exp we are using
    private static final int MAX_INPUT = 9;
    public static AnchorPane staticAccountInfoAnchorPane;
    public static Label staticTimerLabel;
    @FXML
    private AnchorPane accountInfoAnchorPane;

    @FXML
    private MFXTextField accountIdTextField;

    @FXML
    private MFXButton generateIdBtn;

    @FXML
    private Label timerLabel;
    @FXML
    private ImageView errorImg;

    @FXML
    private Label labelErrorMsg;

    public static String userName;
    public static String emailAddress;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        staticAccountInfoAnchorPane = accountInfoAnchorPane;
        staticTimerLabel = timerLabel;
        initializeAccountIdValidation();
        labelErrorMsg.setText("");

        RegisterMainController.accountIdField = accountIdTextField;
    }

    @FXML
    void generateId(MouseEvent event) throws Exception {
        TimerClass setTimer = new TimerClass(timerLabel, generateIdBtn);

        Thread timerThread = new Thread(setTimer::createTimeline);
        timerThread.start();


        Thread sendEmailThread = new Thread(() -> {
            try {
//                SendEmail.sendMail("jadgogovi@gmail.com");
                SendEmail.sendMail(emailAddress);
                System.out.println("account id email = " + emailAddress);
                System.out.println("username = " + userName);
//                SendEmail.sendMail("einsteinmit100@gmail.com");


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        sendEmailThread.start();
        generateIdBtn.setDisable(true);
        RegisterMainController.nextBtn.setDisable(true);
    }

    private void initializeAccountIdValidation() {
        UnaryOperator<TextFormatter.Change> accountIdRegExpFilter = change -> {
            String newText = change.getControlNewText(); // Gets the complete new text which will be used on the control after this change.
            String textUsed = change.getText(); // User typed or OS pasted text

            //System.out.println("\nTextBox 1 - Control textUsed : " + textUsed); // Output TextField User and OS
            // Pasted Input
            //System.out.println("TextBox 1 - Control newText  : " + newText); // Output Control Text

            labelErrorMsg.setText(""); // Reset Error Label

            // Check for maximum input
            if (newText.length() > MAX_INPUT) {
                labelErrorMsg.setText("ERROR - Exceeds Max Length " + MAX_INPUT);
                return null; // don't allow char input as invalid

            }

            if (newText.length() == 8 && !newText.contains("-")) {
                labelErrorMsg.setText("ERROR - ID does not contain '-'.");
                errorImg.setVisible(true);
            } else if (newText.length() == 2) {
                labelErrorMsg.setText("Don't forget to add '-'");
                labelErrorMsg.setStyle("-fx-text-fill: #6F4EB9");
            }
            // colour the TextField Pink while total input for the email input is invalid
            if (newText.matches(ACCOUNT_ID_PATTERN) && newText.equals(SendEmail.getID())) {
                System.out.println(SendEmail.getID() + " here at account info");
                accountIdTextField.setStyle("-fx-border-color: lightgray;");
                errorImg.setVisible(false);
                RegisterMainController.nextBtn.setDisable(false);
            } else if (!newText.equals(SendEmail.getID())) {
                generateIdBtn.setDisable(true);
            } else {

                accountIdTextField.setStyle("-fx-border-color: #EF6E6B;");
                errorImg.setVisible(true);
                if (newText.equals("")) {
                    accountIdTextField.setStyle("-fx-border-color: lightgray;");
                    errorImg.setVisible(false);
                    //generateIdBtn.setDisable(true);
                    RegisterMainController.nextBtn.setDisable(true);
                }
            }

            return change;
        };

        TextFormatter<String> accountIdFormatter = new TextFormatter<>(accountIdRegExpFilter);
        accountIdTextField.setTextFormatter(accountIdFormatter);
    }


}