package com.example.aqualuminexapp.database_utils;

import com.example.aqualuminexapp.utils.AppSettings;

import java.sql.*;

public class WalletsDataAccess {

    AppSettings settings = AppSettings.getAppSettings();
    /**
     * @return AquaLuminex balance
     * */
    public int retrieveAQBalance() throws SQLException {
        Connection connection;
        PreparedStatement psGetAQBalance;
        ResultSet resultSet;

        int aqBalance = 0;

        connection = DriverManager.getConnection(DBConstants.databaseURL);
        String accountID = settings.getAccount_id();

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
    }
}