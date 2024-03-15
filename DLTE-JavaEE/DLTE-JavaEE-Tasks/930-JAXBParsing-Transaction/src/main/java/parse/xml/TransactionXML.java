package parse.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionXML {

    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        List<Transaction>  transactions = Stream.of(
                new Transaction(new Date(2024, 02, 20),1000.0, "John", "Bills"),
                new Transaction(new Date(2024, 03, 15),5500.0, "Hazel", "Friend"),
                new Transaction(new Date(2024, 01, 25),35000.0,"Karan","Emergency"),
                new Transaction(new Date(2024, 02, 04),15000.0,"Sara","Family"),
                new Transaction(new Date(2023, 12, 22),7000.0,"Sara","Emergency")
        ).collect(Collectors.toList());

        MyBankTransactions myBankTransactions = new MyBankTransactions(transactions);

        JAXBContext context = JAXBContext.newInstance(MyBankTransactions.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        MyBankTransactions myTransactionList = (MyBankTransactions) unmarshaller.unmarshal(new FileInputStream("transactions.xml"));
        myTransactionList.getMyTransactions().forEach(System.out::println);
    }
}
