module com.example.aqualuminexapp {
    requires javafx.fxml;
    requires org.slf4j;
//    requires multi.map;
    requires mysql.connector.j;
    requires java.sql;
    requires com.jfoenix;
    requires MaterialFX;
    requires GNAvatarView;
//    requires de.jensd.fx.glyphs.fontawesome;
    requires VirtualizedFX;
    requires javax.mail.api;
    //requires java.mail;
    requires TrayTester;


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
    opens com.example.aqualuminexapp.utils to javafx.fxml;

    // meter package
    exports com.example.aqualuminexapp.meter;
    opens com.example.aqualuminexapp.meter to javafx.fxml;

    // dashboard package
    exports com.example.aqualuminexapp.dashboard;
    opens com.example.aqualuminexapp.dashboard to javafx.fxml;


    //  transactions package
    exports com.example.aqualuminexapp.transactions;
    opens com.example.aqualuminexapp.transactions to javafx.fxml;



    // wallets package
    exports com.example.aqualuminexapp.wallets;
    opens com.example.aqualuminexapp.wallets to javafx.fxml;
}