package explore.oop.tasks;

import java.util.Date;

public interface MyBank {

    Loan[] loans = {
                new Loan(16752361523423L, 50000.0, new Date(2024, 01, 12), "Open", "John", 7656765456L),
                new Loan(87463253635221L, 100000.0, new Date(2023, 10, 9), "Open", "Peter", 7647342389L),
                new Loan(37463782339867L, 80000.0, new Date(2021, 05, 10), "Closed", "Lauren", 7687345243L),
                new Loan(65478366452377L, 70000.0, new Date(2024, 02, 20), "Open", "Han", 8974563726L),
                new Loan(674563872634L, 150000.0, new Date(2023, 7, 2), "Closed", "Alice", 9874563785L),
    };

    public void addNewLoan();
    public void checkLoans();
    public void checkClosedLoans();
}
