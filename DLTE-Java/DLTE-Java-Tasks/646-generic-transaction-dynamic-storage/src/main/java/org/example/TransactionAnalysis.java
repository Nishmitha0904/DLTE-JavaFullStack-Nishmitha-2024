package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionAnalysis {

    static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {

        Transaction transaction1 = new Transaction(new Date(2024, 02, 20), 1000.0, "John", "Bills");
        Transaction transaction2 = new Transaction(new Date(2024, 03, 15), 5500.0, "Hazel", "Friend");
        Transaction transaction3 = new Transaction(new Date(2024, 01, 25), 35000.0, "Karan", "Emergency");
        Transaction transaction4 = new Transaction(new Date(2024, 02, 04), 15000.0, "Sara", "Family");
        Transaction transaction5 = new Transaction(new Date(2023, 12, 22), 7000.0, "Sara", "Emergency");

        TransactionAnalysis analysis = new TransactionAnalysis();
        transactions = (ArrayList<Transaction>) Stream.of(transaction1, transaction2, transaction3, transaction4, transaction5).collect(Collectors.toList());
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("MENU\n1. Filter the transactions based on a date range \n2. Find the least amount transferred \n3. Find the maximum amount transferred \n4. Sort based on property and order\n6. Exit");
            System.out.println("What do you want to do? Choose");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    //int start_date, end_date;
                    System.out.println("Enter the start date");
                    Date startDate = new Date(scanner.next());
                    System.out.println("Enter the end date");
                    Date endDate = new Date(scanner.next());
                    analysis.filterByDate(startDate, endDate);
                    break;
                case 2:
                    analysis.leastAmount();
                    break;
                case 3:
                    analysis.maximumAmount();
                    break;
                case 4:
                    System.out.println("Enter property(date/amount/remarks) and order(ascending/descending) in the form property:order");
                    String propertyAndOrder = scanner.next();
                    TransactionComparator transactionComparator = new TransactionComparator(propertyAndOrder);
                    Collections.sort(transactions, transactionComparator);
                    transactions.forEach(System.out::println);
                    break;
                default:
                    System.exit(0);
            }
        }
    }

    public void filterByDate(Date start_date, Date end_date) {
        List<Transaction> filteredList = transactions.stream().filter(each->each.getDateOfTransaction().after(start_date) && each.getDateOfTransaction().before(end_date)).collect(Collectors.toList());
        filteredList.forEach(Transaction -> {
            System.out.println(Transaction.toString());
        });
    }


    public void leastAmount() {
        Transaction least = transactions.stream().min(Comparator.comparingDouble(Transaction::getAmountInTransaction)).orElse(null);
        System.out.println("The least amount transferred is " + least.getAmountInTransaction());
    }

    public void maximumAmount() {
        Transaction max = transactions.stream().max(Comparator.comparingDouble(Transaction::getAmountInTransaction)).orElse(null);
        System.out.println("The maximum amount transferred is " + max.getAmountInTransaction());
    }

}
