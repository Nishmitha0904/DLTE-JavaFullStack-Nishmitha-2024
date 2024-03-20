package org.console.app.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public Boolean isValidEmail(String borrowerEmail) {
        String emailExpression = "^[A-Za-z0-9+_.-]+@[a-zA-Z]{3,}+\\.[a-z]{2,}";
        Pattern pattern = Pattern.compile(emailExpression);
        Matcher matcher = pattern.matcher(borrowerEmail);
        return matcher.matches();
    }
    public Boolean isValidMobileNumber(String mobileNumber) {
        String mobileExpression = "\\d{10}";
        Pattern pattern = Pattern.compile(mobileExpression);
        Matcher matcher = pattern.matcher(mobileNumber);
        return matcher.matches();
    }

}
