package explore.oop.tasks;

import java.util.Scanner;

public class GPay {
    private int upiPin;
    private String username;

    public GPay(int upiPin, String username) {
        this.upiPin = upiPin;
        this.username = username;
    }

    public void payBill(String billerName, Double billedAmount, String billerType) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your UPI pin");
        int pin = scanner.nextInt();
        if (pin == upiPin) {
            System.out.println("Bill of "+billedAmount+" to "+billerName+" and "+billerType+" successful");
        } else {
            System.out.println("Bill payment not successful");
        }
    }
}
