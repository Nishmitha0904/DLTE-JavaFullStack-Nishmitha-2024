package basics.service;

import java.util.Scanner;

public class NetBanking {
    public static void main(String[] args) {
        Long accountNumber, pin, depositAmount, withdrawalAmount;
        String transactionType, confirmation;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome");
        System.out.println("Enter account number");
        accountNumber = scanner.nextLong();
        System.out.println("Enter pin");
        pin = scanner.nextLong();
        System.out.println("Enter the transaction type (deposit or withdraw or check balance");
        transactionType = scanner.nextLine();
        if (transactionType=="deposit") {
            System.out.println("Enter deposit amount");
            depositAmount = scanner.nextLong();
        } else if (transactionType=="withdrawal") {
            System.out.println("Enter withdrawal amount");
            withdrawalAmount = scanner.nextLong();
        }
        System.out.println("Confirm transaction (Yes/No)");
        confirmation = scanner.next();

        scanner.close();
    }
}
