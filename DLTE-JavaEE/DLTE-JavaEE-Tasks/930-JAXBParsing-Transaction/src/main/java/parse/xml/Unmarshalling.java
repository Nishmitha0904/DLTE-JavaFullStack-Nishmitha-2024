package parse.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Unmarshalling {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        JAXBContext context=JAXBContext.newInstance(Transaction.class);
        Unmarshaller unmarshaller= context.createUnmarshaller();
        Transaction transaction = (Transaction) unmarshaller.unmarshal(new FileInputStream("transactions.xml"));
        transaction.setAmountInTransaction(transaction.getAmountInTransaction()+200);
        System.out.println(transaction);

    }
}
