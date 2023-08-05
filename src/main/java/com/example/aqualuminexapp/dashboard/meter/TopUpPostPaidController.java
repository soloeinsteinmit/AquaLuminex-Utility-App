package com.example.aqualuminexapp.dashboard.meter;

import com.example.aqualuminexapp.dashboard.WalletCardsController;
import com.example.aqualuminexapp.utils.ChangingScenes;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TopUpPostPaidController implements Initializable {
    public static StackPane topUpStack;
    @FXML
    private MFXScrollPane topUpPostPaidScrollPane;
    @FXML
    private Label deptLabel;

    @FXML
    private MFXTextField payField;
    @FXML
    private AnchorPane topUpPostpaidAnchorPane;



    WalletCardsController walletCardsController = new WalletCardsController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WalletCardsController.scrollPane = topUpPostPaidScrollPane;


        WalletCardsController.loadWalletsCards(topUpPostPaidScrollPane);
    }

    @FXML
    void backToMeterPage(MouseEvent event) {
        ChangingScenes.translateScene(MetersController.class, topUpStack, topUpPostpaidAnchorPane,
                "meters", 'b');
    }

    @FXML
    void payDebt(MouseEvent event) {

    }
}