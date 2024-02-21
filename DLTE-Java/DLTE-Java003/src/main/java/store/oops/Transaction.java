package store.oops;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private Date dateOfTransaction;
    private Double amountInTransaction;
    private String transactionTo;
    private String remarks;

    @Override
    public String toString() {
        return "Transaction{" +
                "dateOfTransaction=" + dateOfTransaction +
                ", amountInTransaction=" + amountInTransaction +
                ", transactionTo='" + transactionTo + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
