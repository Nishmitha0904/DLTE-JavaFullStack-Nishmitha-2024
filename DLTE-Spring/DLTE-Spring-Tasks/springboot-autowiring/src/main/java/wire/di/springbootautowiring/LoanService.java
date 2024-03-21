package wire.di.springbootautowiring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.invoke.LambdaConversionException;
import java.util.List;

@Service
public class LoanService {
    private LoanInterface loanInterface;

    public LoanService() {
    }

    @Autowired
    public LoanService(@Qualifier("homeLoan") LoanInterface loanInterface) {
        this.loanInterface = loanInterface;
    }

    @Autowired
    @Qualifier("personalLoan")
    public void setLoanInterface(LoanInterface loanInterface) {
        this.loanInterface = loanInterface;
    }

    public List<Loan> callFindAll() {
        return loanInterface.findAll();
    }
}
