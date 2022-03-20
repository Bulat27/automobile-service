package system_operation.repair;

import domain.Repair;
import domain.RepairItem;
import system_operation.AbstractSO;
import system_operation.repair_item.AddRepairItemSO;
import validation.Validator;

/**
 *
 * @author Dragon
 */
public class EditRepairSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
        Validator.startValidation()
                .throwIfInvalideParameterInstance(param, "Parameter must be an instance of Repair", Repair.class)
                .validateNotNull(((Repair) param).getServiceBook(), "ServiceBook must not be null")
                .validateNotNullOrEmpty(((Repair) param).getName(), "Name must not be null or empty")
                .validateNotNull(((Repair) param).getStartDate(), "Start date must not be null")
                .validateNumberIsNonNegative(((Repair) param).getTotalRevenue(), "Total revenue must be a non negative number")
                .validateNumberIsNonNegative(((Repair) param).getTotalExpense(), "Total expense must be a non negative number")
                .validateListIsNotEmpty(((Repair) param).getRepairItems(), "Repair must have at least one RepairItem")
                .validateRepairTotalRevenue(((Repair) param), "Repair total revenue must match total revenue of RepairItem list")
                .validateRepairTotalExpense(((Repair) param), "Repair total expense must match total expense of RepairItem list")
                .throwIfInvalide();
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.updateRecord(param);

        RepairItem ri = ((Repair) param).getRepairItems().get(0);
        repository.deleteRecords(ri, "repair_id = " + ri.getRepair().getRepairID());

        AddRepairItemSO addRepairItemSO = new AddRepairItemSO(repository);//Make sure that you don't have to instantiate new one each time!

        for (RepairItem repairItem : ((Repair) param).getRepairItems()) {
            addRepairItemSO.executeAsSuboperation(repairItem);
        }
    }
}
