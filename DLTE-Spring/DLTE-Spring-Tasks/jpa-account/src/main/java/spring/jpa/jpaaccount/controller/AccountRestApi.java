package spring.jpa.jpaaccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.jpa.jpaaccount.model.Account;
import spring.jpa.jpaaccount.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountRestApi {

    @Autowired
    AccountService accountService;

    //AccountOpenning>> POST mapping
    @PostMapping("/")
    public Account apiSave(@RequestBody Account account) {
        return accountService.callSave(account);
    }

    //AccountUpdate>> PUT mapping
    @PutMapping("/")
    public Account apiUpdate(@RequestBody Account account) {
        return accountService.callSave(account);
    }

    //List<Account>        > GET mapping
    @GetMapping("/")
    public List<Account> apiFindAll() {
        return accountService.callFindAll();
    }

}
