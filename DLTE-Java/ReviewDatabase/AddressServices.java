package org.employee;

import java.util.List;
import java.util.ResourceBundle;

public class AddressServices {
    AddressInterface addressInterface;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

    public AddressServices(DatabaseTarget databaseTarget) {
        addressInterface = databaseTarget.getAddressInterface();
    }

    public void callSave(Address address) {
        addressInterface.save(address);
    }

    public List<Address> callDisplay() {
        return addressInterface.display();
    }
}
