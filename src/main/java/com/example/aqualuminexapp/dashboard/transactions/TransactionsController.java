package com.example.aqualuminexapp.dashboard.transactions;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {

    @FXML
    private Label totalPriceLabel;

    @FXML
    private VBox transactionsCardVbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Thread addNodeThread = new Thread(this::addNode2);
        addNodeThread.start();

    }

    private void addNode(){
        int nodesNumber = 15;
        try {
//            System.out.println("here1");
            for (int i = 0; i < CardsData.transactionsCardData().size(); i++) {
//                System.out.println(i);
                Node[] nodes = new Node[CardsData.transactionsCardData().size()];
                FXMLLoader[] loader = new FXMLLoader[CardsData.transactionsCardData().size()];
                loader[i] = new FXMLLoader(TransactionCardController.class.getResource("transaction_card.fxml"));

//                System.out.println(loader[i]);

                nodes[i] = loader[i].load();
                // Access the controller associated with the FXML file
                TransactionCardController controller = loader[i].getController();

                // Modify the content using the controller's methods/properties

                controller.getMeterNameLabel().setText("Hello Suckers");

                transactionsCardVbox.getChildren().add(nodes[i]);

                // for testing number nodes loaded
                /*int finalI = i;
                Thread myThread = new Thread(()-> System.out.println(nodes[finalI]));
                myThread.start();*/
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addNode2() {
        int i = 0;
        double sum = 0;

        Node[] nodes = new Node[CardsData.transactionsCardData().size()];
        FXMLLoader[] loader = new FXMLLoader[CardsData.transactionsCardData().size()];
        DecimalFormat df = new DecimalFormat("#.##");
        DecimalFormat formatPurchasedAmt = new DecimalFormat("0.00");

        for(Map.Entry<String, ArrayList<String>> eachCardInfo: CardsData.transactionsCardData().entrySet()){

            loader[i] = new FXMLLoader(TransactionCardController.class.getResource("transaction_card.fxml"));
            try {
                nodes[i] = loader[i].load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            // Access the controller associated with the FXML file
            TransactionCardController controller = loader[i].getController();

            // Modify the content using the controller's methods/properties
            controller.getMeterNameLabel().setText(eachCardInfo.getValue().get(0));
            controller.getDateLabel().setText(eachCardInfo.getValue().get(1));
            controller.getTimeLabel().setText(eachCardInfo.getValue().get(2));
            controller.getAqNumberLabel().setText(eachCardInfo.getKey());
            double formattedAmountPurchased = Double.parseDouble(eachCardInfo.getValue().get(3));
            controller.getAmountPurchasedLabel().setText(formatPurchasedAmt.format(formattedAmountPurchased));
            Image statusImage;
            Image meterTypeImage;

            if (eachCardInfo.getValue().get(4).equals("1")){
                statusImage = new Image("com/example/aqualuminexapp/images/correct.png");
                meterTypeImage = new Image("com/example/aqualuminexapp/images/electricity.png");

                controller.getStatusImgView().setImage(statusImage);
                controller.getMeterTypeImgView().setImage(meterTypeImage);
            }else {
                statusImage = new Image("com/example/aqualuminexapp/images/electricity_96px 2.png");
                controller.getStatusImgView().setImage(statusImage);
                meterTypeImage = new Image("com/example/aqualuminexapp/images/waterdroplet.png");
                controller.getMeterTypeImgView().setImage(meterTypeImage);
            }


            //controller.getMeterNameLabel().setText("Hello Suckers");
            sum += Double.parseDouble(controller.getAmountPurchasedLabel().getText());

            totalPriceLabel.setText(String.valueOf(df.format(sum)));
            transactionsCardVbox.getChildren().add(nodes[i]);

            if(i == CardsData.transactionsCardData().size() - 1){
                break;
            }else {
                i += 1;
            }


        }
    }
}