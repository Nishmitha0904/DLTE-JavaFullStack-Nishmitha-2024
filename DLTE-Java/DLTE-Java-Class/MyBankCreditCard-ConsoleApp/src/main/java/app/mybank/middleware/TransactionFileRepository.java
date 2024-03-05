package app.mybank.middleware;

import app.mybank.entity.Transaction;
import app.mybank.remote.TransactionRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class TransactionFileRepository implements TransactionRepository {

    private String filePath;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("transaction");
    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private List<Transaction> transactionList = new ArrayList<>();

    public TransactionFileRepository(String url) {
        filePath=url;
        try {
            FileHandler fileHandler = new FileHandler("transaction-logs");
        } catch (IOException ioException){

        }
    }

    @Override
    public void save(Transaction transaction) {

    }

    @Override
    public List<Transaction> findAll() {
        return null;
    }

    @Override
    public List<Transaction> findAllByCreditCard(Long cardNumber) {
        return null;
    }

    @Override
    public List<Transaction> findAllByDate(Date date) {
        return null;
    }

    @Override
    public List<Transaction> findAllByMerchant(Integer merchantId) {
        return null;
    }
}
