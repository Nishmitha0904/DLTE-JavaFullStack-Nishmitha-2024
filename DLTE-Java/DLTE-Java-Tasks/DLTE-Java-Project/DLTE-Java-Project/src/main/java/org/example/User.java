package org.example;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String address;
    private String mailId;
    private Long contact;
    private Double balance;

    public User(String username, String password, String address, String mailId, Long contact, Double balance) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.mailId = mailId;
        this.contact = contact;
        this.balance = balance;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", mailId='" + mailId + '\'' +
                ", contact=" + contact +
                ", balance=" + balance +
                '}';
    }
}
