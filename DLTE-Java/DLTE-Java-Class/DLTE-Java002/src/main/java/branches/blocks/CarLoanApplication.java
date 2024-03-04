package branches.blocks;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarLoanApplication {
    public static void main(String[] args) {
        String borrowerName="", borrowerPan="", borrowerAddress="", borrowerEmail="", borrowerIncomeType="";
        String mobileNumber, aadhaar;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to MyBank!");

        //while (true) {
            System.out.println("Enter your full name");
            borrowerName = scanner.nextLine();

            System.out.println("Enter your address");
            borrowerAddress = scanner.nextLine();

            System.out.println("Enter your mobile number");
            mobileNumber = scanner.next();
            while (!(isValidMobileNumber(mobileNumber))) {
                System.out.println("Invalid mobile number");
                //continue;
                mobileNumber = scanner.next();
            }

            System.out.println("Enter your PAN number (all capital)");
            borrowerPan = scanner.next();
            while (!(isValidPan(borrowerPan))) {
                System.out.println("Invalid PAN number");
                //continue;
                borrowerPan = scanner.next();
            }

            System.out.println("Enter your email ID");
            borrowerEmail = scanner.next();
            while (!(isValidEmail(borrowerEmail))) {
                System.out.println("Invalid email ID");
                //continue;
                borrowerEmail = scanner.next();
            }

            System.out.println("Enter your aadhaar number");
            aadhaar = scanner.next();
            while (!(isValidAadhaar(aadhaar))) {
                System.out.println("Invalid aadhaar number");
                //continue;
                aadhaar = scanner.next();
            }

            System.out.println("Enter borrower's income type (salaried/self employed)");
            borrowerIncomeType = scanner.next();

        System.out.println("Thank you for applying for the car loan in MyBank");

        //}




    }

    public static Boolean isValidPan(String borrowerPan) {
        String panExpression = "^[A-Z]{5}[0-9]{4}[A-Z]$";
        Pattern pattern = Pattern.compile(panExpression);
        Matcher matcher = pattern.matcher(borrowerPan);
        return matcher.matches();
    }

    public static Boolean isValidEmail(String borrowerEmail) {
        String emailExpression = "^[A-Za-z0-9+_.-]+@[a-zA-Z]+\\.com";
        Pattern pattern = Pattern.compile(emailExpression);
        Matcher matcher = pattern.matcher(borrowerEmail);
        return matcher.matches();
    }

    public static Boolean isValidMobileNumber(String mobileNumber) {
        String mobileExpression = "\\d{10}";
        Pattern pattern = Pattern.compile(mobileExpression);
        Matcher matcher = pattern.matcher(mobileNumber);
        return matcher.matches();
    }

    public static Boolean isValidAadhaar(String aadhaar) {
        String aadhaarExpression = "\\d{12}";
        Pattern pattern = Pattern.compile(aadhaarExpression);
        Matcher matcher = pattern.matcher(aadhaar);
        return matcher.matches();
    }
}
