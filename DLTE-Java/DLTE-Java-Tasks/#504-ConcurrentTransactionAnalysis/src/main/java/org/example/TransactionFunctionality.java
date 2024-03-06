package org.example;


public interface TransactionFunctionality {
    void filterByDate(Transaction[] transactions, int startDate, int endDate);
    void leastAmount(Transaction[] transactions);
    void maximumAmount(Transaction[] transactions);
    void numberOfTransaction(Transaction[] transactions, String beneficiary);
    void remarksBasedList(Transaction[] transactions, String remark);

}
