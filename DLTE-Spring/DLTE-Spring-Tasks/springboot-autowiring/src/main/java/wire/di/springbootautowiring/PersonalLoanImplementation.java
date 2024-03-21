package wire.di.springbootautowiring;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("personalLoan")
public class PersonalLoanImplementation implements LoanInterface {

    List<Loan> personalLoans = new ArrayList<>();
    @Override
    public List<Loan> findAll() {
        for (Loan loan: loanList) {
            if (loan.getLoanType().equalsIgnoreCase("personal")) {
                personalLoans.add(loan);
            }
        }
        return personalLoans;
    }
}
