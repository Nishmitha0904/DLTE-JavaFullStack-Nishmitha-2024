package org.example;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    static String testFilePath = "loans.doc";
    static List<Loan> loanList;

    MyBankLoans myBankLoans = new MyBankLoans();


    public static void initialize() {
        loanList = Stream.of(
                new Loan(37463782339867L, 80000.0, new Date(2021, 05, 10), "Closed", "Lauren", 7687345243L),
                new Loan(65478366452377L, 70000.0, new Date(2024, 02, 20), "Open", "Han", 8974563726L),
                new Loan(674563872634L, 150000.0, new Date(2023, 7, 2), "Closed", "Alice", 9874563785L),
                ).collect(Collectors.toList());
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(testFilePath));
            objectOutputStream.writeObject(loanList);
            objectOutputStream.close();
        } catch (IOException ioException) {}
    }

    public void testFetch() {
        assertSame("open", myBankLoans.checkClosedLoans());
    }
}
