import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ManagedBean
@ApplicationScoped
public class LoanService {

    List<Loan> loans = new ArrayList<>();

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    @PostConstruct
    public void init() {
        Loan[] loansList = {
                new Loan(12345L,50000.0,"20/04/2024","Open","Nishmitha",7847548947L),
                new Loan(67809L,35000.0,"17/02/2024","Closed","Dhrithi",9847384799L),
                new Loan(47584L,85000.0,"05/04/2024","Open","Stuti",8748393473L),
                new Loan(78475L,45000.0,"23/12/2023","Closed","Sristi",7859485738L)
        };
        loans.addAll(Stream.of(loansList).collect(Collectors.toList()));
    }

    public String addLoan(Loan loan) {
        loans.add(loan);
        return "Insertion Successful";
    }

    public List<Loan> listClosedLoans() {
        List<Loan> closedLoans = loans.stream().filter(loan -> loan.getLoanStatus().equalsIgnoreCase("closed")).collect(Collectors.toList());
        return closedLoans;
    }

    public String deleteLoan(Long loanNumber) {
        loans.remove(loanNumber);
        return "Deletion successful";
    }

}
