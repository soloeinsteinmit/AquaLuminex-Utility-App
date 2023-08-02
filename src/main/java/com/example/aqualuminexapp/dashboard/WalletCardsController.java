package com.example.aqualuminexapp.dashboard;

import com.example.aqualuminexapp.database_utils.WalletsDataAccess;
import com.example.aqualuminexapp.utils.ChangingScenes;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WalletCardsController implements Initializable {
    @FXML
    private MFXButton addMTNAcc;
    @FXML
    private MFXButton addTigoAcc;
    @FXML
    private MFXButton addVodaAcc;

    @FXML
    private Label aqBalanceCard;

    @FXML
    private Label mtnAccName;

    @FXML
    private Label mtnAccNumber;

    @FXML
    private MFXButton mtnDeleteBtn;

    @FXML
    private Label tigoAccName;

    @FXML
    private Label tigoAccNumber;

    @FXML
    private MFXButton tigoDeleteBtn;

    @FXML
    private Label vodaAccName;

    @FXML
    private Label vodaAccNumber;

    @FXML
    private MFXButton vodaDeleteBtn;

    @FXML
    private MFXButton vodaMTNAcc;


    @FXML
    private HBox walletsCardsHBox;
    WalletsDataAccess walletsDataAccess = new WalletsDataAccess();

    ArrayList<MFXButton> addWalletButtons = new ArrayList<>(3);
    ArrayList<MFXButton> deleteWalletButtons = new ArrayList<>(3);
    ArrayList<Label> walletNames = new ArrayList<>(4);

    ArrayList<Label> walletbalance = new ArrayList<>(4);

    public static AnchorPane walletCardsAnchorPane;
    public static StackPane walletCardsStackPane;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialize();

    }
    //UF-477523

    public void initialize()  {
        addWallet();
        deleteWallet();

        try {
            aqBalanceCard.setText("GHC "+ walletsDataAccess.retrieveAQBalance() +".00");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadWalletsCards(MFXScrollPane scrollPane){

        try {
            FXMLLoader loader = new FXMLLoader(WalletCardsController.class.getResource("wallet_cards.fxml"));
            Parent content = loader.load();

            WalletCardsController controller = loader.getController();
            if (scrollPane.getId().equals("topUpScrollPane") || scrollPane.getId().equals("topUpPostPaidScrollPane")){
                System.out.println(scrollPane.getId() + " id here");
                    controller.getAddMTNAcc().setVisible(false);
                    controller.getAddTigoAcc().setVisible(false);
                    controller.getAddVodaAcc().setVisible(false);
            }else {
                System.out.println(scrollPane.getId() + " id here else");
                controller.getAddMTNAcc().setVisible(true);
                controller.getAddTigoAcc().setVisible(true);
                controller.getAddVodaAcc().setVisible(true);
            }

            controller.initialize(); // Call any initialization method in the controller if needed

            scrollPane.setContent(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void addWallet(){
        addButtonsToArray();
        for (int i = 0; i < addWalletButtons.size(); i++){
            int finalI = i;
            addWalletButtons.get(i).setOnMouseClicked(mouseEvent -> {
                switch (addWalletButtons.get(finalI).getId()) {
                    case "addMTNAcc" -> {
                        ChangingScenes.translateScene(AddNewWalletController.class, walletCardsStackPane,
                                walletCardsAnchorPane, "add_new_wallet", 'f');

                        deleteWalletButtons.get(finalI).setVisible(true);
                        addWalletButtons.get(finalI).setVisible(false);
                        System.out.println("addMTNAcc");
                    }
                    case "addTigoAcc" -> {
                        ChangingScenes.translateScene(AddNewWalletController.class, walletCardsStackPane,
                                walletCardsAnchorPane, "add_new_wallet", 'f');

                        deleteWalletButtons.get(finalI).setVisible(true);
                        addWalletButtons.get(finalI).setVisible(false);
                        System.out.println("addTigoAcc");
                    }
                    case "addVodaAcc" -> {
                        ChangingScenes.translateScene(AddNewWalletController.class, walletCardsStackPane,
                                walletCardsAnchorPane, "add_new_wallet", 'f');

                        deleteWalletButtons.get(finalI).setVisible(true);
                        addWalletButtons.get(finalI).setVisible(false);
                        System.out.println("addVodaAcc");
                    }
                }
            });
        }
    }
    private void deleteWallet(){
        addDeleteButtonsToArray();
        for (int i = 0; i < deleteWalletButtons.size(); i++){
            int finalI = i;
            deleteWalletButtons.get(i).setOnMouseClicked(mouseEvent -> {
                switch (deleteWalletButtons.get(finalI).getId()) {
                    case "mtnDeleteBtn" -> {
                        deleteWalletButtons.get(finalI).setVisible(false);
                        addWalletButtons.get(finalI).setVisible(true);
                        System.out.println("mtnDeleteBtn");
                    }
                    case "tigoDeleteBtn" -> {
                        deleteWalletButtons.get(finalI).setVisible(false);
                        addWalletButtons.get(finalI).setVisible(true);
                        System.out.println("tigoDeleteBtn");
                    }
                    case "vodaDeleteBtn" -> {
                        deleteWalletButtons.get(finalI).setVisible(false);
                        addWalletButtons.get(finalI).setVisible(true);
                        System.out.println("vodaDeleteBtn");
                    }
                }
            });
        }
    }


    private void addButtonsToArray(){
        addWalletButtons.add(0, addMTNAcc); //mtn
        addWalletButtons.add(1, addTigoAcc);//tigo
        addWalletButtons.add(2, addVodaAcc); //voda
    }
    private void addDeleteButtonsToArray(){
        deleteWalletButtons.add(0, mtnDeleteBtn);//mtn
        deleteWalletButtons.add(1, tigoDeleteBtn);//tigo
        deleteWalletButtons.add(2, vodaDeleteBtn);//voda
    }



    public HBox getWalletsCardsHBox() {
        return walletsCardsHBox;
    }

    public MFXButton getAddMTNAcc() {
        return addMTNAcc;
    }

    public MFXButton getAddTigoAcc() {
        return addTigoAcc;
    }

    public MFXButton getAddVodaAcc() {
        return addVodaAcc;
    }
}