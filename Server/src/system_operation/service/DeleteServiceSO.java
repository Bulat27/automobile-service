package system_operation.service;

import domain.Service;
import system_operation.AbstractSO;
import validation.Validator;

/**
 *
 * @author Dragon
 */
public class DeleteServiceSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
        Validator.startValidation()
                .throwIfInvalideParameterInstance(param, "Parameter must be an instance of Service", Service.class);
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.deleteRecord(param);
    }
}
