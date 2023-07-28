package com.example.aqualuminexapp.utils;

import com.example.aqualuminexapp.register.AccountInfoController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SendEmail {

    private static final String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*" +
            "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static String ID;
    private static Calendar calendar;

    public SendEmail() {


    }


    public static Boolean emailValidate(String email) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String getID() {
        return ID;
    }


    public static void sendMail(String recipient) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "solomoneinsteinmit300@gmail.com";
        //Your gmail password
        String password = "vzrnhaishwrvphzx";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recipient);

        //Send mail
        Transport.send(message);
        System.out.println("message sent");


    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            GenerateAccountId generateAccountId = new GenerateAccountId();
            ID = generateAccountId.generateAccountId();


            String name = "Jad Gogovi Wossop 😎😎😎";


            System.out.println(getID() + " ID here");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("AquaLuminex Account-ID Verification");
            String sendMailHtml = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "	<head>\n" +
                    "		<meta charset=\"UTF-8\" />\n" +
                    "		<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
                    "		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                    "		<title>Send Email</title>\n" +
                    "		<style>\n" +
                    "			.top {\n" +
                    "				margin-bottom: 50px;\n" +
                    "			}\n" +
                    "\n" +
                    "			.top img {\n" +
                    "				width: 100%;\n" +
                    "				height: 100px;\n" +
                    "				position: relative;\n" +
                    "			}\n" +
                    "\n" +
                    "			.top h2 {\n" +
                    "				color: white;\n" +
                    "				position: absolute;\n" +
                    "				left: 50px;\n" +
                    "				top: 20px;\n" +
                    "				font-family: \"Segoe UI\", Tahoma, Geneva, Verdana, sans-serif;\n" +
                    "				font-weight: 300;\n" +
                    "			}\n" +
                    "\n" +
                    "			.middle p:first-child {\n" +
                    "				font-size: 20px;\n" +
                    "				font-family: \"Segoe UI\", Tahoma, Geneva, Verdana, sans-serif;\n" +
                    "				font-weight: 300;\n" +
                    "				margin-left: 40px;\n" +
                    "			}\n" +
                    "\n" +
                    "			.middle p:last-child {\n" +
                    "				font-size: 15px;\n" +
                    "				font-family: \"Segoe UI\", Tahoma, Geneva, Verdana, sans-serif;\n" +
                    "				font-weight: 300;\n" +
                    "				margin-left: 40px;\n" +
                    "			}\n" +
                    "\n" +
                    "			.bottom img {\n" +
                    "				width: 100%;\n" +
                    "				height: 100px;\n" +
                    "				margin-top: 50px;\n" +
                    "				position: relative;\n" +
                    "			}\n" +
                    "\n" +
                    "			.bottom p {\n" +
                    "				color: rgb(130, 139, 139);\n" +
                    "				font-size: 13px;\n" +
                    "				position: absolute;\n" +
                    "				left: 50px;\n" +
                    "				top: 390px;\n" +
                    "				font-family: \"Segoe UI\", Tahoma, Geneva, Verdana, sans-serif;\n" +
                    "				font-weight: 100;\n" +
                    "			}\n" +
                    "		</style>\n" +
                    "	</head>\n" +
                    "	<body>\n" +
                    "		<div class=\"top\">\n" +
                    "			<img src=\"https://images.pexels.com/photos/1184834/pexels-photo-1184834.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1\" alt=\"\" />\n" +
                    "			<h2><b>AquaLuminex</b></h2>\n" +
                    "		</div>\n" +
                    "\n" +
                    "		<div class=\"middle\">\n" +
                    "			<p>Hi <b>" + AccountInfoController.userName + "</b>,</p>\n" +
                    "			<p>\n" +
                    "				Use the OTP to verify your account. The OTP is valid for 15\n" +
                    "				minutes\n" +
                    "				<b>" + getID() + "</b>. <br />\n" +
                    "				<br />\n" +
                    "				OTP will expire on " + add15() + "<br />\n" +
                    "				<br />\n" +
                    "				Thanks for choosing us\n" +
                    "			</p>\n" +
                    "		</div>\n" +
                    "\n" +
                    "		<div class=\"bottom\">\n" +
                    "			<img src=\"https://images.pexels.com/photos/3183156/pexels-photo-3183156.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1\" alt=\"\" />\n" +
                    "			<p>\n" +
                    "				Copyright &copy; 2023, AquaLuminex and/or its affiliate. All\n" +
                    "				rights reserved. <br />\n" +
                    "				<br />\n" +
                    "				<span>Terms of Use | Privacy</span>\n" +
                    "			</p>\n" +
                    "		</div>\n" +
                    "	</body>\n" +
                    "</html>\n";

            message.setContent(sendMailHtml, "text/html");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Calendar getCalendar() {
        return calendar;
    }

    public static void setCalendar(Calendar calendar) {
        SendEmail.calendar = calendar;
    }

    public static String add15() {
        calendar = Calendar.getInstance();
        long time = calendar.getTimeInMillis();
        return new Date(time + (15 * 60 * 1000)).toString();
    }

    public static String current() {
        calendar = Calendar.getInstance();
        return calendar.getTime().toString();
    }

}