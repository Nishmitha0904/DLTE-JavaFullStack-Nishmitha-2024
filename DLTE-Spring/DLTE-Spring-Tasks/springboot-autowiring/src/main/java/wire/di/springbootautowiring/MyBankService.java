package wire.di.springbootautowiring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBankService {

    @Autowired
    @Qualifier("homeLoan")
    private LoanInterface loanInterface;

    public List<Loan> executeFindAll() {
        return loanInterface.findAll();
    }

}
