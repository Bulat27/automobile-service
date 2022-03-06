/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system_operation.repair_item;

import domain.RepairItem;
import system_operation.AbstractSO;
import validation.Validator;

/**
 *
 * @author Dragon
 */
public class GetRepairItemsByFKConditionSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
        Validator.startValidation()
                .throwIfInvalideParameterInstance(param, "Parameter must be an instance of RepairItem", RepairItem.class);
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        RepairItem repairItem = (RepairItem) param;
        result = repository.findRecords(repairItem, repairItem.getFKWhereCondition());
    }

    public Object getResult() {
        return result;
    }
}
