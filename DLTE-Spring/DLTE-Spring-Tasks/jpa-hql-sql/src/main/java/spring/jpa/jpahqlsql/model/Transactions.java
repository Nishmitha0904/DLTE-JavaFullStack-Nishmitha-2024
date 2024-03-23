package spring.jpa.jpahqlsql.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Transactions {
    @Id
    private String transactionDoneby;
    private String transactionType;
    private double transactionAmount;
    @Temporal(TemporalType.DATE)
    private Date transactionDate;

    public Transactions() {
    }

    public Transactions(String transactionDoneby, String transactionType, double transactionAmount, Date transactionDate) {
        this.transactionDoneby = transactionDoneby;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
    }

    public String getTransactionDoneby() {
        return transactionDoneby;
    }

    public void setTransactionDoneby(String transactionDoneby) {
        this.transactionDoneby = transactionDoneby;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionDoneby='" + transactionDoneby + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
