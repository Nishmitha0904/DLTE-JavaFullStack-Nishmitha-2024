package basics.service;

import java.util.Scanner;

public class MobileBanking {
    public static void main(String[] args) {
        String name, email, password;
        Long mobileNumber;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your full name");
        name = scanner.nextLine();
        System.out.println("Enter your email id");
        email = scanner.next();
        System.out.println("Enter your mobile number");
        mobileNumber = scanner.nextLong();
        System.out.println("Create a password with atleast 8 characters");
        password = scanner.next();

        scanner.close();
    }
}
