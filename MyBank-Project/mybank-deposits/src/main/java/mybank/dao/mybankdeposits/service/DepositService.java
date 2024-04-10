package mybank.dao.mybankdeposits.service;

import mybank.dao.mybankdeposits.entity.DepositsAvailable;
import mybank.dao.mybankdeposits.entity.DepositsAvailed;
import mybank.dao.mybankdeposits.exception.DepositException;
import mybank.dao.mybankdeposits.interfaces.DepositInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLSyntaxErrorException;
import java.sql.Types;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepositService implements DepositInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    Logger logger = LoggerFactory.getLogger(DepositService.class);

    @Override
    public List<DepositsAvailable> listAllDeposits() throws SQLSyntaxErrorException {
        List<DepositsAvailable> deposits;
        try {
            deposits = jdbcTemplate.query("select deposit_id,deposit_name,deposit_roi,deposit_type,deposit_description from mybank_app_deposits_available",
                    new BeanPropertyRowMapper<>(DepositsAvailable.class));
        } catch (DataAccessException sqlException) {
            throw new SQLSyntaxErrorException();
        }
        if (deposits.size()==0) {
            logger.warn(resourceBundle.getString("deposit.exception"));
            throw new DepositException(resourceBundle.getString("deposit.exception"));
        }

        else
            return deposits;
    }

    @Override
    public List<DepositsAvailable> searchDepositsByRoi(double roi) {
        List<DepositsAvailable> depositsList = new ArrayList<>();

        CallableStatementCreator creator = con -> {
            CallableStatement statement = con.prepareCall("{call read_deposits_by_roi(?,?,?,?,?,?)}");
            statement.setDouble(1,roi);
            statement.registerOutParameter(2, Types.NUMERIC);
            statement.registerOutParameter(3,Types.VARCHAR);
            statement.registerOutParameter(4,Types.VARCHAR);
            statement.registerOutParameter(5,Types.VARCHAR);
            statement.registerOutParameter(6, Types.VARCHAR);
            return statement;
        };



        List<SqlParameter> list= Arrays.asList(
                        new SqlParameter(Types.DOUBLE),
                        new SqlOutParameter("id",Types.LONGVARBINARY),
                        new SqlOutParameter("name",Types.VARCHAR),
                        new SqlOutParameter("type",Types.VARCHAR),
                        new SqlOutParameter("description",Types.VARCHAR),
                        new SqlOutParameter("deposit_info",Types.VARCHAR)
                );
        Map<String,Object> returnedDeposits = jdbcTemplate.call(creator,list);

        DepositsAvailable deposit = new DepositsAvailable();
        BigDecimal id = (BigDecimal) returnedDeposits.get("id");
        long longValue = id.longValue();
        deposit.setDepositId(longValue);
        deposit.setDepositName((String) returnedDeposits.get("name"));
        deposit.setDepositType((String) returnedDeposits.get("type"));
        deposit.setDepositDescription((String) returnedDeposits.get("description"));

        depositsList.add(deposit);

        return depositsList;
    }


//    @Override
//    public List<DepositsAvailable> searchDepositsByRoi(double roi) {
//        List<DepositsAvailable> deposits;
//        deposits = jdbcTemplate.query("select deposit_id,deposit_name,deposit_roi,deposit_type,deposit_description from mybank_app_deposits_available where deposit_roi=?",
//                new Object[]{roi},
//                new BeanPropertyRowMapper<>(DepositsAvailable.class));
//        if (deposits.size()==0)
//            throw new DepositException("EXC-002: No deposits with given return of interest");
//        else
//            return deposits;
//    }



//    @Override
//    public List<DepositsAvailable> searchDepositsByRoi(double roi) throws SQLSyntaxErrorException {
//        List<DepositsAvailable> deposits;
//        try {
//            deposits = jdbcTemplate.query("select deposit_id,deposit_name,deposit_roi,deposit_type,deposit_description from mybank_app_deposits_available",
//                    new BeanPropertyRowMapper<>(DepositsAvailable.class));
//        } catch (DataAccessException sqlException) {
//            logger.error(resourceBundle.getString("internal.error"));
//            throw new SQLSyntaxErrorException();
//        }
//
//        List<DepositsAvailable> roiDeposits = deposits.stream().filter(deposit -> deposit.getDepositRoi()==roi).collect(Collectors.toList());
//
//        if (roiDeposits.size()==0) {
//            logger.warn(resourceBundle.getString("deposit.exception"));
//            throw new DepositException(resourceBundle.getString("deposit.exception"));
//        } else
//            return roiDeposits;
//    }

    @Override
    public Optional<DepositsAvailable> searchDepositById(long id) {
        return null;
    }

    @Override
    public DepositsAvailed availDeposit(DepositsAvailed depositsAvailed) {
        return null;
    }
}
