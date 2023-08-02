package com.example.aqualuminexapp.database_utils;

import com.example.aqualuminexapp.dashboard.transactions.CardsData;
import com.example.aqualuminexapp.utils.AppSettings;

import java.sql.*;
import java.util.HashMap;

public class TransactionsDataAccess {
    public static HashMap<String, CardsData> transactionsInfo = new HashMap<>();
    AppSettings settings = AppSettings.getAppSettings();

    String meterKind;
    String meterName;
    String date;
    String time;
    String aqTokenNumber;
    String status;
    String purchasedAmount;
    public void getAllTransactions() throws SQLException {

        Connection connection;
        PreparedStatement psGetTransaction;
        ResultSet resultSet;


        connection = DriverManager.getConnection(DBConstants.databaseURL);
        String accountID = settings.getAccount_id();

        psGetTransaction = connection.prepareStatement("""
                SELECT meters.user_id, meters.meter_name, meters.meter_kind ,transactions.date, transactions.time, transactions.transaction_id,
                        transactions.top_up_amount, transactions.top_up_status
                FROM transactions
                INNER JOIN meters
                ON transactions.meter_id = meters.meter_id
                WHERE user_id = ?;""");

        /*
         * returns true means read from config.json
         * otherwise read from db
         * */
        if (ReadConfig.readFromConfig()){
            psGetTransaction.setString(1, accountID);
        }else {
            accountID = LoginDataAccess.loggedInUserDetails.get(3);// account id
            psGetTransaction.setString(1, accountID);
        }

        //psGetTransaction.setString(1, accountID);

        resultSet = psGetTransaction.executeQuery();
        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                meterKind = resultSet.getString("meter_kind");
                meterName = resultSet.getString("meter_name");
                date = resultSet.getString("date");
                time = resultSet.getString("time");
                aqTokenNumber = resultSet.getString("transaction_id");
                purchasedAmount = resultSet.getString("top_up_amount");
                status = resultSet.getString("top_up_status");

                transactionsInfo.put(aqTokenNumber, new CardsData(meterKind, meterName, date, time, purchasedAmount, status));

            }

        }

    }
}