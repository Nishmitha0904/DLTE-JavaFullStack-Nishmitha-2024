package org.web.service.mybankdepositsweb.rest;

import mybank.dao.mybankdeposits.entity.DepositsAvailable;
import mybank.dao.mybankdeposits.exception.DepositException;
import mybank.dao.mybankdeposits.interfaces.DepositInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/deposit")
public class DepositController {
    @Autowired
    DepositInterface depositInterface;

    Logger logger = LoggerFactory.getLogger(DepositController.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @GetMapping("/view/{roi}")
    public List<DepositsAvailable> listDepositsByRoi(@PathVariable("roi") double roi) {
        List<DepositsAvailable> depositsList = new ArrayList<>();
        try {
            depositsList = depositInterface.searchDepositsByRoi(roi);
        } catch (SQLSyntaxErrorException e) {
            logger.error(resourceBundle.getString("internal.error"));
        } catch (DepositException depositException) {
            logger.warn(resourceBundle.getString("deposit.exception"));
        }
        return depositsList;
    }

}
