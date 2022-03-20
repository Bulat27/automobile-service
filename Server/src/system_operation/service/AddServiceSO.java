package system_operation.service;

import domain.Service;
import system_operation.AbstractSO;
import validation.Validator;

/**
 *
 * @author Dragon
 */
public class AddServiceSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
        Validator.startValidation()
                .throwIfInvalideParameterInstance(param, "Parameter must be an instance of Service", Service.class)
                .validateNumberIsNonNegative(((Service) param).getPrice(), "Price must be a non negative number")
                .validateNotNullOrEmpty(((Service) param).getName(), "Name must not be null or empty")
                .validateNumberIsNonNegative(((Service) param).getMaterialCost(), "Material cost must be a non negative number")
                .throwIfInvalide();
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.insertRecord(param);
    }
}
