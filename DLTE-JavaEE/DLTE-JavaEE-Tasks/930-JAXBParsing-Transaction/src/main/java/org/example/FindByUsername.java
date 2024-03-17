package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class FindByUsername {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        JAXBContext context = JAXBContext.newInstance(MyBankTransactions.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        MyBankTransactions myBankTransactions = (MyBankTransactions) unmarshaller.unmarshal(new FileInputStream("transactions.xml"));

        List<Transaction> transactionList = myBankTransactions.getTransactions();

        System.out.println("Enter the username");
        String user = scanner.next();

        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionDoneBy().equalsIgnoreCase(user)) {
                System.out.println(transaction.getTransactionDoneBy()+" "+transaction.getAmountInTransaction()+" "+transaction.getDateOfTransaction()+" "+transaction.getTransactionTo());
            }
        }
    }
}
