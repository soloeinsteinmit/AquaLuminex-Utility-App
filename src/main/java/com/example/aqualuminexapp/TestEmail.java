package com.example.aqualuminexapp;

import com.example.aqualuminexapp.database_utils.TransactionsDataAccess;
import com.example.aqualuminexapp.utils.AppSettings;

public class TestEmail {
    public static void main(String[] args) throws Exception {
//        SendEmail.sendMail("jomphiaessuman27@gmail.com");
//        SendEmail.sendMail("einsteinmit100@gmail.com");
//        SendEmail.sendMail("solomon.eshun@stu.ucc.edu.gh");
//        SendEmail.sendMail("samuelmoteyhoward@gmail.com");
//        SendEmail.sendMail("adjeibe567@gmail.com");
//        System.out.println("Sending email");


        AppSettings settings = AppSettings.getAppSettings();
//        System.out.println(settings.getEmail());
//        System.out.println(settings.getAccount_id());
//        System.out.println(settings.getPassword());
//        System.out.println(settings.getProfileImagePath());


        TransactionsDataAccess t = new TransactionsDataAccess();
/*
        t.getAllTransactions(settings.getAccount_id());
        for(Map.Entry<String, CardsData> e : TransactionsDataAccess.transactionsInfo.entrySet()){
            System.out.println("[" + e.getKey() + "," + e.getValue().getDate()+"]");
        }*/

    }
}