package spring.explore.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

@RestController
@RequestMapping("/loans")
public class MyBankController {

    @Autowired
    MyBankService myBankService;

    @GetMapping("/view")
//    public List<Loan> fetchAll() {
    public ResponseEntity fetchAll() {
        try {
//            return myBankService.apiFindAll();
            return ResponseEntity.ok(myBankService.apiFindAll());
        } catch (SQLSyntaxErrorException e) {
            //throw new LoanException("Due to System Failure");
            return ResponseEntity.badRequest().body(new LoanException("Due to System Failure"));
        } catch (LoanException loanException) {
            return ResponseEntity.badRequest().body(new LoanException("No data available"))
        }
    }
}


