package parse.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

public class Marshalling {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        Transaction transaction = new Transaction(new Date(2024, 02, 15), 8000.0, "Nishmitha", "Bills");
        JAXBContext context=JAXBContext.newInstance(Transaction.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(transaction,new FileOutputStream("transactions.xml"));
        System.out.println("XML is built");
    }
}

//    Transaction transaction = new Transaction(new Date(2024, 02, 15), 8000.0, "Nishmitha", "Bills");
//