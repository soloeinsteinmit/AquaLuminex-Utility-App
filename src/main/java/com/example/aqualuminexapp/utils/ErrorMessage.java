package com.example.aqualuminexapp.utils;

/**
 * Used for return from method validation
 *
 * @author © Deimos Coding Projects
 */
public class ErrorMessage {

    static private boolean error = false; // Was there an error
    static private String errMsg = ""; // Error Message to be display if error

    /**
     * Constructor to define method return with
     */
    public ErrorMessage() {
    }

    /**
     * Constructor to assign return from method
     *
     * @param error  - Was there an error
     * @param errMsg - Error Message to be display if error
     */
    public ErrorMessage(boolean error, String errMsg) {
        ErrorMessage.error = error;
        ErrorMessage.errMsg = errMsg;
    }

    /**
     * @return the error
     */
    public static boolean isError() {
        return error;
    }

    /**
     * @return the errMsg
     */
    public static String getErrMsg() {
        return errMsg;
    }
}