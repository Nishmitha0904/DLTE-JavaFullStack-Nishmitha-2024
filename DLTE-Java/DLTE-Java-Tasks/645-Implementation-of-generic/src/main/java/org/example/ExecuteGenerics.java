package org.example;

import java.util.Date;

public class ExecuteGenerics {

    public static void main(String[] args) {
        MyBankDatabase<CreditCard> creditCardMyBankDatabase = new MyBankDatabase<>();
        MyBankDatabase<Transaction> transactionMyBankDatabase = new MyBankDatabase<>();

        creditCardMyBankDatabase.myObjects=new CreditCard[4];
        transactionMyBankDatabase.myObjects=new Transaction[3];

        //CreditCard executions
        CreditCard creditCard1 = new CreditCard(6785649876564L,"Nishmitha Shetty",new Date(2036,9,23),787,50000,new Date(2024,4,15),new Date(2024,5,28),3223);
        CreditCard creditCard2 = new CreditCard(8765678765678L,"Sinchana",new Date(2034,12,30),565,100000,new Date(2024,3,11),new Date(2024,03,30),2316);
        CreditCard creditCard3 = new CreditCard(7654556987656L,"Shreya Poojary",new Date(2029,5,5),993,80000,new Date(2024,9,9),new Date(2024,10,28),2234);
        CreditCard creditCard4 = new CreditCard(8756312542453L,"Medhini Shetty",new Date(2040,11,17),667,110000,new Date(2024,6,24),new Date(2024,7,12),2312);

        System.out.println(creditCardMyBankDatabase.insertNewRecord(creditCard1));
        System.out.println(creditCardMyBankDatabase.insertNewRecord(creditCard2));
        System.out.println(creditCardMyBankDatabase.insertNewRecord(creditCard3));
        System.out.println(creditCardMyBankDatabase.insertNewRecord(creditCard4));

        creditCardMyBankDatabase.viewAll();

        System.out.println(creditCardMyBankDatabase.read(3));

        System.out.println(creditCardMyBankDatabase.delete(1));

        creditCardMyBankDatabase.update(0, new CreditCard(6785649876564L,"Nishmitha Shetty",new Date(2036,9,23),345,50000,new Date(2024,5,25),new Date(2024,7,18),1223));
        creditCardMyBankDatabase.viewAll();

        //Transaction Executions
        Transaction transaction1 = new Transaction(new Date(2024, 02, 20),1000.0, "John", "Bills");
        Transaction transaction2 = new Transaction(new Date(2024, 03, 15),5500.0, "Hazel", "Friend");
        Transaction transaction3 = new Transaction(new Date(2024, 01, 25),35000.0,"Karan","Emergency");

        System.out.println(transactionMyBankDatabase.insertNewRecord(transaction1));
        System.out.println(transactionMyBankDatabase.insertNewRecord(transaction2));
        System.out.println(transactionMyBankDatabase.insertNewRecord(transaction3));

        transactionMyBankDatabase.viewAll();

        System.out.println(transactionMyBankDatabase.read(1));
        System.out.println(transactionMyBankDatabase.delete(2));
        transactionMyBankDatabase.update(0, new Transaction(new Date(2024, 02, 20),1000.0, "John", "Family"));
        transactionMyBankDatabase.viewAll();

    }
}
