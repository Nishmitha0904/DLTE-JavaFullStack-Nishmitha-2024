
package client.soap.web;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "UserSoapService", targetNamespace = "http://web.soap.service/", wsdlLocation = "http://localhost:1234/soap?wsdl")
public class UserSoapService
    extends Service
{

    private final static URL USERSOAPSERVICE_WSDL_LOCATION;
    private final static WebServiceException USERSOAPSERVICE_EXCEPTION;
    private final static QName USERSOAPSERVICE_QNAME = new QName("http://web.soap.service/", "UserSoapService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:1234/soap?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        USERSOAPSERVICE_WSDL_LOCATION = url;
        USERSOAPSERVICE_EXCEPTION = e;
    }

    public UserSoapService() {
        super(__getWsdlLocation(), USERSOAPSERVICE_QNAME);
    }

    public UserSoapService(WebServiceFeature... features) {
        super(__getWsdlLocation(), USERSOAPSERVICE_QNAME, features);
    }

    public UserSoapService(URL wsdlLocation) {
        super(wsdlLocation, USERSOAPSERVICE_QNAME);
    }

    public UserSoapService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, USERSOAPSERVICE_QNAME, features);
    }

    public UserSoapService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public UserSoapService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns UserSoap
     */
    @WebEndpoint(name = "UserSoapPort")
    public UserSoap getUserSoapPort() {
        return super.getPort(new QName("http://web.soap.service/", "UserSoapPort"), UserSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns UserSoap
     */
    @WebEndpoint(name = "UserSoapPort")
    public UserSoap getUserSoapPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://web.soap.service/", "UserSoapPort"), UserSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (USERSOAPSERVICE_EXCEPTION!= null) {
            throw USERSOAPSERVICE_EXCEPTION;
        }
        return USERSOAPSERVICE_WSDL_LOCATION;
    }

}
