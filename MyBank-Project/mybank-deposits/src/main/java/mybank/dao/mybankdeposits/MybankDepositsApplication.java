package mybank.dao.mybankdeposits;

import mybank.dao.mybankdeposits.interfaces.DepositInterface;
import mybank.dao.mybankdeposits.service.DepositService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLSyntaxErrorException;

@SpringBootApplication
public class MybankDepositsApplication {

    public static void main(String[] args) throws SQLSyntaxErrorException {
        SpringApplication.run(MybankDepositsApplication.class, args);
        }
    }


