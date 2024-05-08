package spring.explore.springjdbc;

public class LoanException extends RuntimeException {

    public LoanException() {
        super("Loan Details Not Available");
    }

    public LoanException(String info) {
        super("Loan Details Not Available " + info);
    }
}
