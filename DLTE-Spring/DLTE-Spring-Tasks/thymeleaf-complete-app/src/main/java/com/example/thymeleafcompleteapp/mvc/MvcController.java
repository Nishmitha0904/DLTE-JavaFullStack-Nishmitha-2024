package com.example.thymeleafcompleteapp.mvc;

import com.example.thymeleafcompleteapp.entity.Transaction;
import com.example.thymeleafcompleteapp.service.MyBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/transactions")
public class MvcController {
    @Autowired
    MyBankService myBankService;

    @GetMapping("/")
    public String login(){
        return "index";
    }


    @RequestMapping(value="/dashboard", method = RequestMethod.POST)
    public String dashboard(){
        return "dashboard";
    }

    @GetMapping("/newTransact")
    public String show(Model model){
        Transaction transaction=new Transaction();
        model.addAttribute("transaction",new Transaction());
        return "add";
    }
    @RequestMapping(value = "/new" ,method = RequestMethod.POST)
    public String newTransaction(@Valid @ModelAttribute Transaction transaction, BindingResult bindingResult, Model model){
        model.addAttribute("transaction",transaction);
        if(!bindingResult.hasErrors()){
            Transaction transaction1=myBankService.apiSave(transaction);
            model.addAttribute("message","Transaction successful!");
            model.addAttribute("transaction",transaction1);
            return "dashboard";
        }else{
            model.addAttribute("message","Transaction failed!");
            return "add";
        }
    }

    @GetMapping("/search")
    public String searchShow(Model model){
        Transaction transaction=new Transaction();
        model.addAttribute("transaction",new Transaction());
        return "filter";
    }

    @GetMapping("/results")
    public String search(@RequestParam("filterBy") String filterBy, @RequestParam("search") String searchTerm, Model model){
        System.out.println("Filter By:"+filterBy);
        System.out.println("Search Term:"+searchTerm);
        List<Transaction> transactionList=null;
        switch (filterBy){
            case "filterBySender":transactionList=myBankService.apiFindBySender(searchTerm);
                break;
            case "filterByReceiver":transactionList=myBankService.apiFindByReceiver(searchTerm);
                break;
            case "filterByAmount":transactionList=myBankService.apiFindByAmount(Double.parseDouble(searchTerm));
                break;
        }
        model.addAttribute("transaction",transactionList);
        return "filter";
    }

    @GetMapping("/remove")
    public String deleteShow(Model model){
        //Transaction transaction=new Transaction();
        model.addAttribute("transaction",new Transaction());
        return "delete";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("startDate") String startDateStr,@RequestParam("endDate") String endDateStr,Model model){
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
        Date startDate;
        Date endDate;
        try {
            startDate = (Date) dateFormat.parse(startDateStr);
            endDate = (Date) dateFormat.parse(endDateStr);
        } catch (ParseException e) {
            // Handle parse exception if needed
            e.printStackTrace();
            return "redirect:/transaction/error";
        }
        String delete=myBankService.deleteTransaction(startDate,endDate);
        model.addAttribute("messageDelete",delete);
        return "index";
    }
    @PostMapping("/logout")
    public String logout() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/index";
    }
}
