package org.example;

public class throwProgram {
    static void sampleMethod() {
        try {
            throw new NullPointerException();
        } catch (NullPointerException exception) {
            System.out.println("Exception caught in sample method");
            //throw exception;
        }
    }

    public static void main(String[] args) {
        try {
            sampleMethod();
        } catch (NullPointerException exception) {
            System.out.println("Exception caught in main function");
        }
    }
}
