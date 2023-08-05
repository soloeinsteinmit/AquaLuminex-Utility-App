package com.example.aqualuminexapp.dashboard;

import com.example.aqualuminexapp.database_utils.WalletsDataAccess;
import com.example.aqualuminexapp.utils.ChangingScenes;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

        //walletsDataAccess.mtnCardInfo();
        //walletsDataAccess.tigoCardInfo();
        //walletsDataAccess.vodaCardInfo();


        //initialize();




    }

    public volatile boolean setContent = false;
    //UF-477523
    public static void initializeWalletData(MFXScrollPane scrollPane) throws IOException {
        FXMLLoader loader = new FXMLLoader(WalletCardsController.class.getResource("wallet_cards.fxml"));
        Parent content = loader.load();

        WalletCardsController controller = loader.getController();
        controller.initializeData();


        Platform.runLater(()->{


        // Bind MTN account info labels to observable properties
        controller.getMtnAccName().textProperty().bind(controller.mtnAccNameProperty());
        System.out.println(controller.getMtnAccName() + " jereeeeeeeee");
        controller.getMtnAccNumber().textProperty().bind(controller.mtnAccNumberProperty());

        // Bind tigo account info labels to observable properties
        controller.getTigoAccName().textProperty().bind(controller.tigoAccNameProperty());
        controller.getTigoAccNumber().textProperty().bind(controller.tigoAccNumberProperty());

        // Bind voda account info labels to observable properties
        controller.getVodaAccName().textProperty().bind(controller.vodaAccNameProperty());
        controller.getVodaAccNumber().textProperty().bind(controller.vodaAccNumberProperty());
    });


        scrollPane.setContent(content);


    }

    public static boolean isDataLoaded = false;
    public void initializeData(){
        walletsDataAccess.mtnCardInfo();
        walletsDataAccess.tigoCardInfo();
        walletsDataAccess.vodaCardInfo();

        System.out.println(Arrays.toString(WalletsDataAccess.mtnCardInfo) + " -- mtn");
        System.out.println(Arrays.toString(WalletsDataAccess.tigoCardInfo) + " -- tigo");
        System.out.println(Arrays.toString(WalletsDataAccess.vodaCardInfo) + " -- voda");

        // Set MTN account info using observable properties
        String[] mtnInfo = WalletsDataAccess.mtnCardInfo;
        if (mtnInfo[0] == null) {
            mtnAccNameProperty.set("Unregistered");
            mtnAccNumberProperty.set("000 000 0000");
        } else {
            mtnAccNameProperty.set(mtnInfo[0]);
            mtnAccNumberProperty.set(mtnInfo[1]);
        }
        // Set voda account info using observable properties
        String[] vodaInfo = WalletsDataAccess.vodaCardInfo;
        if (vodaInfo[0] == null) {
            vodaAccNameProperty.set("Unregistered");
            vodaAccNumberProperty.set("000 000 0000");
        } else {
            vodaAccNameProperty.set(vodaInfo[0]);
            vodaAccNumberProperty.set(vodaInfo[1]);
        }

        // Set tigo account info using observable properties
        String[] tigoInfo = WalletsDataAccess.tigoCardInfo;
        if (tigoInfo[0] == null) {
            tigoAccNameProperty.set("Unregistered");
            tigoAccNumberProperty.set("000 000 0000");
        } else {
            tigoAccNameProperty.set(tigoInfo[0]);
            tigoAccNumberProperty.set(tigoInfo[1]);
        }

    }
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
                //System.out.println(scrollPane.getId() + " id here else");


                controller.getAddMTNAcc().setVisible(true);
                controller.getAddTigoAcc().setVisible(true);
                controller.getAddVodaAcc().setVisible(true);
            }



            isDataLoaded = true;
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


                        walletInfo.add(0, "mtn");


                        deleteWalletButtons.get(finalI).setVisible(true);
                        addWalletButtons.get(finalI).setVisible(false);
                        System.out.println("addMTNAcc");
                    }
                    case "addTigoAcc" -> {
                        ChangingScenes.translateScene(AddNewWalletController.class, walletCardsStackPane,
                                walletCardsAnchorPane, "add_new_wallet", 'f');

                        walletInfo.add(0, "tigo");

                        deleteWalletButtons.get(finalI).setVisible(true);
                        addWalletButtons.get(finalI).setVisible(false);
                        System.out.println("addTigoAcc");
                    }
                    case "addVodaAcc" -> {
                        ChangingScenes.translateScene(AddNewWalletController.class, walletCardsStackPane,
                                walletCardsAnchorPane, "add_new_wallet", 'f');

                        walletInfo.add(0, "voda");

                        deleteWalletButtons.get(finalI).setVisible(true);
                        addWalletButtons.get(finalI).setVisible(false);
                        System.out.println("addVodaAcc");
                    }

                }

            });
        }
        System.out.println(walletInfo);
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
        addWalletButtons.add(1, addTigoAcc); //tigo
        addWalletButtons.add(2, addVodaAcc); //voda
    }

    private void addDeleteButtonsToArray(){
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