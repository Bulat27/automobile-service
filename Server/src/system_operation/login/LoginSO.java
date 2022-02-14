/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system_operation.login;

import domain.Employee;
import java.util.ArrayList;
import java.util.List;
import system_operation.AbstractSO;

/**
 *
 * @author Dragon
 */
public class LoginSO extends AbstractSO{
    
    private Employee employee; //TODO: You can use GeneralDomainObject here once you make it. In fact, you can probaly put it in AbstractSO

    @Override
    protected void precondition(Object param) {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception{
        
            //List<Employee> employees = storageUser.getAll();//TODO: Do this in a broker!
            List<Employee> employees = new ArrayList<>();
            Employee e3 = new Employee("bbr", "bbr");
            e3.setFirstName("bbr");
            employees.add(new Employee("nikola", "nikola"));
            employees.add(new Employee("nikola27", "nikola27"));
            employees.add(e3);
            Employee employeeParam = (Employee) param;
            
            for (Employee e : employees) {
                if (e.getUsername().equals(employeeParam.getUsername()) && e.getPassword().equals(employeeParam.getPassword())) {
                    employee = e;
                }
            }
            if(employee == null) throw new Exception("Unknown employee!");
    }

    public Employee getEmployee() {
        return employee;
    }
}
