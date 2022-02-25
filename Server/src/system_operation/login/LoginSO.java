/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system_operation.login;

import domain.Employee;
import domain.GeneralDObject;
import java.util.List;
import system_operation.AbstractSO;
import validation.ValidationException;
import validation.Validator;

/**
 *
 * @author Dragon
 */
public class LoginSO extends AbstractSO {

    //TODO: You can use GeneralDomainObject here once you make it. In fact, you can probaly put it in AbstractSO
    @Override
    protected void precondition(Object param) throws ValidationException  {
//        if(param == null || !(param instanceof Employee)){
//            throw new Exception("Invalid parameter");
//        }
          Validator.startValidation()
                   .throwIfInvalideParameterInstance(param, "Parameter must be an instance of Employee", Employee.class);
//                   .throwIfInvalide();
    }

    @Override
    protected void executeOperation(Object param) throws Exception {

        List<GeneralDObject> employees = repository.findRecords(new Employee(), null);//TODO: Check out the thing about generics here!

        Employee employeeParam = (Employee) param;

        for (GeneralDObject e : employees) {
            if (((Employee) e).getUsername().equals(employeeParam.getUsername())
                    && ((Employee) e).getPassword().equals(employeeParam.getPassword())) {
                result = e;
            }
        }
        if (result == null) {
            throw new Exception("Unknown employee!");
        }
    }
    
//    @Override
//    protected void executeOperation(Object param) throws Exception {
//
//        List<String> employees = repository.findRecords(new Employee());//TODO: Check out the thing about generics here!
//
//        Employee employeeParam = (Employee) param;
//
//        for (String e : employees) {
//            System.out.println(e);
//        }
//        if (result == null) {
//            throw new Exception("Unknown employee!");
//        }
//    }

    public Object getResult() {
        return result;
    }
}
