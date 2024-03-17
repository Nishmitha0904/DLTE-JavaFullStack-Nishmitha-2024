package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionXML {

    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        List<Transaction> transactions = Stream.of(
                new Transaction("Hazel", new Date(2024, 02, 20),1000.0, "John", "Bills"),
                new Transaction("Sara", new Date(2024, 03, 15),5500.0, "Hazel", "Friend"),
                new Transaction("Sara", new Date(2024, 01, 25),35000.0,"Karan","Emergency"),
                new Transaction("Nishmitha", new Date(2024, 02, 04),15000.0,"Sara","Family"),
                new Transaction("Nishmitha", new Date(2023, 12, 22),7000.0,"Sara","Emergency")
        ).collect(Collectors.toList());

        MyBankTransactions myBankTransactions = new MyBankTransactions(transactions);

        JAXBContext context = JAXBContext.newInstance(MyBankTransactions.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(myBankTransactions, new FileOutputStream("transactions.xml"));
        System.out.println("XML built");

    }


}
