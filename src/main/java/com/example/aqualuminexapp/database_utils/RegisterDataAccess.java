package com.example.aqualuminexapp.database_utils;

import com.example.aqualuminexapp.utils.AppSettings;

import java.sql.*;

public class RegisterDataAccess {

    public static AppSettings appSettings = AppSettings.getAppSettings();
    public static String accId = appSettings.getAccount_id();
    public static void insertPersonalDetails() throws SQLException {
        Connection connection;
        PreparedStatement insertPersonalDetails;

        connection = DriverManager.getConnection(DBConstants.databaseURL);
        insertPersonalDetails = connection.prepareStatement("""
                    UPDATE user_credentials
                    SET username = ?, email = ?, phone_number = ?, gender = ?, picture = ?
                    WHERE user_id =?""" );
        System.out.println(appSettings.getUserName());
        System.out.println(appSettings.getEmail());
        System.out.println(appSettings.getTelephoneNumber());
        System.out.println(appSettings.getGender());


        insertPersonalDetails.setString(1, appSettings.getUserName());
        insertPersonalDetails.setString(2, appSettings.getEmail());
        insertPersonalDetails.setString(3, appSettings.getTelephoneNumber());
        insertPersonalDetails.setString(4, appSettings.getGender());
        insertPersonalDetails.setString(5, appSettings.getProfileImagePath());
        insertPersonalDetails.setString(6, accId);
        insertPersonalDetails.executeUpdate();
    }

    public static void insertAccountId() throws SQLException {
        Connection connection;
        PreparedStatement insertAccountId;



        connection = DriverManager.getConnection(DBConstants.databaseURL);

        insertAccountId = connection.prepareStatement("""
            INSERT INTO user_credentials(user_id, username, email, phone_number, gender, password, picture)
            VALUES(?, ?, ?, ?, ?, ?, ?)
        """);
        insertAccountId.setString(1, accId);
        insertAccountId.setNull(2, java.sql.Types.VARCHAR); // Set username as NULL
        insertAccountId.setNull(3, java.sql.Types.VARCHAR); // Set email as NULL
        insertAccountId.setNull(4, java.sql.Types.VARCHAR); // Set phone_number as NULL
        insertAccountId.setNull(5, java.sql.Types.VARCHAR); // Set gender as NULL
        insertAccountId.setNull(6, java.sql.Types.VARCHAR); // Set password as NULL
        insertAccountId.setNull(7, java.sql.Types.VARCHAR);
        insertAccountId.executeUpdate();

    }

    public static void insertPassword() throws SQLException {
        Connection connection;
        PreparedStatement insertPassword;

        connection = DriverManager.getConnection(DBConstants.databaseURL);
        insertPassword = connection.prepareStatement("""
                    UPDATE user_credentials
                    SET password = ?
                    WHERE user_id = ?""");
        System.out.println(appSettings.getAccount_id());
        System.out.println(appSettings.getPassword());

        insertPassword.setString(1, appSettings.getPassword());
        insertPassword.setString(2, accId);
        insertPassword.executeUpdate();
    }


}