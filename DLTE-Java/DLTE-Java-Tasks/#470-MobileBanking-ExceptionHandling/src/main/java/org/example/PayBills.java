package org.example;

import java.util.ResourceBundle;
import java.util.Scanner;

public class PayBills {
    GPay gPay = new GPay();

    public static void main(String[] args) {
        Account account = new Account(3453234323432L, 10000.0, "Harry");
        GPay gPay = new GPay(1234, "ice21");
        PayBills payBills = new PayBills();
        payBills.payBill("Harry", 5000.0, "Education");
    }

    public boolean isValidPin(int pin) {
        return pin == gPay.getUpiPin();
    }

    public void payBill(String billerName, Double billedAmount, String billerType) {
        int attempts=0;
        Scanner scanner = new Scanner(System.in);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        System.out.println(resourceBundle.getString("pin.enter"));
        int pin = scanner.nextInt();
        while (attempts<=5) {
            if (!isValidPin(pin)) {
                attempts++;
                if (attempts>=5) {
                    throw new MyBankException(resourceBundle.getString("account.blocked"));
                }
                else {
                    System.out.println(resourceBundle.getString("pin.invalid"));
                    System.out.println(resourceBundle.getString("pin.enter"));
                    pin = scanner.nextInt();
                }
            }
        }

        System.out.println("Bill of "+billedAmount+" to "+billerName+" and "+billerType+" successful");

    }
}
