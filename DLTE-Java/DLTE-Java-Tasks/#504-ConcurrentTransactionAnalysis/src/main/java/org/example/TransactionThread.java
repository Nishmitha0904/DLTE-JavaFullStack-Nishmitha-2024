package org.example;


public class TransactionThread {
    TransactionAnalysis transactionAnalysis = new TransactionAnalysis();
    Transaction transaction = new Transaction();
    Thread threadOne = new Thread(transactionAnalysis::filterByDate(transaction,),)
}
