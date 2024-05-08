package org.web.service.mybankdepositsweb.auth;

import mybank.dao.mybankdeposits.entity.Customer;
import mybank.dao.mybankdeposits.service.MyBankCustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class CustomerFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    MyBankCustomerService service;
    Logger logger = LoggerFactory.getLogger(CustomerFailureHandler.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("secure");

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        try {
            Customer customer = service.findByUsername(username);
            if (customer != null && customer.getCustomerStatus().equalsIgnoreCase("active")) {
                if (customer.getAttempts() < customer.getMaxAttempt()) {
                    customer.setAttempts(customer.getAttempts() + 1);
                    service.updateAttempts(customer);
                    logger.warn(resourceBundle.getString("cred.invalid"));
                    int leftAttempts = 4;
                    exception = new LockedException(resourceBundle.getString("pass.invalid") + ". " + (leftAttempts - customer.getAttempts()) + " "+ resourceBundle.getString("attempt.left"));
                    String errorMessage = customer.getAttempts().toString() + " " + exception.getMessage();
                    logger.warn(errorMessage);
                } else {
                    service.updateStatus(customer);
                    exception = new LockedException(resourceBundle.getString("max.attempts"));
                    logger.warn(resourceBundle.getString("account.suspend"));
                }
            } else {
                logger.warn(resourceBundle.getString("account.suspend"));
            }
        } catch (UsernameNotFoundException userException) {
            logger.warn(userException.toString());
            logger.warn(resourceBundle.getString("account.suspend"));
            exception = new LockedException("username not found");
        }
        // Set the default failure URL here
        setDefaultFailureUrl("/?error=" + exception.getMessage());
        super.onAuthenticationFailure(request, response, exception);
    }
}

