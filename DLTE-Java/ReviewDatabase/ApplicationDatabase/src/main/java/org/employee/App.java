package org.employee;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        DatabaseTarget databaseTarget = new DatabaseTarget();
        EmployeeServices employeeServices = new EmployeeServices(databaseTarget);
//        AddressServices addressServices = new AddressServices(databaseTarget);
//
//        Employee emp = new Employee(654567654L, "Nishmitha", "nish@gmail.com", 7846574837L);
//        employeeServices.callSave(emp);
//        System.out.println(employeeServices.callDisplay());
//        Employee emp2 = new Employee(753747385L, "Test", "test@gmail.com", 6758746574L);
//        employeeServices.callSave(emp2);

//        Address address = new Address(654567654L, "Sri Lakshmi pg", "Mailasandra", "Bangalore", "Karnataka", 674653L);
//        Address address = new Address(654567654L, "3-67(1)", "Cherkady", "Udupi", "Karnataka", 576215L);
//
//        addressServices.callSave(address);

    }
}
