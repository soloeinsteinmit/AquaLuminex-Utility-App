module com.example.aqualuminexapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.aqualuminexapp to javafx.fxml;
    exports com.example.aqualuminexapp;
}