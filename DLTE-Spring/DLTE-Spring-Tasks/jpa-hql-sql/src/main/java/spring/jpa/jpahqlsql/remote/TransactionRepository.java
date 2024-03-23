package spring.jpa.jpahqlsql.remote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.jpa.jpahqlsql.model.Transactions;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, String> {

    Transactions save(Transactions transaction);

    @Query(value = "from transactions where transactionDoneby = :user and transactionType = :type", nativeQuery = true)
    List<Transactions> findByTransactionDonebyAndTransactionType(String user, String type);

//    @Query(value = "from transactions where transaction_amount between :amount1 and :amount2")
//    List<Transactions> findByAmountRange(double amount1, double amount2);

    @Query(value = "SELECT t FROM Transactions t WHERE t.transactionAmount BETWEEN :amount1 AND :amount2")
    List<Transactions> findByTransactionAmountBetween(double amount1, double amount2);
}
