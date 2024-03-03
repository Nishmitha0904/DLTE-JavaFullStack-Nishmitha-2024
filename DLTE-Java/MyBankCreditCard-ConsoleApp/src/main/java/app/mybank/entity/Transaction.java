package app.mybank.entity;

import java.util.Date;

//transaction model/entity/business model
public class Transaction {
    private Integer transactionID;
    private Date transactionDate;
    private Long transactionDoneBy;
    private Double transactionAmount;
    private Integer merchant;

    public Integer getMerchant() {
        return merchant;
    }

    public void setMerchant(Integer merchant) {
        this.merchant = merchant;
    }

    public Transaction() {
    }

    public Transaction(Integer transactionID, Date transactionDate, Long transactionDoneBy, Double transactionAmount, Integer merchant) {
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
        this.transactionDoneBy = transactionDoneBy;
        this.transactionAmount = transactionAmount;
        this.merchant = merchant;
    }

    public Transaction(Date transactionDate, Long transactionDoneBy, Double transactionAmount, String transactionReceiver) {

        this.transactionDate = transactionDate;
        this.transactionDoneBy = transactionDoneBy;
        this.transactionAmount = transactionAmount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", transactionDate=" + transactionDate +
                ", transactionDoneBy=" + transactionDoneBy +
                ", transactionAmount=" + transactionAmount +
                '}';
    }

    public Integer getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getTransactionDoneBy() {
        return transactionDoneBy;
    }

    public void setTransactionDoneBy(Long transactionDoneBy) {
        this.transactionDoneBy = transactionDoneBy;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }


}
