package com.example.aqualuminexapp.dashboard.transactions;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class TransactionCardController implements Initializable {

    @FXML
    private Label amountPurchasedLabel;

    @FXML
    private Label aqNumberLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label meterNameLabel;

    @FXML
    private ImageView meterTypeImgView;

    @FXML
    private ImageView statusImgView;

    @FXML
    private Label timeLabel;

    @FXML
    private AnchorPane transactionCard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize date time
        //TODO: this method will be used somewhere appropriate... this is for testing the code
//        Thread getDateTimeThread = new Thread(()-> getDateTimeLabels(dateLabel, timeLabel));
        getDateTimeLabels(dateLabel, timeLabel);
    }


    private void getDateTimeLabels(Label dateLabel, Label timeLabel) {
        Thread getDateTimeThread = new Thread(()->{
            // Get current date and time
            Date currentDate = new Date();

            // Format date as "January 04 2023"
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd yyyy");
            String formattedDate = dateFormat.format(currentDate);

            // Format time as "7:32 AM"
            SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
            String formattedTime = timeFormat.format(currentDate);

            // Update labels
            dateLabel.setText(formattedDate);
            timeLabel.setText(formattedTime);
        });
        getDateTimeThread.start();
    }
}