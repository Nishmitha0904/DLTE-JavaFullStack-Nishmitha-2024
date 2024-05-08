
package services.employee;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the services.employee package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: services.employee
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindAllEmployeesResponse }
     * 
     */
    public FindAllEmployeesResponse createFindAllEmployeesResponse() {
        return new FindAllEmployeesResponse();
    }

    /**
     * Create an instance of {@link Employee }
     * 
     */
    public Employee createEmployee() {
        return new Employee();
    }

    /**
     * Create an instance of {@link ServiceStatus }
     * 
     */
    public ServiceStatus createServiceStatus() {
        return new ServiceStatus();
    }

    /**
     * Create an instance of {@link FindByPincodeRequest }
     * 
     */
    public FindByPincodeRequest createFindByPincodeRequest() {
        return new FindByPincodeRequest();
    }

    /**
     * Create an instance of {@link FindByIdRequest }
     * 
     */
    public FindByIdRequest createFindByIdRequest() {
        return new FindByIdRequest();
    }

    /**
     * Create an instance of {@link FindByPincodeResponse }
     * 
     */
    public FindByPincodeResponse createFindByPincodeResponse() {
        return new FindByPincodeResponse();
    }

    /**
     * Create an instance of {@link FindByIdResponse }
     * 
     */
    public FindByIdResponse createFindByIdResponse() {
        return new FindByIdResponse();
    }

    /**
     * Create an instance of {@link SaveRequest }
     * 
     */
    public SaveRequest createSaveRequest() {
        return new SaveRequest();
    }

    /**
     * Create an instance of {@link FindAllEmployeesRequest }
     * 
     */
    public FindAllEmployeesRequest createFindAllEmployeesRequest() {
        return new FindAllEmployeesRequest();
    }

    /**
     * Create an instance of {@link SaveResponse }
     * 
     */
    public SaveResponse createSaveResponse() {
        return new SaveResponse();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

}
