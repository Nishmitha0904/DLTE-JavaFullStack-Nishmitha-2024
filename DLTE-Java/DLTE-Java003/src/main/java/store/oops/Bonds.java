package store.oops;

import java.util.Date;

public class Bonds {
    private Double maturity;
    private Double interestRate;
    private Boolean taxStatus;
    private String bondHolder;
    private Integer period;

    public Bonds() {
    }

    public Bonds(Double maturity, Double interestRate, Boolean taxStatus, String bondHolder, Integer period) {
        this.maturity = maturity;
        this.interestRate = interestRate;
        this.taxStatus = taxStatus;
        this.bondHolder = bondHolder;
        this.period = period;
    }

    @Override
    public String toString() {
        return "Bonds{" +
                "maturity=" + maturity +
                ", interestRate=" + interestRate +
                ", taxStatus='" + taxStatus + '\'' +
                ", bondHolder='" + bondHolder + '\'' +
                ", period=" + period +
                '}';
    }

    public Double getMaturity() {
        return maturity;
    }

    public void setMaturity(Double maturity) {
        this.maturity = maturity;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Boolean getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(Boolean taxStatus) {
        this.taxStatus = taxStatus;
    }

    public String getBondHolder() {
        return bondHolder;
    }

    public void setBondHolder(String bondHolder) {
        this.bondHolder = bondHolder;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}
