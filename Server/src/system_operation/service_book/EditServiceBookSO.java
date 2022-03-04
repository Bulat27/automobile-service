/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system_operation.service_book;

import domain.ServiceBook;
import system_operation.AbstractSO;
import validation.Validator;

/**
 *
 * @author Dragon
 */
public class EditServiceBookSO extends AbstractSO {
    
    @Override
    protected void precondition(Object param) throws Exception {
        Validator.startValidation()
                .throwIfInvalideParameterInstance(param, "Parameter must be an instance of ServiceBook", ServiceBook.class)
                .validateValueIsAllAlphabets(((ServiceBook) param).getClientFirstName(), "Client first name must not be null or empty and must contain only alphabetic characters")
                .validateValueIsAllAlphabets(((ServiceBook) param).getClientLastName(), "Client last name must not be null or empty and must contain only alphabetic characters")
                .validateNotNullOrEmpty(((ServiceBook) param).getVehicleDescription(), "Vehicle decription must not be null or empty")
                .validateNotNull(((ServiceBook) param).getInitialDate(), "Initial date must not be null")
                .throwIfInvalide();
    }
    
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.updateRecord(param);
    }
}
