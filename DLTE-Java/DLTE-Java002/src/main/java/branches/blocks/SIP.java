package branches.blocks;

import java.util.Scanner;

public class SIP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Double monthlyInvestment, expectedReturns, period;
        Double total, estimatedReturns, principleAmount;

        System.out.println("Enter the monthly investment");
        monthlyInvestment = scanner.nextDouble();
        System.out.println("Enter the expected returns");
        expectedReturns = scanner.nextDouble();
        System.out.println("Enter the period in years");
        period = scanner.nextDouble();

        expectedReturns = expectedReturns / (12*100);
        total = (monthlyInvestment*((Math.pow(1+expectedReturns, period*12)-1)/expectedReturns)*(1+expectedReturns));
    }
}
