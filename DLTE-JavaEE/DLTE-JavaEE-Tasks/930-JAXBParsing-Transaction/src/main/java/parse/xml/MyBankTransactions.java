package parse.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement (name = "transactions")
public class MyBankTransactions {

    private List<Transaction> myTransactions;

    public MyBankTransactions() {
    }

    public MyBankTransactions(List<Transaction> myTransactions) {
        this.myTransactions = myTransactions;
    }

    @XmlElement
    public List<Transaction> getMyTransactions() {
        return myTransactions;
    }

    public void setMyTransactions(List<Transaction> myTransactions) {
        this.myTransactions = myTransactions;
    }
}
