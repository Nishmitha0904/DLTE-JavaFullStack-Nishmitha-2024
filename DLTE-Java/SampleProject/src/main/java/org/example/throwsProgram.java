package org.example;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class throwsProgram {
    public static void sampleMethod() throws InputMismatchException {
        //System.out.println("Hello");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter age");
        int age = scanner.nextInt();
    }

    public static void main(String[] args) {
        try {
            sampleMethod();
        } catch (InputMismatchException inputException) {
            System.out.println(inputException);
        }
    }
}
