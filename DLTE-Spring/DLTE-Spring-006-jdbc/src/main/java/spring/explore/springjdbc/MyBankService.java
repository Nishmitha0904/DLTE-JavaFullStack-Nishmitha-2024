package spring.explore.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Service
public class MyBankService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Loan> apiFindAll() throws SQLSyntaxErrorException {

       List<Loan> loans = jdbcTemplate.query("select * from loans", new LoanMapper());
       if (loans.size()==0)
           throw new LoanException();
       return loans;
    }

    private class LoanMapper implements RowMapper<Loan> {

        @Override
        public Loan mapRow(ResultSet rs, int rowNum) throws SQLException {
            Loan loan = new Loan();
            loan.setLoanNumber(rs.getLong(1));
            loan.setLoanAmount(rs.getDouble(2));
            loan.setLoanType(rs.getString(3));
            loan.setLoanStatus(rs.getString(4));
            loan.setBorrowerName(rs.getString(5));
            loan.setBorrowerContact(rs.getLong(6));
            return loan;
        }
    }
}
