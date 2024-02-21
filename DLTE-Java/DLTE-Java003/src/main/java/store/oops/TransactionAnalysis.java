package store.oops;

import java.util.Date;
import java.util.Scanner;

public class TransactionAnalysis {
    public static void main(String[] args) {
        Transaction[] transaction = {
                new Transaction(new Date(2024, 02, 20),1000.0, "John", "Bills"),
                new Transaction(new Date(2024, 03, 15),5500.0, "Hazel", "Friend"),
                new Transaction(new Date(2024, 01, 25),35000.0,"Karan","Emergency"),
                new Transaction(new Date(2024, 02, 04),15000.0,"Sara","Family"),
                new Transaction(new Date(2023, 12, 22),7000.0,"Sara","Emergency"),
        };

        String beneficiary;
        Date startDate, endDate;
        Scanner scanner = new Scanner(System.in);

        TransactionAnalysis analysis = new TransactionAnalysis();
        System.out.println("MENU\n1. Filter the transactions based on a date range \n 2. Find the least amount transferred \n 3. Find the maximum amount transferred \n 4. Find the number of transaction made to particular beneficiary \n 5. Filter the transactions based on remarks");
        System.out.println("What do you want to do? Choose");
        int choice = scanner.nextInt();
        switch(choice) {
            case 1:
                System.out.println("Enter the start date");
                startDate = new Date();
                System.out.println("Enter the end date");
                endDate = new Date();

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                System.out.println("Enter a beneficiary to check the number of transactions made");
                beneficiary = scanner.next();
                analysis.numberOfTransactions(transaction, beneficiary);
                break;
            case 5:
                break;
        }
        //number of transaction made to particular beneficiary

    }

    //Filter based on given ranges of date

    //least amount transferred
    public void leastAmount(Transaction[] transaction) {
        Double leastAmount = 0.0;

        for (Transaction each : transaction) {

        }
    }

    //maximum amount transferred

    //number of transaction made to particular beneficiary
    public void numberOfTransactions(Transaction[] transaction, String beneficiary) {
        int numberOfTransactions=0;
        for (Transaction each : transaction) {
            if (each.getTransactionTo() == beneficiary) {
                numberOfTransactions += 1;
            }
        }
        System.out.println("The total number of transactions made to "+beneficiary+" is "+numberOfTransactions);
    }

    //filter based on particular remarks
}
