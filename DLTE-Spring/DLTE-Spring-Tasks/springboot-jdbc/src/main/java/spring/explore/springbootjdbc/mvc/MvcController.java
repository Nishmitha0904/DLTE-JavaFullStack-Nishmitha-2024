package spring.explore.springbootjdbc.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.explore.springbootjdbc.TransactionException;
import spring.explore.springbootjdbc.entity.Transaction;
import spring.explore.springbootjdbc.service.MyBankService;

import java.util.ResourceBundle;

@Controller
@RequestMapping("/transaction")
public class MvcController {
    @Autowired
    MyBankService myBankService;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    Logger logger = LoggerFactory.getLogger(MvcController.class);

    @GetMapping("/new")
    public String submit(Model model){
        Transaction transaction=new Transaction();
        model.addAttribute("transaction",new Transaction());
        return "form.html";
    }

    @RequestMapping(value="/submit",method = RequestMethod.POST)
    public String newTransaction(Transaction transaction, BindingResult bindingResult, Model model){
        try{
            if(!bindingResult.hasErrors()){
                transaction = myBankService.apiSave(transaction);
                model.addAttribute("status",transaction.getTransactionId()+" has inserted");
            }
        }catch(TransactionException transactionException){
            logger.warn(transactionException.toString());
            model.addAttribute("error",transactionException.toString());
        }
        return "form.html";
    }

}
