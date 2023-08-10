package com.example.aqualuminexapp.dashboard;

import com.example.aqualuminexapp.database_utils.WalletsDataAccess;
import com.example.aqualuminexapp.utils.ChangingScenes;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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

    // Define observable properties for account names and numbers
    private final StringProperty mtnAccNameProperty = new SimpleStringProperty();
    private final StringProperty mtnAccNumberProperty = new SimpleStringProperty();

    private final StringProperty vodaAccNameProperty = new SimpleStringProperty();
    private final StringProperty vodaAccNumberProperty = new SimpleStringProperty();

    private final StringProperty tigoAccNameProperty = new SimpleStringProperty();
    private final StringProperty tigoAccNumberProperty = new SimpleStringProperty();

    WalletsDataAccess walletsDataAccess = null;

    ArrayList<MFXButton> addWalletButtons = new ArrayList<>(3);
    ArrayList<MFXButton> deleteWalletButtons = new ArrayList<>(3);
    ArrayList<Label> walletNames = new ArrayList<>(4);

    ArrayList<Label> walletbalance = new ArrayList<>(4);

    public static AnchorPane walletCardsAnchorPane;
    public static StackPane walletCardsStackPane;

    /**
     * index 0 - walletId
     * index 1 - walletName
     * index 2 - walletNumber
     * index 3 - walletId
     * */
    public static volatile  ArrayList<String> walletInfo = new ArrayList<>(4);
    //[tigo, Kofi T, 027 222 1234, UF-477523]


    public static volatile boolean loadContent = false;

    public static boolean containsValue(String targetValue, String[] array) {
        return Arrays.asList(array).contains(targetValue);
    }
    public static MFXScrollPane scrollPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        walletsDataAccess = new WalletsDataAccess();
        initializeData();

        //walletsDataAccess.mtnCardInfo();
        //walletsDataAccess.tigoCardInfo();
        //walletsDataAccess.vodaCardInfo();


        //initialize();




    }

    public volatile boolean setContent = false;
    public void initializeData(){
        walletsDataAccess.mtnCardInfo();
        walletsDataAccess.tigoCardInfo();
        walletsDataAccess.vodaCardInfo();
        initializeDataIntoArrays();

        System.out.println(Arrays.toString(WalletsDataAccess.mtnCardInfo) + " -- mtn");
        System.out.println(Arrays.toString(WalletsDataAccess.tigoCardInfo) + " -- tigo");
        System.out.println(Arrays.toString(WalletsDataAccess.vodaCardInfo) + " -- voda");

        if (WalletsDataAccess.mtnCardInfo[0] == null) {
            mtnAccName.setText("Unregistered");
            mtnAccNumber.setText("000 000 0000");

            addWalletButtons.get(0).setVisible(true); // mtn
            deleteWalletButtons.get(0).setVisible(false); // mtn
        } else {
            mtnAccName.setText(WalletsDataAccess.mtnCardInfo[0]);
            mtnAccNumber.setText(WalletsDataAccess.mtnCardInfo[1]);
            //addMTNAcc.setVisible(false);
            if (scrollPane.getId().equals("topUpScrollPane") || scrollPane.getId().equals("topUpPostPaidScrollPane")){
                //System.out.println(scrollPane.getId() + " id here");
                deleteWalletButtons.get(0).setVisible(false); // mtn
            }else {
                addWalletButtons.get(0).setVisible(false); // mtn
                deleteWalletButtons.get(0).setVisible(true); // mtn
            }

        }
        if (WalletsDataAccess.tigoCardInfo[0] == null) {
            tigoAccName.setText("Unregistered");
            tigoAccNumber.setText("000 000 0000");
            addWalletButtons.get(1).setVisible(true); // tigo
            deleteWalletButtons.get(1).setVisible(false); // tigo
        } else {
            tigoAccName.setText(WalletsDataAccess.tigoCardInfo[0]);
            tigoAccNumber.setText(WalletsDataAccess.tigoCardInfo[1]);
            if (scrollPane.getId().equals("topUpScrollPane") || scrollPane.getId().equals("topUpPostPaidScrollPane")){
                //System.out.println(scrollPane.getId() + " id here");
                deleteWalletButtons.get(1).setVisible(false); // tigo
            }else {
                addWalletButtons.get(1).setVisible(false); // tigo
                deleteWalletButtons.get(1).setVisible(true); // tigo
            }
        }
        if (WalletsDataAccess.vodaCardInfo[0] == null) {
            vodaAccName.setText("Unregistered");
            vodaAccNumber.setText("000 000 0000");
            addWalletButtons.get(2).setVisible(true); // voda
            deleteWalletButtons.get(2).setVisible(false); // voda
        } else {
            vodaAccName.setText(WalletsDataAccess.vodaCardInfo[0]);
            vodaAccNumber.setText(WalletsDataAccess.vodaCardInfo[1]);
            if (scrollPane.getId().equals("topUpScrollPane") || scrollPane.getId().equals("topUpPostPaidScrollPane")){
                //System.out.println(scrollPane.getId() + " id here");
                deleteWalletButtons.get(2).setVisible(false); // voda
            }else {
                addWalletButtons.get(2).setVisible(false); // voda
                deleteWalletButtons.get(2).setVisible(true); // voda
            }
        }
    }
    //UF-477523


    public void initializeDataIntoArrays(){
        addWallet();
        deleteWallet();
    }
    public void initialize() {

            initializeDataIntoArrays();

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
                //System.out.println(scrollPane.getId() + " id here else");


                controller.getAddMTNAcc().setVisible(true);
                controller.getAddTigoAcc().setVisible(true);
                controller.getAddVodaAcc().setVisible(true);
            }

            //controller.initializeWalletData(scrollPane);

            //isDataLoaded = true;
            controller.initialize(); // Call any initialization method in the controller if needed


            scrollPane.setContent(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String cardName;
    private void addWallet(){
        addButtonsToArray();
        for (int i = 0; i < addWalletButtons.size(); i++){
            int finalI = i;
            addWalletButtons.get(i).setOnMouseClicked(mouseEvent -> {
                switch (addWalletButtons.get(finalI).getId()) {
                    case "addMTNAcc" -> {
                        ChangingScenes.translateScene(AddNewWalletController.class, walletCardsStackPane,
                                walletCardsAnchorPane, "add_new_wallet", 'f');


                        walletInfo.add(0, "mtn");
                        cardName = "addMTNAcc";

                        //deleteWalletButtons.get(finalI).setVisible(true);
                        //addWalletButtons.get(finalI).setVisible(false);
                        System.out.println(cardName);
                    }
                    case "addTigoAcc" -> {
                        ChangingScenes.translateScene(AddNewWalletController.class, walletCardsStackPane,
                                walletCardsAnchorPane, "add_new_wallet", 'f');

                        walletInfo.add(0, "tigo");

                        //deleteWalletButtons.get(finalI).setVisible(true);
                        //addWalletButtons.get(finalI).setVisible(false);
                        cardName = "addTigoAcc";
                        System.out.println(cardName);
                    }
                    case "addVodaAcc" -> {
                        ChangingScenes.translateScene(AddNewWalletController.class, walletCardsStackPane,
                                walletCardsAnchorPane, "add_new_wallet", 'f');

                        walletInfo.add(0, "voda");

                        //deleteWalletButtons.get(finalI).setVisible(true);
                        //addWalletButtons.get(finalI).setVisible(false);
                        cardName = "addVodaAcc";
                        System.out.println(cardName);
                    }

                }

            });
        }
        System.out.println(walletInfo);
    }

    private void deleteWallet(){
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Wallet Deletion Confirmation");
        confirmationAlert.setHeaderText("Are you sure you want to delete this wallet?");
        confirmationAlert.setContentText("Click OK to delete wallet or Cancel to go close alert");


        deleteButtonsToArray();
        for (int i = 0; i < deleteWalletButtons.size(); i++){
            int finalI = i;
            deleteWalletButtons.get(i).setOnMouseClicked(mouseEvent -> {
                switch (deleteWalletButtons.get(finalI).getId()) {
                    case "mtnDeleteBtn" -> {
                        confirmationAlert.showAndWait().ifPresent(reponse ->{
                            if (reponse == ButtonType.OK){
                                Thread deleteAccountThread = new Thread(() ->{
                                    walletsDataAccess.deleteWalletAccount(mtnAccName.getText());
                                    walletsDataAccess.deletWalletBalance("mtn");
                                });
                                mtnAccName.setText("Unregistered");
                                mtnAccNumber.setText("000 000 0000");
                                deleteAccountThread.start();

                            }else if (reponse == ButtonType.CANCEL){
                                confirmationAlert.close();
                            }
                        });

                        deleteWalletButtons.get(finalI).setVisible(false);
                        addWalletButtons.get(finalI).setVisible(true);
                        System.out.println("mtnDeleteBtn");
                    }
                    case "tigoDeleteBtn" -> {
                        confirmationAlert.showAndWait().ifPresent(reponse ->{
                            if (reponse == ButtonType.OK){
                                Thread deleteAccountThread = new Thread(() ->{
                                    walletsDataAccess.deleteWalletAccount(tigoAccName.getText());
                                    walletsDataAccess.deletWalletBalance("tigo");
                                });
                                tigoAccName.setText("Unregistered");
                                tigoAccNumber.setText("000 000 0000");
                                deleteAccountThread.start();

                            }else if (reponse == ButtonType.CANCEL){
                                confirmationAlert.close();
                            }
                        });
                        deleteWalletButtons.get(finalI).setVisible(false);
                        addWalletButtons.get(finalI).setVisible(true);
                        System.out.println("tigoDeleteBtn");
                    }
                    case "vodaDeleteBtn" -> {
                        confirmationAlert.showAndWait().ifPresent(reponse ->{
                            if (reponse == ButtonType.OK){
                                Thread deleteAccountThread = new Thread(() ->{
                                    walletsDataAccess.deleteWalletAccount(vodaAccName.getText());
                                    walletsDataAccess.deletWalletBalance("voda");
                                });
                                vodaAccName.setText("Unregistered");
                                vodaAccNumber.setText("000 000 0000");
                                deleteAccountThread.start();

                            }else if (reponse == ButtonType.CANCEL){
                                confirmationAlert.close();
                            }
                        });
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
        addWalletButtons.add(1, addTigoAcc); //tigo
        addWalletButtons.add(2, addVodaAcc); //voda
    }

    private void deleteButtonsToArray(){
        deleteWalletButtons.add(0, mtnDeleteBtn); //mtn
        deleteWalletButtons.add(1, tigoDeleteBtn); //tigo
        deleteWalletButtons.add(2, vodaDeleteBtn); //voda
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

    public Label getMtnAccName() {
        return mtnAccName;
    }

    public Label getMtnAccNumber() {
        return mtnAccNumber;
    }

    public Label getTigoAccName() {
        return tigoAccName;
    }

    public Label getTigoAccNumber() {
        return tigoAccNumber;
    }

    public Label getVodaAccName() {
        return vodaAccName;
    }

    public Label getVodaAccNumber() {
        return vodaAccNumber;
    }

    public MFXButton getMtnDeleteBtn() {
        return mtnDeleteBtn;
    }

    public MFXButton getTigoDeleteBtn() {
        return tigoDeleteBtn;
    }

    public MFXButton getVodaDeleteBtn() {
        return vodaDeleteBtn;
    }

    public StringProperty mtnAccNameProperty() {
        return mtnAccNameProperty;
    }

    public StringProperty mtnAccNumberProperty() {
        return mtnAccNumberProperty;
    }

    public StringProperty vodaAccNameProperty() {
        return vodaAccNameProperty;
    }

    public StringProperty vodaAccNumberProperty() {
        return vodaAccNumberProperty;
    }


    public StringProperty tigoAccNameProperty() {
        return tigoAccNameProperty;
    }

    public StringProperty tigoAccNumberProperty() {
        return tigoAccNumberProperty;
    }
}