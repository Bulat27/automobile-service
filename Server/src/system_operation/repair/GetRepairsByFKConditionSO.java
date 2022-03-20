package system_operation.repair;

import domain.Repair;
import system_operation.AbstractSO;
import validation.Validator;

/**
 *
 * @author Dragon
 */
public class GetRepairsByFKConditionSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
        Validator.startValidation()
                .throwIfInvalideParameterInstance(param, "Parameter must be an instance of Repair", Repair.class);
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Repair repair = (Repair) param;
        result = repository.findRecords(repair, repair.getFKWhereCondition());
    }

    public Object getResult() {
        return result;
    }
}
