package wire.di.springbootautowiring;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface LoanInterface {
    List<Loan> loanList = Stream.of(
            new Loan(16752361523423L, 50000.0, "Home", "Open", "John", 7656765456L),
            new Loan(87463253635221L, 100000.0, "Personal", "Open", "Peter", 7647342389L),
            new Loan(37463782339867L, 80000.0, "Personal", "Closed", "Lauren", 7687345243L),
            new Loan(65478366452377L, 70000.0, "Home", "Open", "Han", 8974563726L),
            new Loan(674563872634L, 150000.0, "Home", "Closed", "Alice", 9874563785L)
    ).collect(Collectors.toList());

    List<Loan> findAll();
}
