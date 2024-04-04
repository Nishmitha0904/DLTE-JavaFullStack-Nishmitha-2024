package web;

import org.db.entity.Employee;

import java.util.List;

public class GroupOfEmployees {
    private List<Employee> employees;

    public GroupOfEmployees() {
    }

    public GroupOfEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "GroupOfEmployees{" +
                "employees=" + employees +
                '}';
    }
}
