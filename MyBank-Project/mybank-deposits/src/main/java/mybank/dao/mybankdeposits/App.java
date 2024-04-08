package mybank.dao.mybankdeposits;

import mybank.dao.mybankdeposits.service.DepositService;

import java.sql.SQLSyntaxErrorException;

public class App {
    public static void main(String[] args) throws SQLSyntaxErrorException {
        DepositService depositService = new DepositService();
        depositService.listAllDeposits();
    }
}
