package com.example.aqualuminexapp.register;

import com.example.aqualuminexapp.LoginController;
import com.example.aqualuminexapp.utils.ChangingScenes;
import com.example.aqualuminexapp.utils.ProgressBarAnimationBackward;
import com.example.aqualuminexapp.utils.ProgressBarAnimationForward;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterMainController implements Initializable {

    ProgressBarAnimationForward progressBarAnimationForward1;
    ProgressBarAnimationForward progressBarAnimationForward2;
    ProgressBarAnimationBackward progressBarAnimationBackward1;
    ProgressBarAnimationBackward progressBarAnimationBackward2;
    ChangingScenes changingScenes = new ChangingScenes();


    @FXML
    private MFXButton nextButton;

    @FXML
    private MFXButton previousButton;

    @FXML
    private MFXProgressBar progressBarStage1;

    @FXML
    private MFXProgressBar progressBarStage2;

    @FXML
    private MFXProgressSpinner progressSpinner;

    @FXML
    private AnchorPane registerAnchorPane;

    @FXML
    private MFXButton loginButton;

    @FXML
    private StackPane registerTransitionContainerStack;

    @FXML
    private ImageView doneImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize first scene
        changingScenes.initializeScene(PersonalDetailsController.class, registerTransitionContainerStack, "personal-details");

        next();
        previous();
    }

    public void next() {
        progressBarAnimationForward1 = new ProgressBarAnimationForward(progressBarStage1, doneImage);
        progressBarAnimationForward2 = new ProgressBarAnimationForward(progressBarStage2, doneImage);


        nextButton.setOnMouseClicked(mouseEvent -> {

            Thread thread;
            if ((int) progressBarStage1.getProgress() == 0 && (int) progressBarStage2.getProgress() == 0) {

                thread = new Thread(progressBarAnimationForward1);
                if (!thread.isAlive()) {
                    ChangingScenes.translateScene(AccountInfoController.class, registerTransitionContainerStack,
                            PersonalDetailsController.userDetailsAnchorPanePublic, "account-info", 'f');
                    nextButton.setText("Next");

                    previousButton.setVisible(true);
                    loginButton.setVisible(false);
                }


            } else {
                thread = new Thread(progressBarAnimationForward2);

                if (!thread.isAlive()) {
                    ChangingScenes.translateScene(PasswordSecurityController.class, registerTransitionContainerStack,
                            AccountInfoController.aP, "password-security", 'f');

                    nextButton.setText("Done");
                }
            }


            thread.setDaemon(true);
            thread.start();

        });
    }

    public void previous() {
        progressBarAnimationBackward1 = new ProgressBarAnimationBackward(progressBarStage1);
        progressBarAnimationBackward2 = new ProgressBarAnimationBackward(progressBarStage2);

        previousButton.setVisible(false);
        loginButton.setVisible(true);

        previousButton.setOnMouseClicked(mouseEvent -> {

            Thread thread;
            /*System.out.println(progressBarStage1.getProgress() + " === 1");
            System.out.println(progressBarStage2.getProgress() + " === 2");*/

            if ((int) progressBarStage1.getProgress() == 1 && (int) progressBarStage2.getProgress() == 0) {
                thread = new Thread(progressBarAnimationBackward1);
                if (!thread.isAlive()) {
                    ChangingScenes.translateScene(PersonalDetailsController.class, registerTransitionContainerStack,
                            AccountInfoController.aP, "personal-details", 'b');

                }

            } else {
                thread = new Thread(progressBarAnimationBackward2);
                if (!thread.isAlive()) {
                    ChangingScenes.translateScene(AccountInfoController.class, registerTransitionContainerStack,
                            PasswordSecurityController.pA, "account-info", 'b');
                    nextButton.setText("Next");
                    doneImage.setVisible(false);
                }
            }
//            System.out.println((int) progressBarStage1.getProgress() + " here");
            if ((int) progressBarStage1.getProgress() == 1 && (int) progressBarStage2.getProgress() == 0) {
                previousButton.setVisible(false);
                loginButton.setVisible(true);
            }
            thread.setDaemon(true);
            thread.start();

        });
    }

    @FXML
    void loginUser(MouseEvent event) {
        ChangingScenes.translateScene(LoginController.class, LoginController.stackContainer, registerAnchorPane, "login", 'b');
    }
}