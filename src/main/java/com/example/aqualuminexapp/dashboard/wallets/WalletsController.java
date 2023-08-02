package com.example.aqualuminexapp.dashboard.wallets;

import com.example.aqualuminexapp.dashboard.WalletCardsController;
import com.example.aqualuminexapp.database_utils.WalletsDataAccess;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;


public class WalletsController implements Initializable {
    @FXML
    private Label aqBalanceLabel;

    @FXML
    private MFXButton depositBtn;

    @FXML
    private MFXScrollPane walletsScrollPane;
    @FXML
    private StackPane parentWalletStack;
    @FXML
    private AnchorPane walletAnchor;
    WalletsDataAccess walletsDataAccess = new WalletsDataAccess();
    DecimalFormat formatAQBalance = new DecimalFormat("0.00");
    public static StackPane duplicateParentStack;
    WalletCardsController walletCardsController = new WalletCardsController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        duplicateParentStack = parentWalletStack;

        WalletCardsController.loadWalletsCards(walletsScrollPane);
        WalletCardsController.walletCardsStackPane = parentWalletStack;
        WalletCardsController.walletCardsAnchorPane = walletAnchor;


        //load aq balance to label
        try {
            String aqBalance = String.valueOf(walletsDataAccess.retrieveAQBalance());
            //aqBalanceLabel.setText(formatAQBalance.format(aqBalance));
            aqBalanceLabel.setText(aqBalance+".00");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}