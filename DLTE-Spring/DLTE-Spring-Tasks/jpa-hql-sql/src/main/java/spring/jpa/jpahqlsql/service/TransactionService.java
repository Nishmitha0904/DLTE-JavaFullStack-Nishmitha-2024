package spring.jpa.jpahqlsql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.jpa.jpahqlsql.model.Transactions;
import spring.jpa.jpahqlsql.remote.TransactionRepository;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public Transactions callSave(Transactions transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transactions> callFindByTransactionDonebyAndTransactionType(String user, String type) {
        return transactionRepository.findByTransactionDonebyAndTransactionType(user, type);
    }

    public List<Transactions> callFindByTransactionAmount(double amount1, double amount2) {
        return transactionRepository.findByTransactionAmountBetween(amount1, amount2);
    }
}
