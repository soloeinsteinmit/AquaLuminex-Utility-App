package com.example.aqualuminexapp.utils;

import javax.comm.*;
import java.io.OutputStream;

public class SmsSender {

    public static void main(String[] args) {
        // Replace with the appropriate port name for your GSM modem
        String portName = "COM1"; // On Windows, the port name will be something like "COM1"
        int baudRate = 9600; // The baud rate used by your GSM modem

        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            if (portIdentifier.isCurrentlyOwned()) {
                System.out.println("Error: Port is currently in use");
            } else {
                CommPort commPort = portIdentifier.open(SmsSender.class.getName(), 2000);

                if (commPort instanceof SerialPort) {
                    SerialPort serialPort = (SerialPort) commPort;
                    serialPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                    // Prepare the message to be sent
                    String recipientPhoneNumber = "+233599901816"; // Replace with the recipient phone number
                    String messageBody = "Hello from Java! This is a test SMS.";

                    // Open the output stream to send the AT commands to the modem
                    OutputStream outputStream = serialPort.getOutputStream();

                    // Send AT commands to set up the modem and send the SMS
                    outputStream.write(("AT\r\n").getBytes());
                    outputStream.write(("AT+CMGF=1\r\n").getBytes()); // Set the modem to text mode
                    outputStream.write(("AT+CMGS=\"" + recipientPhoneNumber + "\"\r\n").getBytes());
                    outputStream.write((messageBody + "\r\n").getBytes());
                    outputStream.write((char) 26); // ASCII code for Ctrl+Z to send the SMS

                    // Wait for the modem response and read it if needed
                    // You can implement reading from the InputStream to get the response from the modem

                    // Close the output stream and the serial port
                    outputStream.close();
                    serialPort.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}