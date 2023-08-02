package com.example.aqualuminexapp.database_utils;

import java.sql.*;
import java.util.ArrayList;

public class LoginDataAccess {


    /**
     * retrieves details user during login
     * */
    public static ArrayList<String> loggedInUserDetails = new ArrayList<>(4);
    public static String loggedInAccountID;

    /**
     * @return <b>true</b> if user exist else <b>false</b> if user does not exist
     * */

    public static boolean checkLoginCredential(String accountId, String password) throws SQLException {
        Connection connection;
        PreparedStatement psCheckUserExist;
        ResultSet resultSet;

        connection = DriverManager.getConnection(DBConstants.databaseURL);

        psCheckUserExist = connection.prepareStatement("""
                SELECT user_id, password, picture, username, email
                FROM user_credentials
                WHERE user_id = ? AND password = ?""");

        psCheckUserExist.setString(1, accountId);
        psCheckUserExist.setString(2, password); // decrypt password
        resultSet = psCheckUserExist.executeQuery();
        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                loggedInUserDetails.add(0, resultSet.getString("username"));
                loggedInUserDetails.add(1, resultSet.getString("email"));
                loggedInUserDetails.add(2, resultSet.getString("picture"));
                loggedInUserDetails.add(3, resultSet.getString("user_id"));

            }
            System.out.println("hererere");
            return true;
        }else {
            System.out.println(loggedInAccountID);
            System.out.println(loggedInUserDetails);
            return false;
        }


    }

    public static void getLoggedUserId(String accountId) throws SQLException {
        Connection connection;
        PreparedStatement psCheckUserExist;
        ResultSet resultSet;

        connection = DriverManager.getConnection(DBConstants.databaseURL);

        psCheckUserExist = connection.prepareStatement("""
                SELECT user_id
                FROM user_credentials
                WHERE user_id = ?""");
        psCheckUserExist.setString(1, accountId);

        resultSet = psCheckUserExist.executeQuery();
        loggedInAccountID = resultSet.getString("user_id");
        if (resultSet.isBeforeFirst()){
            loggedInAccountID = resultSet.getString("user_id");
        }
    }


}