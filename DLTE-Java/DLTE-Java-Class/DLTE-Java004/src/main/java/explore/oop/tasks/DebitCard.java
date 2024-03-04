package explore.oop.tasks;

import java.util.Scanner;

public class DebitCard {
    private Long cardNumber;
    private int cardPin;

    public DebitCard(Long cardNumber, int cardPin) {
        this.cardNumber = cardNumber;
        this.cardPin = cardPin;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardPin() {
        return cardPin;
    }

    public void setCardPin(int cardPin) {
        this.cardPin = cardPin;
    }

    public Boolean verifyPin(int pin) {
        if (pin == cardPin)
            return true;
        else
            return false;
    }

    public void withdraw (double amount, Account account) {
        int pin;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the card pin");
        pin = scanner.nextInt();
        if (verifyPin(pin) && amount <= account.getAccountBalance()) {
            System.out.println("Withdraw approved");
        } else {
            System.out.println("Withdrawal not approved");
        }
    }
}
