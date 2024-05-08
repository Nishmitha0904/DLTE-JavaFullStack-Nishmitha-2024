package rmi.explore;

import org.database.DatabaseTarget;
import org.database.User;
import org.database.UserServices;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserServer extends UnicastRemoteObject implements MyUserFunctions, Serializable {

    private static Context context;
    private Registry registry;
    private UserServices userServices;

    protected MyUserServer() throws RemoteException {
        super();
        userServices = new UserServices(new DatabaseTarget());
        try {
            registry = LocateRegistry.createRegistry(3030);
            Hashtable properties = new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
            properties.put(Context.PROVIDER_URL, "rmi://localhost:3030");
            context = new InitialContext(properties);
        } catch (NamingException | RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<String> fetchOverBalance() throws RemoteException {
        List<User> users = userServices.callFindAll().stream().filter(each -> each.getBalance()>=50000.0).collect(Collectors.toList());
        List<String> returnedList = new ArrayList<>();
        for (User user: users) {
            returnedList.add(user.getUsername());
        }
        return returnedList;
    }

    public static void main(String[] args) throws RemoteException, NamingException {
        MyUserServer myUserServer = new MyUserServer();
        context.bind("java:/user-filter", myUserServer);
    }
}
