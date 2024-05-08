package rmi.explore;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MyUserFunctions extends Remote {
    List<String> fetchOverBalance() throws RemoteException;
}
