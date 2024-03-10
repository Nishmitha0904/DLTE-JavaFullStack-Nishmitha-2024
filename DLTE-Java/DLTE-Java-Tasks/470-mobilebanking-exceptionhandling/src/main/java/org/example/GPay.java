package org.example;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Scanner;

public class GPay extends Account {

    private String username;
    private int upiPin;

    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    public GPay(String username, int upiPin) {
        this.username = username;
        this.upiPin = upiPin;
    }

    public GPay(Long accountNumber, Double accountBalance, String accountHolder, String username, int upiPin) {
        super(accountNumber, accountBalance, accountHolder);
        this.username = username;
        this.upiPin = upiPin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUpiPin() {
        return upiPin;
    }

    public void setUpiPin(int upiPin) {
        this.upiPin = upiPin;
    }

    public void payBill(String billerName, double billerAmount, String billerType) {
        Scanner scanner = new Scanner(System.in);
        int attempts=0;
        while (attempts<5) {
            try {
                System.out.println(resourceBundle.getString("pin.enter"));
                int pin = scanner.nextInt();
                if (pin==getUpiPin()) {
                    System.out.println(resourceBundle.getString("bill.success"));
                    logger.log(Level.INFO, resourceBundle.getString("bill.success"));
                    return;
                }
                else {
                    throw new MyBankException();
                }
            } catch (MyBankException exception) {
                System.out.println(resourceBundle.getString("bill.not.success"));
                logger.log(Level.WARNING, resourceBundle.getString("exception.pin.wrong"));
                attempts++;
                System.out.println();
            }
        }
        if (attempts==5) {
            System.out.println(resourceBundle.getString("account.blocked"));
            logger.log(Level.WARNING, resourceBundle.getString("account.blocked"));
        }
    }
}
