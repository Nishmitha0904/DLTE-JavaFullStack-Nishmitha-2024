package org.example;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class MyBankTransactions {
    private List<Transaction> transactions;

    public MyBankTransactions() {
    }

    public MyBankTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @XmlElement(name = "transaction")
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "MyBankTransactions{" +
                "transactions=" + transactions +
                '}';
    }
}
