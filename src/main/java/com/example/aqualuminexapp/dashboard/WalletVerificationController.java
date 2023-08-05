package com.example.aqualuminexapp.dashboard;

import com.example.aqualuminexapp.AquaLuminexMain;
import com.example.aqualuminexapp.dashboard.wallets.WalletsController;
import com.example.aqualuminexapp.database_utils.LoginDataAccess;
import com.example.aqualuminexapp.database_utils.WalletsDataAccess;
import com.example.aqualuminexapp.utils.ChangingScenes;
import com.example.aqualuminexapp.utils.SendEmail;
import com.example.aqualuminexapp.utils.ShowToastMessage;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.converter.CharacterStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WalletVerificationController implements Initializable {
    public MFXButton verifyOTP;
    @FXML
    private GridPane otpFieldsGrid;
    @FXML
    private StackPane testStack;

    @FXML
    private AnchorPane verificationAnchorPane;
    public StringBuilder OTP = new StringBuilder();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int numOfOtpFields = 4;
        TextField[] otpFields = new TextField[numOfOtpFields];

        // Create and configure OTP text fields
        for (int i = 0; i < numOfOtpFields; i++) {

            otpFields[i] = new TextField();

            otpFields[i].setStyle("""
                -fx-min-height: 60;
                -fx-min-width: 60;
                -fx-max-height: 60;
                -fx-max-width: 60;
                -fx-text-alignment: center;
                -fx-alignment: center;
                -fx-background-color: #1b1d60;
                -fx-text-fill: #fff;
                -fx-font-family: 'Poppins Medium';
                -fx-font-weight: bold;
                -fx-font-size: 16px;
                """);

            otpFields[i].setPrefColumnCount(1); // Set the width of the text field to display one character at a time
            int finalI1 = i;
            otpFields[i].setTextFormatter(new TextFormatter<>(new CharacterStringConverter(), null, c -> {
                if (c.getControlNewText().length() > 1) {
                    return null; // Reject input if more than one character is entered
                } else {
                    String newChar = c.getText();
                    int otpLength = SendEmail.getOTP().length();
                    //int otpLength = matcher.length();

                    if (finalI1 < otpLength && newChar.equals(String.valueOf(SendEmail.getOTP().charAt(finalI1)))) {
                    //if (finalI1 < otpLength && newChar.equals(String.valueOf(matcher.charAt(finalI1)))) {
                        OTP.append(newChar);
                        System.out.println(OTP + "   otp");
                        System.out.println(SendEmail.getOTP());
                    }

                    if (OTP.length() > 4) {
                        OTP.delete(4, OTP.length()); // Enforce a maximum character length of 4
                    }

                    return c; // Accept input if it's a single character
                }
            })); // Restrict each field to accept only one character
            int finalI = i;
            otpFields[i].textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() == 1) {
                    // When the current text field is filled with one character, move focus to the next field
                    int currentIndex = GridPane.getColumnIndex(otpFields[finalI]);
                    if (currentIndex < numOfOtpFields - 1) {
                        otpFields[finalI + 1].requestFocus();

                    }
                } else if (newValue.isEmpty() && !oldValue.isEmpty()) {
                    // When the current text field is cleared (by pressing backspace), move focus to the previous field
                    int currentIndex = GridPane.getColumnIndex(otpFields[finalI]);
                    if (currentIndex > 0) {
                        otpFields[finalI - 1].requestFocus();
                    }
                }

            });
            // Add key event handler to each text field
            otpFields[i].addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                KeyCode keyCode = event.getCode();
                int currentIndex = GridPane.getColumnIndex(otpFields[finalI]);

                if (keyCode == KeyCode.RIGHT) {
                    // Move focus to the next field when the RIGHT arrow key is pressed
                    if (currentIndex < numOfOtpFields - 1) {
                        otpFields[finalI + 1].requestFocus();
                        event.consume();
                    }
                } else if (keyCode == KeyCode.LEFT) {
                    // Move focus to the previous field when the LEFT arrow key is pressed
                    if (currentIndex > 0) {
                        otpFields[finalI - 1].requestFocus();
                        event.consume();
                    }
                }
            });

            otpFieldsGrid.add(otpFields[i], i, 0);
        }

        // Focus the first text field when the application starts
        otpFields[0].requestFocus();
    }

    @FXML
    void verifyOtpCode(MouseEvent event) throws IOException {
        System.out.println("hello");
        System.out.println(OTP.toString());
        System.out.println(SendEmail.getOTP());

        int count = 0; // Initialize a counter to track the occurrences of characters from the matcher in the OTP
        // Loop through each character in the matcher
        for (int i = 0; i < SendEmail.getOTP().length(); i++) {
            char currentChar = SendEmail.getOTP().charAt(i); // Get the current character from the matcher

            // Count the occurrences of the current character in the OTP
            int occurrences = OTP.toString().length() - OTP.toString().replace(String.valueOf(currentChar), "").length();

            // If the occurrences of the current character are greater than or equal to 3, increment the count
            if (occurrences >= 3) {
                count++;
            }
        }

        // Check if at least 3 characters from the matcher have been found in the OTP
        if (count >= 3 || OTP.toString().equals(SendEmail.getOTP())) {
            ShowToastMessage.showToast(AquaLuminexMain.messageToastStage, "Wallet Verification " +
                            "Successful", 3
                    , 5, 3);

            WalletCardsController.walletInfo.add(3, LoginDataAccess.loggedInAccountID);
            // If OTP contains at least 3 characters from the matcher, perform some action (e.g., changing scenes)

            System.out.println(WalletCardsController.walletInfo);
            //loadCardContent();
            Thread sendWalletInfoThread = new Thread(() -> {
                synchronized (WalletCardsController.walletInfo) {
                    System.out.println(WalletCardsController.walletInfo + " inside thread");

                    WalletsDataAccess.insetWalletInfoIntoDB(
                            WalletCardsController.walletInfo.get(0),
                            WalletCardsController.walletInfo.get(1),
                            WalletCardsController.walletInfo.get(2),
                            WalletCardsController.walletInfo.get(3)
                    );

                    WalletsDataAccess.insertWalletBalance(
                            WalletCardsController.walletInfo.get(0),
                            "1000",
                            WalletCardsController.walletInfo.get(3)
                    );

                    System.out.println("passing data into db");
                    WalletCardsController.loadContent = true;
                    WalletCardsController.walletInfo.clear();
                }
            });


            ChangingScenes.translateScene(WalletsController.class, WalletsController.duplicateParentStack,
                    verificationAnchorPane, "wallets", 'b');

            sendWalletInfoThread.start();


        } else {
            //MessageToast.makeText(HelloApplication.toastMessageStage, "Wallet Verified", 30, 10, 10);
            // If OTP doesn't contain at least 3 characters from the matcher, display a toast message
            ShowToastMessage.showToast(AquaLuminexMain.messageToastStage, "Invalid OTP", 3
                    , 5, 3);
        }





    }

    @FXML
    void backToAddNewWallet(MouseEvent event) {
        ChangingScenes.translateScene(AddNewWalletController.class, WalletsController.duplicateParentStack,
                verificationAnchorPane, "add_new_wallet", 'b');
        WalletCardsController walletCardsController = new WalletCardsController();

    }


    public void loadCardContent() {

        //WalletCardsController.loadContent = true;



    }
}