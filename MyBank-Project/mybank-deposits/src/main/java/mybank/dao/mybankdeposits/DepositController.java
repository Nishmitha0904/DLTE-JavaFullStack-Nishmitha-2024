//package mybank.dao.mybankdeposits;
//
//import mybank.dao.mybankdeposits.entity.DepositsAvailable;
//import mybank.dao.mybankdeposits.service.DepositService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.sql.SQLSyntaxErrorException;
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/deposit/*")
//public class DepositController {
//    @Autowired
//    private DepositService depositService;
//
//    @GetMapping("/view")
//    public List<DepositsAvailable> fetchAll() {
//        List<DepositsAvailable> deposits = new ArrayList<>();
//        try {
//            depositService.listAllDeposits();
//        } catch (SQLSyntaxErrorException e) {
//            e.printStackTrace();
//        }
//        return deposits;
//    }
//}
