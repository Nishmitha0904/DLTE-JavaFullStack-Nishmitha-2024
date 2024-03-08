package org.example;


import java.util.ResourceBundle;

import static java.util.ResourceBundle.*;

public class MyBankCreditCardException extends RuntimeException {

    public MyBankCreditCardException(String message) {
        super (ResourceBundle.getBundle("creditcard").getString("credit.exception"));
    }

}
