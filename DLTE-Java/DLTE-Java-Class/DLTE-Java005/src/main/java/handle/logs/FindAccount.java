package handle.logs;

import java.io.IOException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FindAccount {
    static String[] accountHolders={"John", "Alice", "Peter", "Sofia"};
    static ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    static Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public FindAccount() {
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("account-logs.txt", false);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static int findAccount(int start,int end,String holderName){
        if(start<=end){
            int mid=start+(end-start)/2;
            if(accountHolders[mid].equals(holderName))
                return mid;
            else if(accountHolders[mid].compareTo(holderName)<0)
                return findAccount(mid+1,end,holderName);
            else
                return findAccount(start,mid-1,holderName);
        }
        else
            throw new AccountNotFoundException();
    }

    public static void main(String[] args) {
        FindAccount findAccount = new FindAccount();
        Arrays.sort(accountHolders);
        Scanner scanner=new Scanner(System.in);
        String nameOfTheHolder="";
        logger.log(Level.INFO,resourceBundle.getString("members.init"));
        try{
            System.out.println("Search by holder name ");
            nameOfTheHolder=scanner.next();
            System.out.println(findAccount(0,accountHolders.length-1,nameOfTheHolder));
        }
        catch (AccountNotFoundException accountException){
            logger.log(Level.WARNING,accountException.toString());
            System.out.println("Search by holder name ");
            nameOfTheHolder=scanner.next();
            System.out.println(findAccount(0,accountHolders.length-1,nameOfTheHolder));
        }
        finally {
            System.out.println("Account retrieved");
        }
    }
}