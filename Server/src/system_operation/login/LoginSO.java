/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system_operation.login;

import domain.Employee;
import domain.GeneralDObject;
import java.util.ArrayList;
import java.util.List;
import system_operation.AbstractSO;

/**
 *
 * @author Dragon
 */
public class LoginSO extends AbstractSO {

    private Employee employee; //TODO: You can use GeneralDomainObject here once you make it. In fact, you can probaly put it in AbstractSO

    @Override
    protected void precondition(Object param) {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {

        List<GeneralDObject> employees = dbBroker.findRecords(new Employee());

        Employee employeeParam = (Employee) param;

        for (GeneralDObject e : employees) {
            if (((Employee) e).getUsername().equals(employeeParam.getUsername())
                    && ((Employee) e).getPassword().equals(employeeParam.getPassword())) {
                employee = (Employee) e;
            }
        }
        if (employee == null) {
            throw new Exception("Unknown employee!");
        }
    }

    public Employee getEmployee() {
        return employee;
    }
}
