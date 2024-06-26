package spring.explore.springbootjdbc.entity;

import java.util.Date;
import java.util.stream.Stream;

public class Transaction {
    private Long transactionId;
    private Date transactionDate;
    private Double transactionAmount;
    private String transactionTo;
    private String remarks;
    private String transactionBy;

    public Transaction() {
    }

    public Transaction(Long transactionId, Date transactionDate, Double transactionAmount, String transactionTo, String remarks, String transactionBy) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.transactionTo = transactionTo;
        this.remarks = remarks;
        this.transactionBy = transactionBy;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTransactionBy() {
        return transactionBy;
    }

    public void setTransactionBy(String transactionBy) {
        this.transactionBy = transactionBy;
    }
}
