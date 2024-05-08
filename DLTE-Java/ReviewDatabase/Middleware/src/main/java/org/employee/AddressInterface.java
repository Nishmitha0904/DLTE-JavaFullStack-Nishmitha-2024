package org.employee;

import java.util.List;

public interface AddressInterface {
    void save(Address address);
    List<Address> display();
    //List<Employee> findByPincode(Long pincode);
}
