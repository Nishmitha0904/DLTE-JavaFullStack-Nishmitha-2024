package mybank.dao.mybankdeposits.interfaces;

import mybank.dao.mybankdeposits.entity.DepositsAvailable;
import mybank.dao.mybankdeposits.entity.DepositsAvailed;
import org.springframework.stereotype.Repository;

import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepositInterface {

    List<DepositsAvailable> listAllDeposits() throws SQLSyntaxErrorException;
    List<DepositsAvailable> searchDepositsByRoi(double roi) throws SQLSyntaxErrorException;
//    String proceduralSearch(double roi);
    Optional<DepositsAvailable> searchDepositById(long id);
    DepositsAvailed availDeposit(DepositsAvailed depositsAvailed);
}
