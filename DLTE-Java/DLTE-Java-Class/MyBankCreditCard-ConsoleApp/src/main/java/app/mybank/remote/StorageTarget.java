package app.mybank.remote;

import app.mybank.entity.CreditCard;

public interface StorageTarget {
    CreditCardRepository getCreditCardRepository();
    TransactionRepository getTransactionRepository();
}
