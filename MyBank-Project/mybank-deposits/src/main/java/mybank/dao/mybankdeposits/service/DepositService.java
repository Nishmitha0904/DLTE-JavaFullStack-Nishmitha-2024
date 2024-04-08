package mybank.dao.mybankdeposits.service;

import mybank.dao.mybankdeposits.entity.DepositsAvailable;
import mybank.dao.mybankdeposits.entity.DepositsAvailed;
import mybank.dao.mybankdeposits.exception.DepositException;
import mybank.dao.mybankdeposits.interfaces.DepositInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
    @Override
    public List<DepositsAvailable> searchDepositsByRoi(double roi) {
        List<DepositsAvailable> deposits;
        deposits = jdbcTemplate.query("select deposit_id,deposit_name,deposit_roi,deposit_type,deposit_description from mybank_app_deposits_available",
                new BeanPropertyRowMapper<>(DepositsAvailable.class));
        List<DepositsAvailable> roiDeposits = deposits.stream().filter(deposit -> deposit.getDepositRoi()==roi).collect(Collectors.toList());

        return roiDeposits;
    }

    @Override
    public Optional<DepositsAvailable> searchDepositById(long id) {
        return null;
    }

    @Override
    public DepositsAvailed availDeposit(DepositsAvailed depositsAvailed) {
        return null;
    }
}
