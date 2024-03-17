package org.example;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Transaction {

    private String transactionDoneBy;
    private Date dateOfTransaction;
    private Double amountInTransaction;
    private String transactionTo;
    private String remarks;

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionDoneBy='" + transactionDoneBy + '\'' +
                ", dateOfTransaction=" + dateOfTransaction +
                ", amountInTransaction=" + amountInTransaction +
                ", transactionTo='" + transactionTo + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    public Transaction() {
    }

    public Transaction(String transactionDoneBy, Date dateOfTransaction, Double amountInTransaction, String transactionTo, String remarks) {
        this.transactionDoneBy = transactionDoneBy;
        this.dateOfTransaction = dateOfTransaction;
        this.amountInTransaction = amountInTransaction;
        this.transactionTo = transactionTo;
        this.remarks = remarks;
    }

    @XmlAttribute(name = "by")
    public String getTransactionDoneBy() {
        return transactionDoneBy;
    }

    public void setTransactionDoneBy(String transactionDoneBy) {
        this.transactionDoneBy = transactionDoneBy;
    }

    @XmlElement(name="date")
    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    @XmlElement(name = "amount")
    public Double getAmountInTransaction() {
        return amountInTransaction;
    }

    public void setAmountInTransaction(Double amountInTransaction) {
        this.amountInTransaction = amountInTransaction;
    }

    @XmlElement(name = "receiver")
    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    @XmlElement
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}
