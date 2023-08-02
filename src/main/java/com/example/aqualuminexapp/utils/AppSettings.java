package com.example.aqualuminexapp.utils;

import com.google.gson.Gson;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppSettings {
    String userName;
    String email;
    String telephoneNumber;
    String gender;
    String password;
    String account_id;
    String profileImagePath;



    double waterMeterPercent;
    double electricityMeterPercent;

    public static final String CONFIG_FILE = "config.json";
    private static final String CONFIG_FOLDER_NAME = "AquaLuminexConfig";
    private static final File configFolder = new File(System.getProperty("user.home"), "Documents" + File.separator +
            "AquaLuminexConfig");



    public AppSettings(){
        userName = "null";
        email = "null";
        telephoneNumber = "0000000000";
        gender = "null";
        //password = "null";
        setPassword("null");
        account_id = "null";
        profileImagePath = "null";
        waterMeterPercent = 1.0;
        electricityMeterPercent = 1.0;
    }

    public static void initConfigProjectFolder(){
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
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void initConfigDocumentFolder(){
        //File configFolder = new File(System.getProperty("user.home"), "Documents" + File.separator +
                //"AquaLuminexConfig");

        // Create the AquaLuminexConfig folder if it doesn't exist
        if (!configFolder.exists()) {
            boolean folderCreated = configFolder.mkdirs();
            if (!folderCreated) {
                System.err.println("Failed to create the AquaLuminexConfig folder.");
                return;
            }
        }

        // Create the CONFIG file inside the AquaLuminexConfig folder
        File configFile = new File(configFolder, CONFIG_FILE);

        try (Writer writer = new FileWriter(configFile)) {
            System.out.println("CONFIG file is created");

            // Write your app settings to the CONFIG file using Gson
            AppSettings appSettings = new AppSettings();
            Gson gson = new Gson();
            gson.toJson(appSettings, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static AppSettings getAppSettings(){
        Gson gson = new Gson();
        AppSettings appSettings;
        File configFile = getConfigFile();
        try {
            appSettings = gson.fromJson(new FileReader(configFile), AppSettings.class);
        } catch (FileNotFoundException e) {
            initConfigDocumentFolder();
            throw new RuntimeException(e);
        }
        return appSettings;
    }
    private static File getConfigFile() {
        Path documentFolder = getDocumentFolderPath();
        File configFileFolder = new File(documentFolder.toString(), CONFIG_FOLDER_NAME);

        return new File(configFileFolder, CONFIG_FILE);
    }
    private static Path getDocumentFolderPath() {
        String userHome = System.getProperty("user.home");
        return Paths.get(userHome, "Documents");
    }

    public static void writeAppSettingsToConfig(AppSettings appSettings){
        //File configFolder = new File(System.getProperty("user.home"), "Documents" + File.separator +
                //"AquaLuminexConfig");

        // Create the AquaLuminexConfig folder if it doesn't exist
        if (!configFolder.exists()) {
            boolean folderCreated = configFolder.mkdirs();
            if (!folderCreated) {
                System.err.println("Failed to create the AquaLuminexConfig folder.");
                return;
            }
        }

        // Create the CONFIG file inside the AquaLuminexConfig folder
        File configFile = new File(configFolder, CONFIG_FILE);

        try (Writer writer = new FileWriter(configFile)) {
            System.out.println("CONFIG writing success");

            // Write your app settings to the CONFIG file using Gson
            Gson gson = new Gson();
            gson.toJson(appSettings, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        Thread hashPasswordThread = new Thread(() -> this.password = DigestUtils.sha256Hex(password));
        //hashPasswordThread.start();
        this.password = DigestUtils.sha256Hex(password);

    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public double getWaterMeterPercent() {
        return waterMeterPercent;
    }

    public void setWaterMeterPercent(double waterMeterPercent) {
        this.waterMeterPercent = waterMeterPercent;
    }

    public double getElectricityMeterPercent() {
        return electricityMeterPercent;
    }

    public void setElectricityMeterPercent(double electricityMeterPercent) {
        this.electricityMeterPercent = electricityMeterPercent;
    }
}