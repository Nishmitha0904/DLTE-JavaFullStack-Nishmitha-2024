package mybank.dao.mybankdeposits.interfaces;

import mybank.dao.mybankdeposits.entity.DepositsAvailable;
import mybank.dao.mybankdeposits.entity.DepositsAvailed;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepositInterface {

    List<DepositsAvailable> listAllDeposits() throws SQLSyntaxErrorException;
    ResponseEntity<?> searchDepositsByRoi(Double roi) throws SQLSyntaxErrorException;
//        List<DepositsAvailable> searchDepositsByRoi(double roi) throws SQLSyntaxErrorException;
//    List<DepositsAvailable> searchDepositsByRoi(double roi);
//    String proceduralSearch(double roi);
    Optional<DepositsAvailable> searchDepositById(Long id);
    DepositsAvailed availDeposit(DepositsAvailed depositsAvailed);
}
