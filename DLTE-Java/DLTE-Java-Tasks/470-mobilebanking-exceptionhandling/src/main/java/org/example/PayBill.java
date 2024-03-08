package org.example;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class PayBill {

    static Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    public static void main(String[] args) {
        Account account = new Account(3453234323432L, 10000.0, "Harry");
        DebitCard debitCard = new DebitCard(8574839576859L, 4532);
        GPay gpay = new GPay(6543, "harry123");
        Scanner scanner = new Scanner(System.in);

        int attempts=0;
        while (attempts<=5) {
            try {
                gpay.payBill("DSF", 3000.0, "Electricity");
            } catch (MyBankException myBankException) {
                attempts++;
                logger.log(Level.WARNING, resourceBundle.getString("pin.invalid"));
                gpay.payBill("DSF", 3000.0, "Electricity");
            }
        }


    }
}
