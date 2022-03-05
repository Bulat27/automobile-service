/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system_operation.service;

import domain.Employee;
import domain.Service;
import system_operation.AbstractSO;
import validation.Validator;

/**
 *
 * @author Dragon
 */
public class GetServicesByConditionSO extends AbstractSO{

    @Override
    protected void precondition(Object param) throws Exception {
        Validator.startValidation()
                   .throwIfInvalideParameterInstance(param, "Parameter must be an instance of Service", Service.class);
//                   .throwIfInvalide();
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Service service = (Service) param;
        result = repository.findRecords(service, service.getAttributeValuesWhereCondition());
    }

    public Object getResult() {
        return result;
    }
}
