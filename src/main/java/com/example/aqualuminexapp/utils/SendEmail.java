package com.example.aqualuminexapp.utils;

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
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("AquaLuminex Account-ID Verification");
            String html = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Send Email</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div class=\"top\" style=\"margin-bottom: 50px;\">\n" +
                    "        <img src=\"background.png\" alt=\"\"\n" +
                    "        width=\"100%\" height=\"100\" \n" +
                    "        style=\"\n" +
                    "        position: relative;\" >\n" +
                    "        \n" +
                    "        \n" +
                    "        <h2 style=\"color: white;\n" +
                    "        position: absolute; left: 50px; top: 20px;\n" +
                    "        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                    "        font-weight: 300;\n" +
                    "        \">i<b style=\"font-size: 25px;\n" +
                    "        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                    "       font-weight: 500;\n" +
                    "       \">Manage</b>School</h2>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <div class=\"middle\">\n" +
                    "        <p style=\"font-size: 20px;\n" +
                    "         font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                    "        font-weight: 300;\n" +
                    "        margin-left: 40px;\n" +
                    "        \"\n" +
                    "        > Hi <b style=\"font-size: 20px;\n" +
                    "        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                    "       font-weight: 450;\n" +
                    "       \"></b>,</p>\n" +
                    "\n" +
                    "        <p style=\"font-size: 15px;\n" +
                    "        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                    "       font-weight: 300;\n" +
                    "       margin-left: 40px;\n" +
                    "       \">Use the OTP to verify yout account. The OTP is valid for 15 minutes\n" +
                    "       <b style=\"font-size: 15px;\n" +
                    "       font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                    "      font-weight: 500;\n" +
                    "      \">" + generateAccountId.generateAccountId() + "</b>. <br> <br>OPT will expire on " + add15() + "<br>\n" +
                    "      <br> Thanks for choosing us \n" +
                    "        </p>\n" +
                    "\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <div class=\"bottom\">\n" +
                    "        <img src=\"pexels-sebastian-sørensen-750225.jpg\" width=\"100%\" height=\"100px\"  alt=\"\"\n" +
                    "        style=\"margin-top: 50px;\n" +
                    "        position: relative;\">\n" +
                    "\n" +
                    "        <p\n" +
                    "        style=\"color: rgb(130, 139, 139);\n" +
                    "        font-size: 13px;\n" +
                    "        position: absolute; left: 50px;top: 390px;\n" +
                    "        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                    "        font-weight: 100;\n" +
                    "        \">Copyright &copy; 2022, iManageSchool and/or its affliate.\n" +
                    "         All right reserved. <br> <br> <span\n" +
                    "         style=\"color: rgb(216, 224, 224);\n" +
                    "         font-weight: 500;\"> Terms of Use | Privacy</span> </p>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "</html>";

            message.setContent(html, "text/html");
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