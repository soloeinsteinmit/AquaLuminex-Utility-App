package com.example.aqualuminexapp;

import com.example.aqualuminexapp.utils.GenerateAccountId;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final int SMTP_PORT = 587;
    private static final String SMTP_USERNAME = "solomoneinsteinmit300@gmail.com"; // Replace with your email address
    //    private static final String SMTP_USERNAME = "programming.coding.info@gmail.com"; // Replace with your email address
    private static final String SMTP_PASSWORD = "vzrnhaishwrvphzx"; // Replace with your email password

    public static void main(String[] args) {
        GenerateAccountId generateAccountId = new GenerateAccountId();
        String recipientEmail = "einsteinmit100@gmail.com"; // Replace with recipient's email address
        String subject = "Hello from JavaMail AquaLuminex";
        String message = "This is a test email sent from JavaMail. Your id is ";

        sendEmail(recipientEmail, subject, message);
    }

    public static void sendEmail(String recipientEmail, String subject, String message) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
            }
        });

        try {
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(SMTP_USERNAME));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            Transport.send(mimeMessage);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            System.out.println("Failed to send email. Error message: " + e.getMessage());
        }
    }
}