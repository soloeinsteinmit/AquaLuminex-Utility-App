package com.example.aqualuminexapp.database_utils;

import com.example.aqualuminexapp.AquaLuminexMain;
import com.example.aqualuminexapp.utils.ShowToastMessage;

import java.sql.*;
import java.util.ArrayList;

public class LoginDataAccess {


    /**
     * retrieves details user during login
     * */
    public static ArrayList<String> loggedInUserDetails = new ArrayList<>(4);
    public static String loggedInAccountID;
    public static String loggedInUserEmail;

    /**
     * @return <b>true</b> if user exist else <b>false</b> if user does not exist
     * */

    public static boolean checkLoginCredential(String accountId, String password){
        Connection connection = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try{
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
                ShowToastMessage.showToast(AquaLuminexMain.messageToastStage, "Invalid User Credentials", 3, 4, 3);
                System.out.println(loggedInAccountID);
                System.out.println(loggedInUserDetails);
                return false;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            // Make sure to close the connection even if an exception occurs
            try {
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (psCheckUserExist != null) {
                    psCheckUserExist.close();
                }
            } catch (SQLException e) {
                // Handle any errors that occur while closing the connection
            }
        }




    }

    public static void getLoggedUserId(String accountId) throws SQLException {
        Connection connection = null;

        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;
        try{

            connection = DriverManager.getConnection(DBConstants.databaseURL);

            psCheckUserExist = connection.prepareStatement("""
                SELECT user_id, email
                FROM user_credentials
                WHERE user_id = ?""");
            psCheckUserExist.setString(1, accountId);

            resultSet = psCheckUserExist.executeQuery();
            loggedInAccountID = resultSet.getString("user_id");
            if (resultSet.isBeforeFirst()){
                while(resultSet.next()){
                    loggedInAccountID = resultSet.getString("user_id");
                    loggedInUserEmail = resultSet.getString("email");

                    System.out.println(loggedInAccountID +" here");
                    System.out.println(loggedInUserEmail +" here");
                }

            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            // Make sure to close the connection even if an exception occurs
            try {
                if (connection != null) {
                    connection.close();
                }
                if (psCheckUserExist != null){
                    psCheckUserExist.close();
                }
                if (resultSet != null){
                    resultSet.close();
                }

            } catch (SQLException e) {
                // Handle any errors that occur while closing the connection
            }
        }

    }


}