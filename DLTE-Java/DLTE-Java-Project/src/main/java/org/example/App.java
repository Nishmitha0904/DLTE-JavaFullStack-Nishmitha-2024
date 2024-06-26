package org.example;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{
    private static UserServices services;
    private static StorageTarget storageTarget;
    private static ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    private static Scanner scanner=new Scanner(System.in);
    private static User user;
    public static void main( String[] args )
    {
        storageTarget=new FileStorageTarget();
        services=new UserServices(storageTarget);
        int option=0;
        do{
            System.out.println(resourceBundle.getString("app.greet")); // welocme
            System.out.println(resourceBundle.getString("app.menu")); // login/create account
            option=scanner.nextInt();
            switch (option){
                case 1:loggingIn();
                if (user!=null) {
                    System.out.println(resourceBundle.getString("app.greet"));
                    System.out.println("Hello "+user.getUserName());
                    System.out.println(resourceBundle.getString("app.menu2"));
                    return;
                }
                break;
                case 2:
                    System.out.println("Enter the account details");
                    User user =new User();
                    System.out.println("Enter the username");
                    user.setUserName(scanner.next());
//                    try {
//                        user.setUserName(scanner.next());
//                    } catch (UserException userException) {
//                        System.out.println(resourceBundle.getString("username.exists"));
//                        System.out.println("Re-enter username");
//                        user.setUserName(scanner.next());
//                    }


                    System.out.println("Enter the password");
                    user.setUserPassword(scanner.next());
                    while (!isValidPassword(user.getUserPassword())) { //validating password
//                        System.out.println(resourceBundle.getString("app.password.invalid"));
                        //invalid
                        System.out.println(resourceBundle.getString("app.password.format"));
                        user.setUserPassword(scanner.next());
                    }
                    System.out.println("Enter the mail id");
                    user.setUserMailId(scanner.next());
                    while (!isValidEmail(user.getUserMailId())) { //main-validation
                        System.out.println(resourceBundle.getString("app.mail.invalid"));
                        //invalid
                        user.setUserMailId(scanner.next());
                    }
                    System.out.println("Enter the contact number");
                    user.setContactInfo(scanner.nextLong());
                    while (!isValidContactNumber(user.getContactInfo())) { //validating contact
                        // if invalid
                        System.out.println(resourceBundle.getString("app.contact.invalid"));
                        user.setContactInfo(scanner.nextLong());
                    }
                    scanner.nextLine();
                    System.out.println("Enter the Address");
                    user.setUserAddress(scanner.nextLine());
                    System.out.println("Enter the initial Balance");
                    user.setInitialBalance(scanner.nextDouble());
                    try{
                        services.callSave(user);
                    }
                    catch(UserException userException){
                        System.out.println(userException);
                    }
                    break;
                default:return;
            }
        }while(true);

    }
    public static void loggingIn(){
        User current=null;
        try{
            System.out.println(resourceBundle.getString("app.username"));
            current=services.callFindById(scanner.next());
            System.out.println(resourceBundle.getString("app.password"));
            String password=scanner.next();
            if(current.getUserPassword().equals(password)){
                App.user=current;
                System.out.println(resourceBundle.getString("app.log.ok"));
            }
            else{
                throw new UserException("Invalid Password");
            }
        }catch (UserException userException){
            System.out.println(userException);
            App.loggingIn();
        }
    }

    //Validation checking - Regex
    public static Boolean isValidEmail(String borrowerEmail) {
        String emailExpression = "^[A-Za-z0-9+_.-]+@[a-zA-Z]+\\.[a-z]{2,}";
        Pattern pattern = Pattern.compile(emailExpression);
        Matcher matcher = pattern.matcher(borrowerEmail);
        return matcher.matches();
    }
    public static Boolean isValidContactNumber(Long contactNumber) {
        String contactString = Long.toString(contactNumber);
        String mobileExpression = "\\d{10}";
        Pattern pattern = Pattern.compile(mobileExpression);
        Matcher matcher = pattern.matcher(contactString);
        return matcher.matches();
    }
    public static Boolean isValidPassword(String password) {
        String passwordExpression = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(passwordExpression);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
