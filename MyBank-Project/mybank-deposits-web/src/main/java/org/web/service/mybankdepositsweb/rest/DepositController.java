package org.web.service.mybankdepositsweb.rest;

import mybank.dao.mybankdeposits.entity.DepositsAvailable;
import mybank.dao.mybankdeposits.exception.DepositException;
import mybank.dao.mybankdeposits.interfaces.DepositInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLSyntaxErrorException;
import java.util.*;

@RestController
@RequestMapping("/deposit")
public class DepositController {
    @Autowired
    DepositInterface depositInterface;

    Logger logger = LoggerFactory.getLogger(DepositController.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    ResourceBundle messageBundle = ResourceBundle.getBundle("messages");


    @GetMapping("/view/{roi}")
    public ResponseEntity<?> getDepositsByRoi(@PathVariable("roi") double roi) {
        if (roi < 0) {
            logger.warn(messageBundle.getString("roi.negative"));
            return ResponseEntity.status(HttpStatus.OK).body(messageBundle.getString("roi.negative"));
        }
        try {
            List<DepositsAvailable> deposits = depositInterface.searchDepositsByRoi(roi);
            return ResponseEntity.ok().body(deposits);
        } catch (DepositException depositException) {
            String message = depositException.getMessage();
            logger.warn(resourceBundle.getString("deposit.exception") + ": " + message);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (SQLSyntaxErrorException syntaxException) {
            syntaxException.printStackTrace();
            logger.error(resourceBundle.getString("internal.error"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageBundle.getString("internal.error"));
        }
    }

}
