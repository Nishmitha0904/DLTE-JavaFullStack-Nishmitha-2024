package basics.service;


import java.util.Scanner;

public class FDCalculator {
    public static void main(String[] args) {
        double principle, rate, time, fd;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the principle");
        principle = scanner.nextDouble();
        System.out.println("Enter the rate of interest");
        rate = scanner.nextDouble();
        System.out.println("Enter the time");
        time = scanner.nextDouble();
        fd = principle*rate*time;
    }
}
