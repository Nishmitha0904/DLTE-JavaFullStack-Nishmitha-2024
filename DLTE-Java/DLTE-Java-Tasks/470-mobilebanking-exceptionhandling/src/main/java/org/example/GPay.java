package org.example;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GPay {
    private int upiPin;
    private String username;

    public GPay(int upiPin, String username) {
        this.upiPin = upiPin;
        this.username = username;
    }

    static Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public GPay() {
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("account-logs.txt", false);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void payBill(String billerName, Double billedAmount, String billerType) {
        int attempts=0;
        Scanner scanner = new Scanner(System.in);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        System.out.println(resourceBundle.getString("pin.enter"));
        int pin = scanner.nextInt();
//        while (attempts <= 5) {
            if (pin == upiPin) {
                System.out.println("Bill of "+billedAmount+" to "+billerName+" and "+billerType+" successful");
                logger.log(Level.INFO, resourceBundle.getString("bill.success"));
                return;
            } else {
                //throw new MyBankException(resourceBundle.getString("account.blocked"));
                attempts++;
                if (attempts>=5) {
                    logger.log(Level.WARNING, resourceBundle.getString("account.blocked"));
                    return;
                }
                else {
                    throw new MyBankException(resourceBundle.getString("account.blocked"));
                }
            }
//        }

    }
}
