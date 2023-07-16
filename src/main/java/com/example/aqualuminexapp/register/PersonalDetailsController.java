package com.example.aqualuminexapp.register;

import com.example.aqualuminexapp.AquaLuminexMain;
import com.example.aqualuminexapp.utils.EmailValidation;
import io.github.gleidson28.GNAvatarView;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

/**
 * @author .py_ML_ai_MIT(Main author of this code)
 * @author © Deimos Coding Projects -some part of the code was copied from the second author mentioned
 */
public class PersonalDetailsController implements Initializable {

    private static final String EMAIL_PATTERN = "^(?!.*\\.{2})(?!.*@.*@)[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"; // req exp we are using
    private static final int MAX_INPUT = 64; // max chars allowed in an email address typically 64
    private static final String WHOLE_NUMBER_PATTERN = "\\d*";
    //    private static final String WHOLE_NUMBER_PATTERN = "\\d{3} \\d{3} \\d{4}";
    private static final int MAX_INPUT_TELEPHONE = 12;
    private static final int MAX_LENGTH = 10;
    private static final String SEPARATOR = " ";
    private static final String TELEPHONE_PATTERN = "\\d{0,3}(\\d{0,3})?(\\d{0,4})?";
    public static AnchorPane userDetailsAnchorPanePublic;
    List<File> imageFiles;
    Image profileImage;
    @FXML
    private AnchorPane userDetailsAnchorPane;
    @FXML
    private ImageView addImageButton;
    @FXML
    private ImageView errorEmailImg;
    @FXML
    private ImageView errorTelephoneImg;
    @FXML
    private Label browseLabel;
    @FXML
    private Label normalLabel;
    @FXML
    private Label labelErrorMsg;
    @FXML
    private Label labelErrorMsgTelephone;
    @FXML
    private Label removeProfileLabel;
    @FXML
    private MFXTextField emailField;
    @FXML
    private MFXRadioButton femaleRadioBtn;
    @FXML
    private ToggleGroup gender;
    @FXML
    private MFXRadioButton maleRadioBtn;
    @FXML
    private GNAvatarView profileImageField;
    @FXML
    private MFXTextField telephoneNumberField;
    @FXML
    private MFXTextField userNameField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userDetailsAnchorPanePublic = userDetailsAnchorPane;

        // initialize email validation checker
        initializeEmailValidation();
        initializeTelephone3();

        // Set the text formatter to format the phone number
        /*UnaryOperator<TextFormatter.Change> formatter = createPhoneNumberFormatter();
        TextFormatter<String> textFormatter = new TextFormatter<>(formatter);
        telephoneNumberField.setTextFormatter(textFormatter);*/

        labelErrorMsg.setText(""); // set initial error to an empty string
        labelErrorMsgTelephone.setText("");
    }

    @FXML
    void handleDragDropped(DragEvent event) throws IOException {
        imageFiles = event.getDragboard().getFiles();
        profileImage = new Image(Files.newInputStream(imageFiles.get(0).toPath()));
        profileImageField.setImage(profileImage);
        removeProfileLabel.setVisible(true);
    }

    @FXML
    void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            browseLabel.setVisible(false);
            addImageButton.setVisible(false);
            normalLabel.setVisible(false);
            event.acceptTransferModes(TransferMode.ANY);
        }

    }

    @FXML
    void removeProfileImage(MouseEvent event) {
        browseLabel.setVisible(true);
        addImageButton.setVisible(true);
        normalLabel.setVisible(true);

        String imagePath = "D:\\IntelliJ IDEA\\Java projects\\AquaLuminexApp\\src\\main\\resources\\com\\example\\aqualuminexapp\\images\\circle.png";
        String imagePathResource = "com/example/aqualuminexapp/images/circle.png";
        profileImage = new Image("file:" + imagePath);

        profileImageField.setImage(profileImage);

        removeProfileLabel.setVisible(false);
    }

    @FXML
    void openFileChooserWindow(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpeg", "*.jpg"));

        File selectedImageFile = fileChooser.showOpenDialog(AquaLuminexMain.fileChooserStage);
        if (selectedImageFile != null) {
            try {
                profileImage = new Image(Files.newInputStream(selectedImageFile.toPath()));

                browseLabel.setVisible(false);
                addImageButton.setVisible(false);
                normalLabel.setVisible(false);

                profileImageField.setImage(profileImage);
                removeProfileLabel.setVisible(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void validateEmail(KeyEvent event) {
        EmailValidation emailValidation = new EmailValidation();
        emailValidation.validateEmail(emailField);
    }

    private void initializeEmailValidation() {
        ////////////////////////////////////////////////////////////////////////
        // UNARY OPERATOR -> Standard Regular Expression for Email Validation //
        ////////////////////////////////////////////////////////////////////////
        UnaryOperator<TextFormatter.Change> emailAddressRegExpFilter = change -> {
            String newText = change.getControlNewText(); // Gets the complete new text which will be used on the control after this change.
            String textUsed = change.getText(); // User typed or OS pasted text

            //System.out.println("\nTextBox 1 - Control textUsed : " + textUsed); // Output TextField User and OS
            // Pasted Input
            //System.out.println("TextBox 1 - Control newText  : " + newText); // Output Control Text

            labelErrorMsg.setText(""); // Reset Error Label

            // Check for maximum input
            if (newText.length() > MAX_INPUT) {
                labelErrorMsg.setText("ERROR - Exceeds Max Length " + MAX_INPUT);
                return null; // don't allow char input as invalid
            }
            // colour the TextField Pink while total input for the email input is invalid
            if (newText.matches(EMAIL_PATTERN)) {
                emailField.setStyle("-fx-border-color: lightgray;");
                errorEmailImg.setVisible(false);
            } else {
                emailField.setStyle("-fx-border-color: #EF6E6B;");
                errorEmailImg.setVisible(true);
                if (newText.equals("")) {
                    emailField.setStyle("-fx-border-color: lightgray;");
                    errorEmailImg.setVisible(false);


                }
            }
            // checks if email field is empty then returns border back to original color
            if (newText.equals("") && emailField.isFocused()) {
                emailField.setStyle("-fx-border-color: #7a0ed9;");
            }
            return change;
        };

        TextFormatter<String> emailAddress2Formatter = new TextFormatter<>(emailAddressRegExpFilter);
        emailField.setTextFormatter(emailAddress2Formatter);
    }

    private void initializeTelephone() {
        // Only allow digits and a maximum of 5 characters
        UnaryOperator<TextFormatter.Change> wholeNumberFilter = change -> {
            String newText = change.getControlNewText();
            String textUsed = change.getText();


            //System.out.println("Total Change Control              -> " + newText);
            //System.out.println("\nChange Control - ** User Input ** -> " + textUsed + "\n");

            if (newText.matches(WHOLE_NUMBER_PATTERN)) {
//
                labelErrorMsgTelephone.setText("");
//                telephoneNumberField.setStyle("-fx-background-color: white;");
                errorTelephoneImg.setVisible(false);
                // Insert a space after every character


                if (newText.length() <= MAX_INPUT_TELEPHONE) {

                    if (newText.length() == 3) {
//                        change.setText("  5");
                        System.out.println(3);
                    } else if (newText.length() == 7) {
//                        change.setText("  5");
                        System.out.println(7);
                    }
                    return change;
                } else {
                    labelErrorMsgTelephone.setText("Invalid Input - Max Input " + MAX_INPUT_TELEPHONE + " numbers");
                }
            } else {
                if (newText.equals("")) {
                    labelErrorMsgTelephone.setText("Invalid Input - Whole Number");
                } else {
                    labelErrorMsgTelephone.setText("Invalid Input - Whole Number");
                }

            }

//            telephoneNumberField.setStyle("-fx-background-color: pink;");
            errorTelephoneImg.setVisible(true);
            if (newText.equals("")) {
                errorTelephoneImg.setVisible(false);
            }
            return null;
        };

        TextFormatter<String> wholeNumberFormatter = new TextFormatter<>(wholeNumberFilter);

        telephoneNumberField.setTextFormatter(wholeNumberFormatter);
    }

    private void initializeTelephone1() {
        UnaryOperator<TextFormatter.Change> wholeNumberFilter = change -> {
            String newText = change.getControlNewText();

            if (newText.matches(WHOLE_NUMBER_PATTERN)) {
                labelErrorMsgTelephone.setText("");
                errorTelephoneImg.setVisible(false);

                // Remove all non-digit characters
                String digitsOnly = newText.replaceAll("\\D", "");

                // Insert spaces at the desired positions
                StringBuilder formattedText = new StringBuilder();
                int length = digitsOnly.length();
                for (int i = 0; i < length; i++) {
                    if (i == 3 || i == 6) {
                        formattedText.append(" "); // Insert a space at positions 3 and 6
                    }
                    formattedText.append(digitsOnly.charAt(i));
                }

                // Set the modified text and caret position
                change.setText(formattedText.toString());
                change.setCaretPosition(change.getControlNewText().length());

                // Check the maximum input length
                if (formattedText.length() <= MAX_INPUT_TELEPHONE) {
                    return change;
                } else {
                    labelErrorMsgTelephone.setText("Invalid Input - Max Input " + MAX_INPUT_TELEPHONE + " numbers");
                    return null;
                }
            } else {
                labelErrorMsgTelephone.setText("Invalid Input - Whole Number");
                errorTelephoneImg.setVisible(true);
                return null;
            }
        };
    }

    private void initializeTelephone2() {

    }
//    private static final int MAX_INPUT_TELEPHONE = 12;

    private void initializeTelephone3() {
        // Only allow digits and a maximum of 12 characters
        UnaryOperator<TextFormatter.Change> telephoneFilter = change -> {
            String newText = change.getControlNewText();

            if (newText.matches(TELEPHONE_PATTERN)) {
                labelErrorMsgTelephone.setText("");
                errorTelephoneImg.setVisible(false);
                System.out.println(newText + " hh");
                System.out.println(change.getText() + " jjj");
                userNameField.setText(change.getText());

                StringBuilder formattedText = new StringBuilder();
                int digitCount = 0;
                for (char c : newText.toCharArray()) {
                    if (Character.isDigit(c)) {
                        formattedText.append(c);
                        digitCount++;

                        if (digitCount == 3 || digitCount == 6) {
                            formattedText.append(" ");
                        }

                        if (digitCount >= MAX_INPUT_TELEPHONE) {
                            break;
                        }
                    }
                }

                System.out.println(formattedText);

                change.setText(formattedText.toString());

                return change;
            } else {
                labelErrorMsgTelephone.setText("Invalid Input - Whole Number");
                errorTelephoneImg.setVisible(true);

                if (newText.equals("")) {
                    errorTelephoneImg.setVisible(false);

                }

                return null;
            }
        };

        TextFormatter<String> telephoneFormatter = new TextFormatter<>(telephoneFilter);

        telephoneNumberField.setTextFormatter(telephoneFormatter);

    }


}