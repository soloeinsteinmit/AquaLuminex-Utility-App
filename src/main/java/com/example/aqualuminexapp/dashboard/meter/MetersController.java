package com.example.aqualuminexapp.dashboard.meter;

import com.example.aqualuminexapp.utils.ChangingScenes;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MetersController implements Initializable {

    @FXML
    private MFXButton deleteMeter_1_btn;

    @FXML
    private MFXButton deleteMeter_2_btn;

    @FXML
    private Label meterIdLabel_1;

    @FXML
    private Label meterIdLabel_2;

    @FXML
    private Label meterNameLabel_1;

    @FXML
    private Label meterNameLabel_2;

    @FXML
    private AnchorPane metersAnchorPane;

    @FXML
    private StackPane metersParentStackPane;

    @FXML
    private ImageView postpaidMeter1_img;

    @FXML
    private ImageView prepaidMeter1_img;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TopUpPrepaidController.topUpStack = metersParentStackPane;
        TopUpPostPaidController.topUpStack = metersParentStackPane;

    }

    @FXML
    void payBill(MouseEvent event) {

    }

    @FXML
    void topUpPrepaid(MouseEvent event) {
        ChangingScenes.translateScene(TopUpPrepaidController.class, metersParentStackPane, metersAnchorPane,
                "top_up_prepaid", 'f');
    }
}