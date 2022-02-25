/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system_operation.service;

import domain.Service;
import system_operation.AbstractSO;
import validation.Validator;

/**
 *
 * @author Dragon
 */
public class SaveServiceSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
//        if(param == null || !(param instanceof Service)){
//            throw new Exception("Invalid parameter");//TODO: This can be generalized
//        }
        Validator.startValidation()
                .throwIfInvalideParameterInstance(param, "Parameter must be an instance of Service", Service.class)
                .validateNumberIsNonNegative(((Service) param).getPrice(), "Price must be a non negative number")
                .validateNotNullOrEmpty(((Service) param).getName(), "Name must not be null or empty")
                .validateNumberIsNonNegative(((Service) param).getMaterialCost(), "Material cost must be a non negative number")
                .throwIfInvalide();
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.insertRecord(param);//TODO: CHECK OUT WHY THIS WORKS WITHOUT CASTING! GENERICS|!
    }
}
