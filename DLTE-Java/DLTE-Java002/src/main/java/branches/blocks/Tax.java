package branches.blocks;

import java.util.Scanner;

public class Tax {
    public static void main(String[] args) {
        Double salary, tax=0.0;
        char regime;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the salary");
        salary = scanner.nextDouble();
        System.out.println("Choose the regime (old(o)/new(n))");
        regime = scanner.next().charAt(0);

        switch(regime) {
            case 'o':
                if (salary >= 0 && salary <= 250000) {
                    tax = salary * 0.05;
                } else if (salary > 250000 && salary <= 500000) {
                    tax = salary * 0.20;
                } else if (salary > 500000 && salary <= 750000) {
                    tax = salary * 0.20;
                } else if (salary > 750000 && salary <= 100000) {
                    tax = salary * 0.30;
                } else {
                    tax = salary * 0.30;
                }
                break;
            case 'n':
                if (salary >= 0 && salary <= 250000) {
                    tax = salary * 0.05;
                } else if (salary > 250000 && salary <= 500000) {
                    tax = salary * 0.10;
                } else if (salary > 500000 && salary <= 750000) {
                    tax = salary * 0.15;
                } else if (salary > 750000 && salary <= 100000) {
                    tax = salary * 0.20;
                } else if (salary > 100000 && salary <= 125000) {
                    tax = salary * 0.25;
                } else {
                    tax = salary * 0.30;
                }
                break;
        }
        System.out.println("The tax supposed to pay is " + tax);

    }


}
