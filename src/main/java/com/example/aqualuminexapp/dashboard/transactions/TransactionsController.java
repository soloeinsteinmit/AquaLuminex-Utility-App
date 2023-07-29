package com.example.aqualuminexapp.dashboard.transactions;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {

    @FXML
    private Label totalPriceLabel;

    @FXML
    private VBox transactionsCardVbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Thread addNodeThread = new Thread(this::addNode);
        addNodeThread.start();
    }

    private void addNode(){
        int nodesNumber = 15;
        try {
//            System.out.println("here1");
            for (int i = 0; i < nodesNumber; i++) {
//                System.out.println(i);
                Node[] nodes = new Node[nodesNumber];
                FXMLLoader[] loader = new FXMLLoader[nodesNumber];
                loader[i] = new FXMLLoader(TransactionCardController.class.getResource("transaction_card.fxml"));
//                System.out.println(loader[i]);
                nodes[i] = loader[i].load();
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
}