package org.example;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Before
    public void initialize() {
        MyBankLoans myBankLoans = new MyBankLoans();
    }

    @Test
    public void testAddNewLoan() {
        MyBankLoans myBankLoans = new MyBankLoans();
        Loan loan = new Loan(345437856746L, 50000.0, new Date(2024, 13, 9), "open", "Nishmitha", 98764563746L);
        myBankLoans.loanList.add(loan);

        assertSame("open", myBankLoans.loanList.get(0).getLoanStatus());
        assertEquals(loan.getLoanAmount(), myBankLoans.loanList.get(0).getLoanAmount());
    }

    

    @Test (timeout = 500)
    public void testAddLoan() {
        MyBankLoans myBankLoans = new MyBankLoans();
        Loan loan = new Loan(345437856746L, 50000.0, new Date(2024, 13, 9), "open", "Nishmitha", 98764563746L);
        myBankLoans.loanList.add(loan);

        assertFalse(myBankLoans.loanList.contains(loan));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testException() {
        testAddLoan();
    }


}
