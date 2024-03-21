package wire.di.springbootautowiring;

public class ExecutingAutowire {
    public static void main(String[] args) {
        LoanInterface loanInterface = new HomeLoanImplementation();
        LoanService loanService = new LoanService(loanInterface);
        System.out.println(loanService.callFindAll());

        loanInterface = new PersonalLoanImplementation();
        loanService = new LoanService(loanInterface);
        System.out.println(loanService.callFindAll());
    }
}
