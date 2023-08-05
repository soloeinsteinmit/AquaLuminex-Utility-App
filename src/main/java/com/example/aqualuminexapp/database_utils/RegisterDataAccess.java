package com.example.aqualuminexapp.database_utils;

import com.example.aqualuminexapp.utils.AppSettings;

import java.sql.*;

public class RegisterDataAccess {

    public static AppSettings appSettings = AppSettings.getAppSettings();
    public static String accId = appSettings.getAccount_id();
    public static void insertPersonalDetails(){

        PreparedStatement insertPersonalDetails = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBConstants.databaseURL);
            insertPersonalDetails = connection.prepareStatement("""
                    UPDATE user_credentials
                    SET username = ?, email = ?, phone_number = ?, gender = ?, picture = ?
                    WHERE user_id =?""");
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            // Make sure to close the connection even if an exception occurs
            try {
                if (connection != null) {
                    connection.close();
                }
                if (insertPersonalDetails != null) {
                    insertPersonalDetails.close();
                }
            } catch (SQLException e) {
                // Handle any errors that occur while closing the connection
            }
        }
        // Make sure to close the connection even if an exception occurs
        // Handle any errors that occur while closing the connection


    }

    public static void insertAccountId() throws SQLException {
        Connection connection = null;
        PreparedStatement insertAccountId = null;
        try {
            connection = DriverManager.getConnection(DBConstants.databaseURL);

            insertAccountId = connection.prepareStatement("""
                        INSERT INTO user_credentials(user_id, username, email, phone_number, gender, password, picture)
                        VALUES(?, ?, ?, ?, ?, ?, ?)
                    """);
            insertAccountId.setString(1, accId);
            insertAccountId.setNull(2, Types.VARCHAR); // Set username as NULL
            insertAccountId.setNull(3, Types.VARCHAR); // Set email as NULL
            insertAccountId.setNull(4, Types.VARCHAR); // Set phone_number as NULL
            insertAccountId.setNull(5, Types.VARCHAR); // Set gender as NULL
            insertAccountId.setNull(6, Types.VARCHAR); // Set password as NULL
            insertAccountId.setNull(7, Types.VARCHAR);
            insertAccountId.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            // Make sure to close the connection even if an exception occurs
            try {
                if (connection != null) {
                    connection.close();
                }
                if (insertAccountId != null) {
                    insertAccountId.close();
                }
            } catch (SQLException e) {
                // Handle any errors that occur while closing the connection
            }
        }
        // Make sure to close the connection even if an exception occurs
        // Handle any errors that occur while closing the connection


    }

    public static void insertPassword() {
        Connection connection = null;
        PreparedStatement insertPassword = null;
        try {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            // Make sure to close the connection even if an exception occurs
            try {
                if (connection != null) {
                    connection.close();
                }
                if (insertPassword != null) {
                    insertPassword.close();
                }
            } catch (SQLException e) {
                // Handle any errors that occur while closing the connection
            }
        }
        // Make sure to close the connection even if an exception occurs
        // Handle any errors that occur while closing the connection


    }


}