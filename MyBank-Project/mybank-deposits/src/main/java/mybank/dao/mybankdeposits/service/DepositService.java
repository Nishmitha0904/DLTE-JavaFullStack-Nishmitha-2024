package mybank.dao.mybankdeposits.service;

import mybank.dao.mybankdeposits.entity.DepositsAvailable;
import mybank.dao.mybankdeposits.exception.DepositException;
import mybank.dao.mybankdeposits.interfaces.DepositInterface;
import oracle.jdbc.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;


@Service
public class DepositService implements DepositInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    ResourceBundle messageBundle = ResourceBundle.getBundle("messages");
    Logger logger = LoggerFactory.getLogger(DepositService.class);

    @Override
    public List<DepositsAvailable> searchDepositsByRoi(Double roi) throws SQLSyntaxErrorException {
        try {
            CallableStatementCreator creator = con -> {
                CallableStatement statement = con.prepareCall("{call read_deposits_by_roi(?, ?)}");
                statement.setDouble(1, roi);
                statement.registerOutParameter(2, OracleTypes.CURSOR);
                return statement;
            };
            Map<String, Object> returnedDeposits = jdbcTemplate.call(creator, Arrays.asList(
                    new SqlParameter[]{
                            new SqlParameter(Types.NUMERIC),
                            new SqlOutParameter("deposit_data", OracleTypes.CURSOR)
                    }
            ));
            ArrayList<DepositsAvailable> deposits = (ArrayList<DepositsAvailable>) returnedDeposits.get("deposit_data");
            if (deposits.size() == 0) {
                logger.warn(messageBundle.getString("deposit.exception"));
                throw new DepositException(messageBundle.getString("deposit.exception"));
            }
            logger.info(messageBundle.getString("roi.fetch.success"));
            return deposits;
        } catch (DataAccessException sqlException) {
            logger.error(messageBundle.getString("internal.error"));
            throw new SQLSyntaxErrorException();
        }
    }

    //Listing all deposits -> For Soap
    @Override
    public List<DepositsAvailable> listAllDeposits() throws SQLSyntaxErrorException {
        List<DepositsAvailable> deposits;
        try {
            deposits = jdbcTemplate.query("select deposit_id,deposit_name,deposit_roi,deposit_type,deposit_description from mybank_app_deposits_available",
                    new DepositsMapper());
        } catch (DataAccessException sqlException) {
            throw new SQLSyntaxErrorException();
        }
        if (deposits.isEmpty()) {
            logger.warn(messageBundle.getString("deposit.exception"));
            throw new DepositException(messageBundle.getString("deposit.exception"));
        }
        else
            return deposits;
    }

    public static class DepositsMapper implements RowMapper<DepositsAvailable> {

        @Override
        public DepositsAvailable mapRow(ResultSet rs, int rowNum) throws SQLException {
            DepositsAvailable deposit = new DepositsAvailable();
            deposit.setDepositId(rs.getLong("deposit_id"));
            deposit.setDepositName(rs.getString("deposit_name"));
            deposit.setDepositRoi(rs.getDouble("deposit_roi"));
            deposit.setDepositType(rs.getString("deposit_type"));
            deposit.setDepositDescription(rs.getString("deposit_description"));
            return deposit;
        }
    }
}


