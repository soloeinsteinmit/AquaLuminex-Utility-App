package com.example.aqualuminexapp.dashboard;

import com.example.aqualuminexapp.dashboard.wallets.WalletsController;
import com.example.aqualuminexapp.database_utils.LoginDataAccess;
import com.example.aqualuminexapp.utils.ChangingScenes;
import com.example.aqualuminexapp.utils.SendEmail;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewWalletController implements Initializable {

    @FXML
    private MFXTextField walletNameTextField;

    @FXML
    private MFXTextField walletNumberTextField;
    @FXML
    private AnchorPane newWalletAnchorPane;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    void backToWallet(MouseEvent event) {
        ChangingScenes.translateScene(WalletsController.class, WalletsController.duplicateParentStack,
                newWalletAnchorPane, "wallets", 'b');

        WalletCardsController.walletInfo.clear();
        WalletCardsController walletCardsController = new WalletCardsController();
        //walletCardsController.initializeWalletData();
    }

    @FXML
    void continueToVerification(MouseEvent event) {
        String wName = walletNameTextField.getText();
        String wNumber = walletNumberTextField.getText();

        WalletCardsController.walletInfo.add(1, wName);
        WalletCardsController.walletInfo.add(2, wNumber);
        SendEmail.verifyWallet = true;

        Thread sendEmailThread = new Thread(() -> {
            try {
//                SendEmail.sendMail("jadgogovi@gmail.com");
                String emailAddress = LoginDataAccess.loggedInUserEmail;
                SendEmail.sendMail(emailAddress);
                System.out.println("account id email = " + emailAddress);
                System.out.println("username = " + WalletCardsController.walletInfo.get(1));
//                SendEmail.sendMail("einsteinmit100@gmail.com");


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        sendEmailThread.start();

        ChangingScenes.translateScene(WalletVerificationController.class, WalletsController.duplicateParentStack,
                newWalletAnchorPane, "wallet_verification", 'f');


        //System.out.println(WalletCardsController.walletInfo);
    }
}