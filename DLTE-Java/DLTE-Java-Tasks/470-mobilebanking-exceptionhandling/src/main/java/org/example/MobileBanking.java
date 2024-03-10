package org.example;

import java.util.Scanner;

public class MobileBanking {
    public static void main(String[] args) {
        GPay gPay = new GPay(567645374647L, 50000.0, "Nishmitha", "nish",123);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the biller name");
        String billerName=scanner.nextLine();
        System.out.println("Enter the biller amount");
        Double billerAmount=scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter the biller type");
        String billerType=scanner.nextLine();
        gPay.payBill(billerName, billerAmount, billerType);
    }
}
