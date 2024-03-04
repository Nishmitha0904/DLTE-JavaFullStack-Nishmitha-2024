package store.oops;

import java.util.Date;
import java.util.Scanner;

public class CreditCardAnalysis {
    public static void main(String[] args) {
        CreditCard[] myBank = {
                new CreditCard(6785649876564L,"Nishmitha Shetty",new Date(2036,9,23),787,50000,new Date(2024,4,15),new Date(2024,5,28),3223),
                new CreditCard(8765678765678L,"Sinchana",new Date(2034,12,30),565,100000,new Date(2024,3,11),new Date(2024,03,30),2316),
                new CreditCard(7654556987656L,"Shreya Poojary",new Date(2029,5,5),993,80000,new Date(2024,9,9),new Date(2024,10,28),2234),
                new CreditCard(8756312542453L,"Medhini Shetty",new Date(2040,11,17),667,110000,new Date(2024,6,24),new Date(2024,7,12),2312),
        };

        Scanner scanner = new Scanner(System.in);

        CreditCardAnalysis analysis = new CreditCardAnalysis();
        //Filter based on limit
        System.out.println("Enter the start limit");
        int startLimit = scanner.nextInt();
        System.out.println("Enter the end limit");
        int endLimit = scanner.nextInt();
        analysis.limitBasedList(myBank, startLimit, endLimit);
        //Filter based on bill date payment
        System.out.println("Enter the date of bill payment");
        Integer date = scanner.nextInt();
        analysis.findBillDate(myBank, date);
        // Update pin of customer
        System.out.println("Do you want to change your pin? (yes/no)");
        String pinChange = scanner.next();
        if (pinChange == "yes")
            analysis.updatePin(myBank);
//        analysis.updatePin(myBank, 3223, 4554);
        // Update the limit
        System.out.println("Enter percent increase in limit");
        Integer increasePercent = scanner.nextInt();
        analysis.updateLimit(myBank, increasePercent);
    }

    public void limitBasedList(CreditCard[] customers, Integer startLimit, Integer endLimit) {
        System.out.println("The customers having limit between "+startLimit+" and "+endLimit);
        for (CreditCard each:customers) {
            if (each.getCreditCardLimit()>=startLimit && each.getCreditCardLimit()<=endLimit) {
                System.out.println(each.getCreditCardHolder());
            }
        }
    }

    public void findBillDate(CreditCard[] customers, Integer date){
        System.out.println("Customers having date of bill payment on "+ date);
        for(CreditCard each:customers){
            if(each.getDateOfBillPayment().getDate() == date){
                System.out.println(each.getCreditCardHolder());
            }
        }
    }

//    public void updatePin(CreditCard[] customers, Integer oldPin, Integer newPin) {
//        for (int select=0;select<=customers.length;select++) {
//            if (customers[select].getCreditCardPin() == oldPin) {
//                customers[select].setCreditCardPin(newPin);
//            }
//        }
//    }

    public void updatePin(CreditCard[] customers) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the old pin");
        int oldPin = scanner.nextInt();
        System.out.println("Enter the new pin");
        int newPin = scanner.nextInt();
        for (int select=0;select<=customers.length;select++) {
            if (customers[select].getCreditCardPin() == oldPin) {
                customers[select].setCreditCardPin(newPin);
            }
        }
    }

    public void updateLimit(CreditCard[] customers, Integer increasePercent) {
        for (int select=0;select<=customers.length;select++) {
            if (customers[select].getDateOfBillGeneration() == new Date(5)) {
                int limit = customers[select].getCreditCardLimit() * (increasePercent/100);
                customers[select].setCreditCardLimit(limit);
            }
        }
    }


}
