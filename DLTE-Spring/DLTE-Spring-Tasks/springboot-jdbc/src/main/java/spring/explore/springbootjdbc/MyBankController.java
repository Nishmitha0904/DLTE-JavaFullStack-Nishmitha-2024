package spring.explore.springbootjdbc;

import com.sun.xml.internal.messaging.saaj.soap.impl.TreeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class MyBankController {

    @Autowired
    MyBankService myBankService;

    Logger logger = LoggerFactory.getLogger(MyBankController.class);

    @PostMapping("/new")
    public Transaction saved(@RequestBody Transaction transaction) {
        Transaction transaction1=null;
        try {
            transaction1 = myBankService.apiSave(transaction);
        } catch (TransactionException transactionException) {
            logger.warn(transactionException.toString());
        }
        return transaction1;
    }

    @GetMapping("/sender/{sender}")
    public List<Transaction> gettingSender(@PathVariable("sender") String sender) {
        return myBankService.apiFindBySender(sender);
    }

    @GetMapping("/receiver/{receiver}")
    public List<Transaction> gettingReceiver(@PathVariable("receiver") String receiver) {
        return myBankService.apiFindByReceiver(receiver);
    }

    @GetMapping("/view/{amount}")
    public List<Transaction> gettingAmount(@PathVariable("amount") Double amount) {
        return myBankService.apiFindByAmount(amount);
    }
}
