package mybank.dao.mybankdeposits.entity;

import java.util.Date;

public class DepositsAvailed {
    private long depositAvailId;
    private double depositedAmount;
    private int depositedDuration;
    private Date depositMaturity;
    private long customerId;
    private long depositId;

    public long getDepositAvailId() {
        return depositAvailId;
    }

    public void setDepositAvailId(long depositAvailId) {
        this.depositAvailId = depositAvailId;
    }

    public double getDepositedAmount() {
        return depositedAmount;
    }

    public void setDepositedAmount(double depositedAmount) {
        this.depositedAmount = depositedAmount;
    }

    public int getDepositedDuration() {
        return depositedDuration;
    }

    public void setDepositedDuration(int depositedDuration) {
        this.depositedDuration = depositedDuration;
    }

    public Date getDepositMaturity() {
        return depositMaturity;
    }

    public void setDepositMaturity(Date depositMaturity) {
        this.depositMaturity = depositMaturity;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getDepositId() {
        return depositId;
    }

    public void setDepositId(long depositId) {
        this.depositId = depositId;
    }

    public DepositsAvailed(long depositAvailId, double depositedAmount, int depositedDuration, Date depositMaturity, long customerId, long depositId) {
        this.depositAvailId = depositAvailId;
        this.depositedAmount = depositedAmount;
        this.depositedDuration = depositedDuration;
        this.depositMaturity = depositMaturity;
        this.customerId = customerId;
        this.depositId = depositId;
    }

    public DepositsAvailed() {
    }
}
