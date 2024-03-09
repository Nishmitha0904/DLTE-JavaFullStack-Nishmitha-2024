package org.example;

import java.util.Comparator;

public class TransactionComparator implements Comparator<Transaction> {

    private String byAttribute;
    private String orderBy;

    public TransactionComparator(String userOrder) {
        String[] atThis = userOrder.split(":");
        byAttribute = atThis[0];
        orderBy = atThis[1];
    }

    @Override
    public int compare(Transaction o1, Transaction o2) {
        int returnedOrder=0;
        switch (byAttribute) {
            case "date": case "Date": case "DATE":
                returnedOrder=o1.getDateOfTransaction().compareTo(o2.getDateOfTransaction()); break;
            case "amount": case "Amount": case "AMOUNT":
                returnedOrder=o1.getAmountInTransaction().compareTo(o2.getAmountInTransaction()); break;
            case "remarks": case "Remarks": case "REMARKS":
                returnedOrder=o1.getRemarks().compareTo(o2.getRemarks()); break;
        }
        return orderBy.equals("ascending")?returnedOrder:-returnedOrder;
    }
}
