package spring.jpa.jpahqlsql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.jpa.jpahqlsql.model.Transactions;
import spring.jpa.jpahqlsql.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping(value = "/", consumes = "application/xml")
    public Transactions apiSave(@RequestBody Transactions transaction) {
        return transactionService.callSave(transaction);
    }

    @GetMapping("/{user}/{type}")
    public List<Transactions> apiFindByUserAndType(@PathVariable("user") String user, @PathVariable("type") String type) {
        return transactionService.callFindByTransactionDonebyAndTransactionType(user, type);
    }

    @GetMapping("/{amount1}/{amount2}")
    public List<Transactions> apiFindByAmountRange(@PathVariable("amount1") double amount1, @PathVariable("amount2") double amount2) {
        return transactionService.callFindByTransactionAmount(amount1, amount2);
    }
}
