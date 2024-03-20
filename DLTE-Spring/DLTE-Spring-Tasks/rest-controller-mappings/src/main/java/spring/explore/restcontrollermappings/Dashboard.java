package spring.explore.restcontrollermappings;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/loan")
public class Dashboard {

    private List<Loan> loanList = Stream.of(
            new Loan(16752361523423L, 50000.0, "Open", "John", 7656765456L),
            new Loan(87463253635221L, 100000.0, "Open", "Peter", 7647342389L)
    ).collect(Collectors.toList());

    //GetMapping: index as pathvariable and display loan in that specified index
    @GetMapping("/{index}")
    public Loan readLoan(@PathVariable("index") int index) {
        return loanList.get(index);
    }

    //PostMapping: new loan into the List
    @PostMapping("/")
    public Loan addNewLoan(@RequestBody Loan loan) {
        loanList.add(loan);
        return loan;
    }
}
