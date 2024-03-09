package org.example;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Scanner;

public class GPay extends Account {

    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    private Integer upiPin;

    public GPay(Long accountNumber, Double accountBalance, String accountHolder, Integer upiPin, String username) {
        super(accountNumber, accountBalance, accountHolder);
        this.upiPin = upiPin;
        this.username = username;
    }

    private String username;


    public void payBills(String billerName, Double billAmount, String billType) {
        Scanner scanner = new Scanner(System.in);
        Integer pin;
        int attempts = 0;
        while (attempts < 5) {
            System.out.println("Enter the UPI Pin");
            pin = scanner.nextInt();
            if (pin.equals(upiPin)) {
                Double accountBalance = billAmount;
                System.out.println(resourceBundle.getString("bill.success"));
                logger.log(Level.INFO, resourceBundle.getString("bill.success"));
                break;
            } else {
                attempts++;
                logger.log(Level.WARNING, resourceBundle.getString("pin.invalid"));
            }
        }
        if (attempts >= 5) {
            throw new MyBankException();
        }
    }
}
