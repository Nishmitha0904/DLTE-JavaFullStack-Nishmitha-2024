
package client.soap.web;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "UserSoap", targetNamespace = "http://web.soap.service/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UserSoap {


    /**
     * 
     * @param user
     */
    @WebMethod
    @Action(input = "http://web.soap.service/UserSoap/createAccountRequest", output = "http://web.soap.service/UserSoap/createAccountResponse")
    public void createAccount(
        @WebParam(name = "User", partName = "User")
        User user);

    /**
     * 
     * @param string
     * @return
     *     returns service.soap.web.User
     */
    @WebMethod
    @WebResult(name = "User", partName = "User")
    @Action(input = "http://web.soap.service/UserSoap/readByUsernameRequest", output = "http://web.soap.service/UserSoap/readByUsernameResponse")
    public User readByUsername(
        @WebParam(name = "String", partName = "String")
        String string);

    /**
     * 
     * @param string
     * @return
     *     returns service.soap.web.GroupOfTransactions
     */
    @WebMethod
    @WebResult(name = "GroupOfUsers", partName = "GroupOfUsers")
    @Action(input = "http://web.soap.service/UserSoap/readAllByUsernameRequest", output = "http://web.soap.service/UserSoap/readAllByUsernameResponse")
    public GroupOfTransactions readAllByUsername(
        @WebParam(name = "String", partName = "String")
        String string);

}
