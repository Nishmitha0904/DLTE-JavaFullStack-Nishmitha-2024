    package org.web.service.mybankdepositsweb.mvc;

    import mybank.dao.mybankdeposits.entity.DepositsAvailable;
    import mybank.dao.mybankdeposits.service.DepositService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RequestParam;

    import java.sql.SQLSyntaxErrorException;
    import java.util.List;

    @Controller
    public class MvcController {
        @Autowired
        DepositService depositService;

        @GetMapping("/")
        public String login(){
            return "index";
        }

        @GetMapping("/dashboard")
        public String dashboard(){
            return "dashboard";
        }

        @GetMapping("/view")
        public String viewDeposits() {
            return "view";
        }

        @GetMapping("/view/{roi}")
        public String viewDepositsByRoi(@RequestParam Double roi, Model model) throws SQLSyntaxErrorException {
            List<DepositsAvailable> deposits = depositService.searchDepositsByRoi(roi);
            model.addAttribute("deposits",deposits);
            return "view";
        }

    }
