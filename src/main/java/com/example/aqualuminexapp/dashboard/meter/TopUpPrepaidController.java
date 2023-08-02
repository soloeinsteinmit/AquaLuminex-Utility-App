package com.example.aqualuminexapp.dashboard.meter;

import com.example.aqualuminexapp.dashboard.WalletCardsController;
import com.example.aqualuminexapp.utils.ChangingScenes;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class TopUpPrepaidController implements Initializable {

    public static StackPane topUpStack;
    @FXML
    private MFXScrollPane topUpScrollPane;
    @FXML
    private AnchorPane topUpPrepaidAnchorPane;
    @FXML
    private Text availabelBalance;

    @FXML
    private MFXTextField topField;


    WalletCardsController walletCardsController = new WalletCardsController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WalletCardsController.loadWalletsCards(topUpScrollPane);
    }

    @FXML
    void backToMeterPage(MouseEvent event) {
        ChangingScenes.translateScene(MetersController.class, topUpStack, topUpPrepaidAnchorPane,
                "meters", 'b');
    }

    @FXML
    void topUpPrepaid(MouseEvent event) {
        //todo: write top up meter code here
    }

}