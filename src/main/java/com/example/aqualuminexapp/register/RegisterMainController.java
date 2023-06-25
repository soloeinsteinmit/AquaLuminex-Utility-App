package com.example.aqualuminexapp.register;

import com.example.aqualuminexapp.utils.ChangingScenes;
import com.example.aqualuminexapp.utils.ProgressBarAnimationBackward;
import com.example.aqualuminexapp.utils.ProgressBarAnimationForward;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterMainController implements Initializable {

    ProgressBarAnimationForward progressBarAnimationForward1;
    ProgressBarAnimationForward progressBarAnimationForward2;
    ProgressBarAnimationBackward progressBarAnimationBackward1;
    ProgressBarAnimationBackward progressBarAnimationBackward2;
    int trackNext = 0;
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
    private AnchorPane registerAnchorPane;
    @FXML
    private StackPane registerTransitionContainerStack;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize first scene
        changingScenes.initializeScene(UserDetailsController.class, registerTransitionContainerStack, "user-details");

        next();
        previous();
    }

    public void next() {
        progressBarAnimationForward1 = new ProgressBarAnimationForward(progressBarStage1);
        progressBarAnimationForward2 = new ProgressBarAnimationForward(progressBarStage2);

        /*progressBarAnimationForward2 = new ProgressBarAnimationForward(progressBarStage2,
                registerTransitionContainerStack, PasswordSecurityController.pA, "password" +
                "-security",
                'f');*/

        nextButton.setOnMouseClicked(mouseEvent -> {
            System.out.println(trackNext + "pressed");

            Thread thread;
            if ((int) progressBarStage1.getProgress() == 0 && (int) progressBarStage2.getProgress() == 0) {

                thread = new Thread(progressBarAnimationForward1);
                if (!thread.isAlive()) {
                    ChangingScenes.translateScene(AccountInfoController.class, registerTransitionContainerStack,
                            UserDetailsController.userDetailsAnchorPanePublic, "account-info", 'f');
                }

            } else {
                thread = new Thread(progressBarAnimationForward2);
                if (!thread.isAlive()) {
                    ChangingScenes.translateScene(PasswordSecurityController.class, registerTransitionContainerStack,
                            AccountInfoController.aP, "password-security", 'f');
                }
            }
            thread.setDaemon(true);
            thread.start();

        });
    }

    public void previous() {
        progressBarAnimationBackward1 = new ProgressBarAnimationBackward(progressBarStage1);
        progressBarAnimationBackward2 = new ProgressBarAnimationBackward(progressBarStage2);

        previousButton.setOnMouseClicked(mouseEvent -> {

            Thread thread;
            System.out.println(progressBarStage1.getProgress() + " === 1");
            System.out.println(progressBarStage2.getProgress() + " === 2");

            if ((int) progressBarStage1.getProgress() == 1 && (int) progressBarStage2.getProgress() == 0) {
                thread = new Thread(progressBarAnimationBackward1);
                if (!thread.isAlive()) {
                    ChangingScenes.translateScene(UserDetailsController.class, registerTransitionContainerStack,
                            AccountInfoController.aP, "user-details", 'b');
                }

            } else {
                thread = new Thread(progressBarAnimationBackward2);
                if (!thread.isAlive()) {
                    ChangingScenes.translateScene(AccountInfoController.class, registerTransitionContainerStack,
                            PasswordSecurityController.pA, "account-info", 'b');
                }
            }
            thread.setDaemon(true);
            thread.start();

        });
    }
}