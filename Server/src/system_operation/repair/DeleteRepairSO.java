package system_operation.repair;

import domain.Repair;
import system_operation.AbstractSO;
import validation.Validator;

/**
 *
 * @author Dragon
 */
public class DeleteRepairSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
        Validator.startValidation()
                .throwIfInvalideParameterInstance(param, "Parameter must be an instance of Repair", Repair.class);
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.deleteRecord(param);
    }
}
