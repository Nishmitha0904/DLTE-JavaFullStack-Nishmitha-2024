package org.web.service.mybankdepositsweb.rest;

import mybank.dao.mybankdeposits.entity.DepositsAvailable;
import mybank.dao.mybankdeposits.exception.DepositException;
import mybank.dao.mybankdeposits.interfaces.DepositInterface;
import mybank.dao.mybankdeposits.service.MyBankCustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLSyntaxErrorException;
import java.util.*;

@RestController
@RequestMapping("/deposit")
public class DepositController {
    @Autowired
    DepositInterface depositInterface;
    @Autowired
    MyBankCustomerService myBankCustomerService;

    Logger logger = LoggerFactory.getLogger(DepositController.class);
    ResourceBundle messageBundle = ResourceBundle.getBundle("messages");


    @GetMapping("/view/{roi}")
    public ResponseEntity<?> getDepositsByRoi(@PathVariable("roi") String strRoi) {
        //Validation
        if (!strRoi.matches("^\\d*\\.?\\d+$")) {
            logger.warn(messageBundle.getString("roi.format"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageBundle.getString("roi.format"));
        }
        Double roi = Double.valueOf(strRoi);
        try {
            List<DepositsAvailable> deposits = depositInterface.searchDepositsByRoi(roi);
            return ResponseEntity.ok().body(deposits);
        } catch (DepositException depositException) {
            String message = depositException.getMessage();
            logger.warn(messageBundle.getString("deposit.exception") + ": " + message);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (SQLSyntaxErrorException syntaxException) {
            syntaxException.printStackTrace();
            logger.error(messageBundle.getString("internal.error"));
            return ResponseEntity.status(HttpStatus.OK).body(messageBundle.getString("internal.error"));
        }
    }

    @GetMapping("/name")
    public String getCustomerName() {
        String name = getUser();
        String user = myBankCustomerService.getCustomerName(name);
        return user;
    }
    public String getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return name;
    }
}
