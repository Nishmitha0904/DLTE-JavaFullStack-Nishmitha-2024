package com.example.mybankdashboardthymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/deposit")
public class DepositController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String dashboard() {
        return "dashboard";
    }

    @RequestMapping(value = "/viewall",method = RequestMethod.GET)
    public String viewAll() {
        return "viewall";
    }

    @RequestMapping(value = "/roiview",method = RequestMethod.GET)
    public String viewByRoi() {
        return "viewbyroi";
    }

}
