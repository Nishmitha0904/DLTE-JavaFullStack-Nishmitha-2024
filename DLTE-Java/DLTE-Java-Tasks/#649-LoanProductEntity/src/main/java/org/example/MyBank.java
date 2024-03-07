package org.example;

import java.util.ArrayList;
import java.util.Date;

public interface MyBank {


    public void readFromFile();
    public void writeIntoFile();
    public Loan addNewLoan(Loan loan);
    public void checkAvailableLoans();
    public void checkClosedLoans();

}
