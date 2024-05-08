package handle.logs;

import java.util.ResourceBundle;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(){
        super(ResourceBundle.getBundle("application").getString("exception.account"));
    }
}
