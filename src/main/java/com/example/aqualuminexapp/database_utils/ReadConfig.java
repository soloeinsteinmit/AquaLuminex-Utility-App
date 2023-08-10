package com.example.aqualuminexapp.database_utils;

import com.example.aqualuminexapp.LoginController;
import com.example.aqualuminexapp.utils.AppSettings;

public class ReadConfig {
    static AppSettings settings = AppSettings.getAppSettings();
    /**
     * @return true means read from config.json otherwise read from database
     * <p>
     *
     *     <br>
     *     checks if logged uId is in config.json, read id from config.json
     *      else read database
     * </p>
     * */
    public static boolean readFromConfig(){

        String accountID = settings.getAccount_id();
        System.out.println(LoginDataAccess.loggedInAccountID);
        return LoginController.LOGGED_IN_ACCOUNT_ID.equals(accountID);
    }
}