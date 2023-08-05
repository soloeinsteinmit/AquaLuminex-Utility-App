package com.example.aqualuminexapp.database_utils;

import com.example.aqualuminexapp.utils.AppSettings;

import java.sql.*;

public class WalletsDataAccess {

    AppSettings settings = AppSettings.getAppSettings();



    String accountID = settings.getAccount_id();

    /**
     * @return AquaLuminex balance
     * */
    public int retrieveAQBalance() throws SQLException {
        Connection connection = null;
        PreparedStatement psGetAQBalance = null;
        ResultSet resultSet = null;

        try {
            int aqBalance = 0;

            connection = DriverManager.getConnection(DBConstants.databaseURL);
            //String accountID = settings.getAccount_id();

            psGetAQBalance = connection.prepareStatement("""
                SELECT wallet_balance.wallet_balance
                FROM wallet_balance
                INNER JOIN wallet_account
                ON wallet_balance.wallet_id = wallet_account.wallet_id
                WHERE wallet_balance.user_id = ? AND wallet_balance.wallet_id = 'aq_balance';""");
            /*
             * returns true means read from config.json
             * otherwise read from db
             * */
            if (ReadConfig.readFromConfig()){
                psGetAQBalance.setString(1, accountID);
            }else {
                accountID = LoginDataAccess.loggedInUserDetails.get(3);// account id
                psGetAQBalance.setString(1, accountID);
            }

            resultSet = psGetAQBalance.executeQuery();
            if (resultSet.isBeforeFirst()){
                while (resultSet.next()){
                    aqBalance = resultSet.getInt("wallet_balance");
                }
            }
            return aqBalance;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            // Make sure to close the connection even if an exception occurs
            try {
                if (connection != null) {
                    connection.close();
                }
                if (psGetAQBalance != null) {
                    psGetAQBalance.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                // Handle any errors that occur while closing the connection
            }
        }



    }

    public static void insetWalletInfoIntoDB(String walletId, String walletName, String phoneNumber,
                    String userId){

        PreparedStatement psInsertIntoWalletAccount;
        try (Connection connection = DriverManager.getConnection(DBConstants.databaseURL)) {

            psInsertIntoWalletAccount = connection.prepareStatement("""
                    INSERT
                    INTO wallet_account(wallet_id, wallet_name, phone_number, wallet_type, user_id)
                    VALUES(?, ?, ? ,?, ?);
                    """);
            psInsertIntoWalletAccount.setString(1, walletId);
            psInsertIntoWalletAccount.setString(2, walletName);
            psInsertIntoWalletAccount.setString(3, phoneNumber);
            psInsertIntoWalletAccount.setNull(4, Types.VARCHAR); // wallet type to null
            psInsertIntoWalletAccount.setString(5, userId);
            psInsertIntoWalletAccount.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Make sure to close the connection even if an exception occurs
        // Handle any errors that occur while closing the connection


    }

    public static void insertWalletBalance(String walletId, String balance, String userId) {

        PreparedStatement psInsertIntoWalletBalance;
        try (Connection connection = DriverManager.getConnection(DBConstants.databaseURL)) {
            psInsertIntoWalletBalance = connection.prepareStatement("""
                    INSERT
                    INTO wallet_balance(wallet_id, wallet_balance, user_id)
                    VALUES(?, ?, ?);
                    """);
            psInsertIntoWalletBalance.setString(1, walletId);
            psInsertIntoWalletBalance.setInt(2, Integer.parseInt(balance));
            psInsertIntoWalletBalance.setString(3, userId);
            psInsertIntoWalletBalance.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Make sure to close the connection even if an exception occurs
        // Handle any errors that occur while closing the connection

    }

    /**
     * <p>
     *     index 0 - wallet name
     *     index 1 - phone number
     *     index 2 - wallet balance
     * </p>
     * */
    public static String[] mtnCardInfo = new String[4];

    public void mtnCardInfo(){
        Connection connection = null;
        PreparedStatement psGetMTNInfo = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DBConstants.databaseURL);
            psGetMTNInfo = connection.prepareStatement("""
                    SELECT wallet_account.wallet_name, wallet_account.phone_number,\s
                           wallet_balance.wallet_balance, wallet_account.wallet_id
                    FROM wallet_account
                    INNER JOIN wallet_balance
                    ON wallet_account.wallet_id = wallet_balance.wallet_id
                    WHERE wallet_account.user_id = ? AND wallet_account.wallet_id = 'mtn';
                """);
            //String accountID = settings.getAccount_id();

            if (ReadConfig.readFromConfig()){
                psGetMTNInfo.setString(1, accountID);
            }else {
                accountID = LoginDataAccess.loggedInUserDetails.get(3);// account id
                psGetMTNInfo.setString(1, accountID);
            }

            resultSet = psGetMTNInfo.executeQuery();
            if (resultSet.isBeforeFirst()){
                while (resultSet.next()){
                    mtnCardInfo[0] = resultSet.getString("wallet_name");
                    mtnCardInfo[1] = resultSet.getString("phone_number");
                    mtnCardInfo[2] = resultSet.getString("wallet_balance");
                    mtnCardInfo[3] = resultSet.getString("wallet_id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            // Make sure to close the connection even if an exception occurs
            try {
                if (connection != null) {
                    connection.close();
                }
                if (psGetMTNInfo != null) {
                    psGetMTNInfo.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                // Handle any errors that occur while closing the connection
            }
        }
    }

    /**
     * <p>
     *     index 0 - wallet name
     *     index 1 - phone number
     *     index 2 - wallet balance
     * </p>
     * */
    public static String[] tigoCardInfo = new String[3];
    public void tigoCardInfo(){
        Connection connection = null;
        PreparedStatement psGetTigoInfo = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DBConstants.databaseURL);
            psGetTigoInfo = connection.prepareStatement("""
                    SELECT wallet_account.wallet_name, wallet_account.phone_number,\s
                        wallet_balance.wallet_balance
                    FROM wallet_account
                    INNER JOIN wallet_balance
                    ON wallet_account.wallet_id = wallet_balance.wallet_id
                    WHERE wallet_account.user_id = ? AND wallet_account.wallet_id = 'tigo';
                """);


            if (ReadConfig.readFromConfig()){
                psGetTigoInfo.setString(1, accountID);
            }else {
                accountID = LoginDataAccess.loggedInUserDetails.get(3);// account id
                psGetTigoInfo.setString(1, accountID);
            }

            resultSet = psGetTigoInfo.executeQuery();
            if (resultSet.isBeforeFirst()){
                while (resultSet.next()){
                    tigoCardInfo[0] = resultSet.getString("wallet_name");
                    tigoCardInfo[1] = resultSet.getString("phone_number");
                    tigoCardInfo[2] = resultSet.getString("wallet_balance");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            // Make sure to close the connection even if an exception occurs
            try {
                if (connection != null) {
                    connection.close();
                }
                if (psGetTigoInfo != null) {
                    psGetTigoInfo.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                // Handle any errors that occur while closing the connection
            }
        }
    }

    /**
     * <p>
     *     index 0 - wallet name
     *     index 1 - phone number
     *     index 2 - wallet balance
     * </p>
     * */
    public static String[] vodaCardInfo = new String[3];
    public void vodaCardInfo(){
        Connection connection = null;
        PreparedStatement psGetVodaInfo = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DBConstants.databaseURL);
            psGetVodaInfo = connection.prepareStatement("""
                    SELECT wallet_account.wallet_name, wallet_account.phone_number,\s
                        wallet_balance.wallet_balance
                    FROM wallet_account
                    INNER JOIN wallet_balance
                    ON wallet_account.wallet_id = wallet_balance.wallet_id
                    WHERE wallet_account.user_id = ? AND wallet_account.wallet_id = 'voda';
                """);


            if (ReadConfig.readFromConfig()){
                psGetVodaInfo.setString(1, accountID);
            }else {
                accountID = LoginDataAccess.loggedInUserDetails.get(3);// account id
                psGetVodaInfo.setString(1, accountID);
            }

            resultSet = psGetVodaInfo.executeQuery();
            if (resultSet.isBeforeFirst()){
                while (resultSet.next()){
                    vodaCardInfo[0] = resultSet.getString("wallet_name");
                    vodaCardInfo[1] = resultSet.getString("phone_number");
                    vodaCardInfo[2] = resultSet.getString("wallet_balance");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            // Make sure to close the connection even if an exception occurs
            try {
                if (connection != null) {
                    connection.close();
                }
                if (psGetVodaInfo != null) {
                    psGetVodaInfo.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                // Handle any errors that occur while closing the connection
            }
        }
    }
}