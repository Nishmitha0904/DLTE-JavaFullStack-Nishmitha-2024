package org.example;

import java.util.Stack;

public class IllustrationOfStack {
    public static void main(String[] args) {
        Stack<String> stringStack=new Stack<>();
        stringStack.push("Education Loan");
        stringStack.push("Vehicle Loan");
        stringStack.push("Personal Loan");
        stringStack.push("Property against Loan");
        stringStack.push("Gold Loan");
        stringStack.push("Housing Loan");
        stringStack.push("Business Loan");

        System.out.println(stringStack.peek()); //element at top

        stringStack.pop(); //remove from top
        System.out.println(stringStack);

        System.out.println(stringStack.search("Business Loan"));

        stringStack.clear();  //removes all elements
        System.out.println(stringStack.isEmpty());
    }
}
