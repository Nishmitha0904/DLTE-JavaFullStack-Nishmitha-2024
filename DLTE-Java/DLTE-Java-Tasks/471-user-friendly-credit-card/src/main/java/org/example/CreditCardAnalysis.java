package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CreditCardAnalysis {

    public CreditCardAnalysis() {
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("credit-card-logs.txt", true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("creditcard");
    static Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static void main(String[] args) {
        CreditCard[] myBank = {
                new CreditCard(6785649876564L,"Nishmitha Shetty",new Date(2036,9,23),787,50000,new Date(2024,4,15),new Date(2024,5,28),3223),
                new CreditCard(8765678765678L,"Sinchana",new Date(2034,12,30),565,100000,new Date(2024,3,11),new Date(2024,03,30),2316),
                new CreditCard(7654556987656L,"Shreya Poojary",new Date(2029,5,5),993,80000,new Date(2024,9,9),new Date(2024,10,28),2234),
                new CreditCard(8756312542453L,"Medhini Shetty",new Date(2040,11,17),667,110000,new Date(2024,6,24),new Date(2024,7,12),2312),
        };

        Scanner scanner = new Scanner(System.in);
        CreditCardAnalysis analysis = new CreditCardAnalysis();

        System.out.println(resourceBundle.getString("menu"));
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter the start limit");
                int startLimit = scanner.nextInt();
                System.out.println("Enter the end limit");
                int endLimit = scanner.nextInt();
                try {
                    analysis.findByLimit(myBank, startLimit, endLimit);
                } catch (MyBankCreditCardException creditCardException) {
                    //System.out.println("Exception occurred");
                    logger.log(Level.WARNING, resourceBundle.getString("holder.not.found"));
                }
                break;
            case 2:
                System.out.println(resourceBundle.getString("enter.date"));
                Integer date = scanner.nextInt();
                try {
                    analysis.findByBillDate(myBank, date);
                } catch (MyBankCreditCardException creditCardException) {
                    logger.log(Level.WARNING, resourceBundle.getString("holder.not.found"));
                }

                break;
            default:
                System.exit(0);
        }

    }

    public void findByLimit(CreditCard[] customers, Integer startLimit, Integer endLimit) {
        ArrayList<String> limitList = new ArrayList<>();
        for (CreditCard each:customers) {
            if (each.getCreditCardLimit()>=startLimit && each.getCreditCardLimit()<=endLimit) {
                //System.out.println(each.getCreditCardHolder());
                limitList.add(each.getCreditCardHolder());
            }
        }
        if (limitList.size() == 0)
            throw new MyBankCreditCardException(resourceBundle.getString("holder.not.found"));
        else {
            System.out.println("The customers having limit between "+startLimit+" and "+endLimit+":");
            System.out.println(limitList);
            logger.log(Level.INFO, resourceBundle.getString("card.limit.success"));
        }

    }

    public void findByBillDate(CreditCard[] customers, Integer date){
        ArrayList<String> dateList = new ArrayList<>();
        for(CreditCard each:customers){
            if(each.getDateOfBillPayment().getDate() == date){
                dateList.add(each.getCreditCardHolder());
            }
        }
        if (dateList.size() == 0)
            throw new MyBankCreditCardException(resourceBundle.getString("holder.not.found"));
        else {
            System.out.println("Customers having date of bill payment on "+ date + ":");
            System.out.println(dateList);
            logger.log(Level.INFO, resourceBundle.getString("card.date.success"));
        }
    }

}
