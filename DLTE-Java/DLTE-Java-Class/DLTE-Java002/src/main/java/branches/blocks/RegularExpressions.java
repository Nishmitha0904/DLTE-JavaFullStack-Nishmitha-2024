package branches.blocks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressions {
    public static void main(String[] args) {

        //Validating numbers
        String mobileExpression = "\\d{10,}";
        Pattern pattern  = Pattern.compile(mobileExpression);
        Matcher matcher = pattern.matcher(args[0]);
        if (matcher.matches())
            System.out.println("Valid mobile number");
        else
            System.out.println("Invalid mobile number");

        //Validating passwords
        String passwordExpression = "^()";
        pattern = Pattern.compile(passwordExpression);
        matcher = pattern.matcher(args[1]);
        if (matcher.matches())
            System.out.println("Valid password");
        else
            System.out.println("Invalid password");

    }
}
