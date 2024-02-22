package branches.blocks;

import java.util.Scanner;

public class InsuranceProvider {
    public static void main(String[] args) {
        String[] insure = {"Health", "Home", "Accidental", "Disability"};
        String[] abc = {"Accidental", "Travel", "General"};
        String[] inr = {"Home", "Health", "Vehicle", "Fire", "General"};

        String insuranceType;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the type of insurance that you want");
        insuranceType = scanner.nextLine();

        for (String each : insure) {
            if (each.equals(insuranceType)) {
                System.out.println("Insure");
            }
        }
        for (String each : abc) {
            if (each.equals(insuranceType)) {
                System.out.println("ABC");
            }
        }
        for (String each : inr) {
            if (each.equals(insuranceType)) {
                System.out.println("INR");
            }
        }


    }
}
