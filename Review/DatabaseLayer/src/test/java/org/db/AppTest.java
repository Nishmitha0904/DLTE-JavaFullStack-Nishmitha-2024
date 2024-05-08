//package org.db;
//
//import org.db.entity.Address;
//import org.db.entity.Employee;
//import org.db.middleware.DatabaseTarget;
//import org.db.middleware.EmployeeImplementations;
//import org.db.remote.EmployeeInterface;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//
///**
// * Unit test for simple App.
// */
//
//@RunWith(MockitoJUnitRunner.class)
//public class AppTest {
//    @Mock
//    private DatabaseTarget mockDatabaseTarget;
//    @Mock
//    private EmployeeInterface mockEmployeeInterface;
//    private EmployeeServices services;
//    @Before
////    public void prepareStore() {
////        when(mockDatabaseTarget.getEmployeeInterface()).thenReturn(mockEmployeeInterface);
////        services=new EmployeeServices(mockDatabaseTarget);
////    }
//
//    @Test
//    public void testSave() throws SQLException {
//        Employee employee1 = new Employee(67585743L, "Nishmitha", "nish@gmail.com", 8756746578L, new Address("234", "abcd", "efgh", "Karnataka", 567566L), new Address("567", "fred", "njkh", "sdfr", 464654L));
//
//        doNothing().when(mockEmployeeInterface).save(employee1);
//        services.callSave(employee1);
//        verify(mockEmployeeInterface).save(employee1);
//    }
//
//    @Test
//    public void testDisplay() throws SQLException {
//        Employee employee1 = new Employee(67585743L, "Nishmitha", "nish@gmail.com", 8756746578L, new Address("234", "abcd", "efgh", "Karnataka", 567566L), new Address("567", "fred", "njkh", "sdfr", 464654L));
//
//        List<Employee> expectedList = Stream.of(employee1).collect(Collectors.toList());
//        when(mockEmployeeInterface.display()).thenReturn(expectedList);
//        List<Employee> actualList = services.callDisplay();
//        assertSame(1,actualList.size());
//        assertTrue(actualList.get(0).getEmployeeID()==expectedList.get(0).getEmployeeID());
//    }
//
//    @Test
//    public void testFindById() {
//        Long employeeId=67585743L;
//        Employee employee1 = new Employee(67585743L, "Nishmitha", "nish@gmail.com", 8756746578L, new Address("234", "abcd", "efgh", "Karnataka", 567566L), new Address("567", "fred", "njkh", "sdfr", 464654L));
//        when(mockEmployeeInterface.findById(employeeId)).thenReturn(employee1);
//        Employee actual = services.callFindById(employeeId);
////        assertNotSame(employee1.getEmployeeName(), actual.getEmployeeName());
//        assertSame(employee1, actual);
//
//    }
//}
