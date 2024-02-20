package branches.blocks;

import java.util.Scanner;

public class Debits {
    public static void main(String[] args) {
        Integer transaction=0, newTransaction, debit=0;
        Scanner scanner = new Scanner(System.in);

        for(int i=10;i>0;i--) {
            System.out.println("Enter the transaction");
            newTransaction = scanner.nextInt();
            if (newTransaction < transaction)
                debit += 1;
            transaction = newTransaction;
        }
        System.out.println("Total debits is "+debit);

    }
}
