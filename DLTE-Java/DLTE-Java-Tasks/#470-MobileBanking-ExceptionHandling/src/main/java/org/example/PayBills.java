package org.example;

import java.util.ResourceBundle;
import java.util.Scanner;

public class PayBills {
    GPay gPay = new GPay();

    public boolean isValidPin(int pin) {
        return pin == gPay.getUpiPin();
    }

    public void payBill(String billerName, Double billedAmount, String billerType) {
        int attempts=0;
        Scanner scanner = new Scanner(System.in);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        System.out.println("Enter your UPI pin");
        int pin = scanner.nextInt();
        if (!isValidPin(pin)) {
            attempts++;
            if (attempts>=5) {
                throw new MyBankException("Account blocked");
            }
            else {
                throw new MyBankException("Wrong pin. "+attempts+" left");

            }
        }
        System.out.println("Bill of "+billedAmount+" to "+billerName+" and "+billerType+" successful");

    }
}
