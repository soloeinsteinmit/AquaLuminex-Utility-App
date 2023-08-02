package com.example.aqualuminexapp.dashboard.transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class is for testing data in the transactions
 * */
public class CardsData {


    String meterKind;
    String meterName;
    String date;
    String time;
    String aqTokenNumber;
    String status;
    String purchasedAmount;


    public CardsData(String meterKind, String meterName, String date, String time,
                        String purchasedAmount, String status) {
        this.meterKind = meterKind;
        this.meterName = meterName;
        this.date = date;
        this.time = time;
        this.purchasedAmount = purchasedAmount;
        this.status = status;
    }



    public static HashMap<String, ArrayList<String>> cardData = new HashMap<String, ArrayList<String>>();

    public static HashMap<String, ArrayList<String>> transactionsCardData(){
        cardData.put("1111111111", new ArrayList<>(List.of("Hello Meter", "January 26 2023", "4:11 AM", "33.21",
                "1")));
        cardData.put("7890864312", new ArrayList<>(List.of("Jessica", "March 03 2021", "7:11 AM", "899.00", "1")));
        cardData.put("3333333333", new ArrayList<>(List.of("Mango Meter", "September 03 2021", "4:11 AM", "3.09",
                "1")));
        cardData.put("9876789876", new ArrayList<>(List.of("Orange Meter", "July 03 2021", "9:31 AM", "899.39", "2")));
        cardData.put("3456754321", new ArrayList<>(List.of("Hello Meter", "January 03 2021", "1:11 AM", "44.99", "1")));
        cardData.put("7865421902", new ArrayList<>(List.of("Sweet Meter", "June 03 2021", "4:11 PM", "12.2", "2")));
        cardData.put("0987213782", new ArrayList<>(List.of("Hello Meter", "November 03 2021", "2:01 AM", "45.0",
                "1")));
        cardData.put("8765290112", new ArrayList<>(List.of("Collins Meter", "August 03 2021", "12:01 PM", "55.0",
                "2")));
        cardData.put("0098626781", new ArrayList<>(List.of("Butter Meter", "January 03 2021", "4:11 AM", "90.9", "1")));
        cardData.put("3678911998", new ArrayList<>(List.of("Messi Meter", "December 03 2021", "4:00 PM", "741.9",
                "2")));
        return cardData;
    }



    public String getMeterKind() {
        return meterKind;
    }

    public void setMeterKind(String meterKind) {
        this.meterKind = meterKind;
    }

    public String getMeterName() {
        return meterName;
    }

    public void setMeterName(String meterName) {
        this.meterName = meterName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAqTokenNumber() {
        return aqTokenNumber;
    }

    public void setAqTokenNumber(String aqTokenNumber) {
        this.aqTokenNumber = aqTokenNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPurchasedAmount() {
        return purchasedAmount;
    }

    public void setPurchasedAmount(String purchasedAmount) {
        this.purchasedAmount = purchasedAmount;
    }


}