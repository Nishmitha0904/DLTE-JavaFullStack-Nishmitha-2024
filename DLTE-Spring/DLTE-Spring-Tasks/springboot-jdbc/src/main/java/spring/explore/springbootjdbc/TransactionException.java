package spring.explore.springbootjdbc;

public class TransactionException extends RuntimeException {
    public TransactionException() {
        super("Trasaction Not Done");
    }
    public TransactionException(String info) {
        super("Transaction Not Done "+info);
    }
}
