module com.example.aqualuminexapp {
//    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires multi.map;
    requires mysql.connector.j;
    requires java.sql;
    requires com.jfoenix;
    requires MaterialFX;
    requires GNAvatarView;
    requires de.jensd.fx.glyphs.fontawesome;
    requires VirtualizedFX;
    requires java.mail;
//    requires javafx.graphics;


    exports com.example.aqualuminexapp;
    opens com.example.aqualuminexapp to javafx.fxml, javafx.graphics;

    // utils package
    exports com.example.aqualuminexapp.utils;

    // register package
    exports com.example.aqualuminexapp.register;
    opens com.example.aqualuminexapp.register to javafx.fxml;


    // opens css
    opens com.example.aqualuminexapp.css;

    // opens font
    // opens com.example.aqualuminexapp.fonts.Poppins;

    // opens images
    opens com.example.aqualuminexapp.images;
}