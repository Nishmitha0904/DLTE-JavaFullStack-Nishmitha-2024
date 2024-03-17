package service.soap.web;

import org.database.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class UserSoap {
    private UserServices services;
    public UserSoap() {
        StorageTarget storageTarget = new DatabaseTarget();
        services = new UserServices(storageTarget);
    }

    //createAccount  >> Create service of creating account
    @WebMethod
    public void createAccount(@WebParam(name = "User")User user) {
        services.callSave(user);
    }

    //findByUsername>> Read service of finding account(can be useful for login)
    @WebMethod
    @WebResult(name = "User")
    public User readByUsername(@WebParam(name = "String")String username) {
        return services.callFindById(username);
    }

    //findAllByUsername> Read service of finding transactions of respective username
    @WebMethod
    @WebResult(name = "GroupOfUsers")
    public GroupOfTransactions readAllByUsername(@WebParam(name = "String")String username) {
        GroupOfTransactions groupOfTransactions = new GroupOfTransactions();
        List<Transaction> transactionList = services.callFindByUsername(username);
        groupOfTransactions.setTransactions(transactionList);
        return groupOfTransactions;
    }


}
