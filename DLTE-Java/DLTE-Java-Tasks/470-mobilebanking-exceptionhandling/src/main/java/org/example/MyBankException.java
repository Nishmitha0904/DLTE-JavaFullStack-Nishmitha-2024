package org.example;

import java.util.ResourceBundle;

public class MyBankException extends RuntimeException {
    public MyBankException(String message) {
        super (ResourceBundle.getBundle("application").getString("exception.pin.wrong"));
    }
}
