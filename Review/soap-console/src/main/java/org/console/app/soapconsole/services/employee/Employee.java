
package services.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for employee complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="employee">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="employeeId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="employeeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="employeeEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="employeeMobile" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="employeeTemporaryAddress" type="{http://employee.services}address"/>
 *         &lt;element name="employeePermanentAddress" type="{http://employee.services}address"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employee", propOrder = {
    "employeeId",
    "employeeName",
    "employeeEmail",
    "employeeMobile",
    "employeeTemporaryAddress",
    "employeePermanentAddress"
})
public class Employee {

    protected long employeeId;
    @XmlElement(required = true)
    protected String employeeName;
    @XmlElement(required = true)
    protected String employeeEmail;
    protected long employeeMobile;
    @XmlElement(required = true)
    protected Address employeeTemporaryAddress;
    @XmlElement(required = true)
    protected Address employeePermanentAddress;

    /**
     * Gets the value of the employeeId property.
     * 
     */
    public long getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets the value of the employeeId property.
     * 
     */
    public void setEmployeeId(long value) {
        this.employeeId = value;
    }

    /**
     * Gets the value of the employeeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * Sets the value of the employeeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeName(String value) {
        this.employeeName = value;
    }

    /**
     * Gets the value of the employeeEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeEmail() {
        return employeeEmail;
    }

    /**
     * Sets the value of the employeeEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeEmail(String value) {
        this.employeeEmail = value;
    }

    /**
     * Gets the value of the employeeMobile property.
     * 
     */
    public long getEmployeeMobile() {
        return employeeMobile;
    }

    /**
     * Sets the value of the employeeMobile property.
     * 
     */
    public void setEmployeeMobile(long value) {
        this.employeeMobile = value;
    }

    /**
     * Gets the value of the employeeTemporaryAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getEmployeeTemporaryAddress() {
        return employeeTemporaryAddress;
    }

    /**
     * Sets the value of the employeeTemporaryAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setEmployeeTemporaryAddress(Address value) {
        this.employeeTemporaryAddress = value;
    }

    /**
     * Gets the value of the employeePermanentAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getEmployeePermanentAddress() {
        return employeePermanentAddress;
    }

    /**
     * Sets the value of the employeePermanentAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setEmployeePermanentAddress(Address value) {
        this.employeePermanentAddress = value;
    }

}