/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system_operation.employee;

import domain.Employee;
import system_operation.AbstractSO;
import validation.Validator;

/**
 *
 * @author Dragon
 */
public class GetEmployeesByConditionSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
        Validator.startValidation()
                .throwIfInvalideParameterInstance(param, "Parameter must be an instance of Employee", Employee.class);
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Employee employee = (Employee) param;
        result = repository.findRecords(employee, employee.getWhereCondition());
    }

    public Object getResult() {
        return result;
    }
}
