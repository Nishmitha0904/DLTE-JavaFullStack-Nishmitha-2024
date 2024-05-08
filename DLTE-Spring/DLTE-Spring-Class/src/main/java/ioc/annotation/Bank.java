package ioc.annotation;

import org.springframework.context.annotation.Bean;

//@Bean
public class Bank {
    private String bankName;
    private String headQuarters;
    private String bankId;

    @Override
    public String toString() {
        return "Bank{" +
                "bankName='" + bankName + '\'' +
                ", headQuarters='" + headQuarters + '\'' +
                ", bankId='" + bankId + '\'' +
                '}';
    }

    public Bank() {
    }

    public Bank(String bankName, String headQuarters, String bankId) {
        this.bankName = bankName;
        this.headQuarters = headQuarters;
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getHeadQuarters() {
        return headQuarters;
    }

    public void setHeadQuarters(String headQuarters) {
        this.headQuarters = headQuarters;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
}
