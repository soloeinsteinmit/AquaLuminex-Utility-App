package com.example.aqualuminexapp.utils;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class AppSettings {
    String userName;
    String email;
    String telephoneNumber;
    String gender;

    public static final String CONFIG_FILE = "config.txt";

    public AppSettings(){
        userName = "AquaLuminex";
        email = "email@gmail.com";
        telephoneNumber = "null";
        gender = "null";
    }

    public static void initConfig(){
        Writer writer = null;
        try {
            System.out.println("file is created");

            AppSettings appSettings = new AppSettings();
            Gson gson = new Gson();
            writer = new FileWriter(CONFIG_FILE);
            gson.toJson(appSettings, writer);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }




}