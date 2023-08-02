package com.example.aqualuminexapp.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class PasswordEncryptDecrypt {

    // Number of iterations for the PBKDF2 algorithm
    private static final int ITERATIONS = 10000;
    // Key length for the generated encryption key
    private static final int KEY_LENGTH = 256;

    public static void main(String[] args) {
        String password = "your_password";

        // Encrypt the password
        String encryptedPassword = encryptPassword(password);
        System.out.println("Encrypted Password: " + encryptedPassword);

        // Decrypt the password (verify if it matches the original)
        boolean isMatch = verifyPassword(password, encryptedPassword);
        System.out.println("Is Password Match: " + isMatch);
    }

    public static String encryptPassword(String password) {
        try {
            // Generate a random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            // Hash the password using PBKDF2 with the random salt
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();

            // Combine the salt and hash to create the encrypted password
            byte[] combined = new byte[salt.length + hash.length];
            System.arraycopy(salt, 0, combined, 0, salt.length);
            System.arraycopy(hash, 0, combined, salt.length, hash.length);

            // Encode the combined byte array as a Base64 string
            return Base64.getEncoder().encodeToString(combined);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new RuntimeException("Error encrypting the password.", ex);
        }
    }

    public static boolean verifyPassword(String password, String encryptedPassword) {
        try {
            // Decode the Base64 string to get the combined byte array
            byte[] combined = Base64.getDecoder().decode(encryptedPassword);

            // Extract the salt and hash from the combined byte array
            byte[] salt = new byte[16];
            byte[] hash = new byte[combined.length - salt.length];
            System.arraycopy(combined, 0, salt, 0, salt.length);
            System.arraycopy(combined, salt.length, hash, 0, hash.length);

            // Hash the input password with the extracted salt
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] inputHash = factory.generateSecret(spec).getEncoded();

            // Compare the extracted hash with the hash of the input password
            return MessageDigest.isEqual(hash, inputHash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new RuntimeException("Error verifying the password.", ex);
        }
    }
}