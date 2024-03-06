package org.example;

import java.util.ResourceBundle;
import java.util.Scanner;

public class GPay {
    private int upiPin;
    private String username;

    public GPay() {
    }

    public GPay(int upiPin, String username) {
        this.upiPin = upiPin;
        this.username = username;
    }

    public int getUpiPin() {
        return upiPin;
    }

    public void setUpiPin(int upiPin) {
        this.upiPin = upiPin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
