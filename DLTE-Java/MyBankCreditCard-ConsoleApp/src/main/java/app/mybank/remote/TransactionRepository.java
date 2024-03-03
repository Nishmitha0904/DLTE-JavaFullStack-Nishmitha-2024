package app.mybank.remote;

import app.mybank.entity.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionRepository {

    void save(Transaction transaction);
    List<Transaction> findAll();
    List<Transaction> findAllByCreditCard(Long cardNumber);
    List<Transaction> findAllByDate(Date date);
    List<Transaction> findAllByMerchant(Integer merchantId);
}
