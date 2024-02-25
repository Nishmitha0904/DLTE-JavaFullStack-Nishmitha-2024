package explore.oop.tasks;

import java.util.Scanner;

public class WithdrawPay {
    public static void main(String[] args) {
        Account account = new Account(3453234323432L, 10000.0, "Harry");
        DebitCard debitCard = new DebitCard(8574839576859L, 4532);

        Double withdrawAmount, billAmount;
        Scanner scanner = new Scanner(System.in);
        withdrawAmount = scanner.nextDouble();
        debitCard.withdraw(withdrawAmount, account);

        GPay gpay = new GPay(6543, "harry123");
        billAmount = scanner.nextDouble();
        gpay.payBill("DSF", 3000.0, "Electricity");
    }
}
