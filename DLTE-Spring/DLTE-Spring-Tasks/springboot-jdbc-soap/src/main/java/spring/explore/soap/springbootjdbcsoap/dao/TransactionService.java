package spring.explore.soap.springbootjdbcsoap.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Transaction publishNewTransaction(Transaction transaction) {
        int ack = jdbcTemplate.update("insert into transaction values(?,?,?,?,?,?)",
                new Object[]{transaction.getTransactionId(), transaction.getTransactionDate(), transaction.getTransactionAmount(), transaction.getTransactionTo(),
                        transaction.getRemarks(), transaction.getTransactionBy()});
        if (ack!=0)
            return transaction;
        else
            return null;
    }

    public List<Transaction> findBySender(String sender) {
        List<Transaction> transactionList = jdbcTemplate.query
                ("select * from transaction where transaction_by=?",
                        new Object[]{sender}, new TransactionMapper());
        return transactionList;
    }

    public  List<Transaction> findByReceiver(String receiver) {
        List<Transaction> transactionList = jdbcTemplate.query
                ("select * from transaction where transaction_to=?",
                        new Object[]{receiver}, new TransactionMapper());
        return transactionList;
    }

    public List<Transaction> findByAmount(Double minAmount, Double maxAmount) {
        List<Transaction> transactionList = jdbcTemplate.query
                ("select * from transaction where transaction_amount between ? and ?",
                        new Object[]{minAmount, maxAmount}, new TransactionMapper());
        return transactionList;
    }

    public Transaction updateRemarks(Transaction transaction) {
        int ack = jdbcTemplate.update("update transaction set remarks=? where transaction_id=?",
                new Object[]{transaction.getRemarks(),transaction.getTransactionId()});
        if (ack!=0)
            return transaction;
        else
            return null;
    }

    public String deleteTransactionBetweenDates(Date startDate, Date endDate) {
        int acknowledge = jdbcTemplate.update("delete from transaction where transaction_date between ? and ?",
                new Object[]{startDate, endDate});
        if (acknowledge!=0)
            return "Transaction details deleted";
        else
            return "No transactions done";
    }

//    public String deleteTransactionBetweenDates(XMLGregorianCalendar startDate, XMLGregorianCalendar endDate) {
//        int acknowledge = jdbcTemplate.update("delete from transaction where transaction_date between ? and ?",
//                new Object[]{startDate, endDate});
//        if (acknowledge!=0)
//            return "Transaction details deleted";
//        else
//            return "No transactions done";
//    }


    protected class TransactionMapper implements RowMapper<Transaction> {

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
