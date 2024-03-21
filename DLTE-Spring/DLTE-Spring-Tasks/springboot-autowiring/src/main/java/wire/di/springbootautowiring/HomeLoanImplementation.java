package wire.di.springbootautowiring;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("homeLoan")
public class HomeLoanImplementation implements LoanInterface {

    List<Loan> homeLoans = new ArrayList<>();
    @Override
    public List<Loan> findAll() {
        for (Loan loan: loanList) {
            if (loan.getLoanType().equalsIgnoreCase("home")) {
                homeLoans.add(loan);
            }
        }
        return homeLoans;
    }
}
