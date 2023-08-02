package com.example.aqualuminexapp.dashboard;

import com.example.aqualuminexapp.dashboard.wallets.WalletsController;
import com.example.aqualuminexapp.utils.ChangingScenes;
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
    }

    @FXML
    void continueToVerification(MouseEvent event) {
        ChangingScenes.translateScene(WalletVerificationController.class, WalletsController.duplicateParentStack,
                newWalletAnchorPane, "wallet_verification", 'f');

    }
}