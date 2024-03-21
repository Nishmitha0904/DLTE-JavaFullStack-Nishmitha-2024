package spring.explore.springbootjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class MyBankService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Transaction apiSave(Transaction transaction) {
        int ack = jdbcTemplate.update("insert into transaction values(?,?,?,?,?,?)",
                new Object[]{
                        transaction.getTransactionId(),
                        transaction.getTransactionDate(),
                        transaction.getTransactionAmount(),
                        transaction.getTransactionTo(),
                        transaction.getRemarks(),
                        transaction.getTransactionBy()
                });
        if (ack!=0)
            return transaction;
        else
            throw new TransactionException(" Insertion failed");
    }

    public List<Transaction> apiFindBySender(String sender) {
        return jdbcTemplate.query("select * from transaction where transaction_by=?",
                new Object[]{sender},
                new TransactionMapper());
    }

    public List<Transaction> apiFindByReceiver(String receiver) {
        return jdbcTemplate.query("select * from transaction where transaction_to=?",
                new Object[]{receiver},
                new TransactionMapper());
    }

    public List<Transaction> apiFindByAmount(Double amount) {
        return jdbcTemplate.query("select * from transaction where transaction_amount=?",
                new Object[]{amount},
                new TransactionMapper());
    }

    private class TransactionMapper implements RowMapper<Transaction> {

        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transaction transaction = new Transaction();
            transaction.setTransactionId(rs.getLong(1));
            transaction.setTransactionDate(rs.getDate(2));
            transaction.setTransactionAmount(rs.getDouble(3));
            transaction.setTransactionTo(rs.getString(4));
            transaction.setRemarks(rs.getString(5));
            transaction.setTransactionBy(rs.getString(6));
            return transaction;
        }
    }

}

