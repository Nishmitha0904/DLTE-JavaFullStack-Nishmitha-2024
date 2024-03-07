package org.example;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class PayBill {


    public static void main(String[] args) {
        Account account = new Account(3453234323432L, 10000.0, "Harry");
        DebitCard debitCard = new DebitCard(8574839576859L, 4532);
        GPay gpay = new GPay(6543, "harry123");

        gpay.payBill("DSF", 3000.0, "Electricity");
    }
}
