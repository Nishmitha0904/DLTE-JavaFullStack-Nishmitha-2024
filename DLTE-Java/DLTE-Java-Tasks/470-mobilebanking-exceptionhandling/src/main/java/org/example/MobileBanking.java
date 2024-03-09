package org.example;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MobileBanking {
    public static void main(String[] args) {
        ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
        Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        GPay gPay=new GPay(65767856456L, 40000.0, "Nishmitha", 456, "nishmitha");

        Scanner scanner=new Scanner(System.in);

        try {
            System.out.println("Enter the biller name");
            String billerName = scanner.nextLine();
            System.out.println("Enter the  biller type");
            String billType = scanner.nextLine();
            System.out.println("Enter the bill amount ");
            Double billAmount = scanner.nextDouble();
            gPay.payBills(billerName, billAmount, billType);
        }catch (MyBankException e){
            logger.log(Level.WARNING,e.toString());
        }

    }

}
