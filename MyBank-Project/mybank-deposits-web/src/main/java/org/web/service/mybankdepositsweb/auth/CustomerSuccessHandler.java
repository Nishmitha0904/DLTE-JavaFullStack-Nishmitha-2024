package org.web.service.mybankdepositsweb.auth;

import mybank.dao.mybankdeposits.entity.Customer;
import mybank.dao.mybankdeposits.service.MyBankCustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomerSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    MyBankCustomerService service;

    Logger logger = LoggerFactory.getLogger(CustomerSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Customer customer= (Customer) authentication.getPrincipal();
        if(customer.getCustomerStatus().equalsIgnoreCase("active")){
            if(customer.getAttempts()>=1){
                customer.setAttempts(1);
                service.updateAttempts(customer);
            }
//            super.setDefaultTargetUrl("/depositsrepo/deposits.wsdl");
            super.setDefaultTargetUrl("/deposit/view");
        }
        else{
            logger.warn("Max attempts reached! Contact admin");
            super.setDefaultTargetUrl("/login");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
