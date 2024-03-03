package org.example;

import java.util.ResourceBundle;
import java.util.Scanner;

public class GPay {
    private int upiPin;
    private String username;

    public GPay(int upiPin, String username) {
        this.upiPin = upiPin;
        this.username = username;
    }

    public boolean isValidPin(int pin) {
        return pin == upiPin;
    }

    public void payBill(String billerName, Double billedAmount, String billerType) {
        Scanner scanner = new Scanner(System.in);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        System.out.println("Enter your UPI pin");
        int pin = scanner.nextInt();
        try {
            if (!isValidPin(pin)) {
                throw new MyBankException();
            }

        } catch (MyBankException exception) {
            
        }
//        if (pin == upiPin) {
//            System.out.println("Bill of "+billedAmount+" to "+billerName+" and "+billerType+" successful");
//        } else {
//            System.out.println("Bill payment not successful");
//        }
    }
}
