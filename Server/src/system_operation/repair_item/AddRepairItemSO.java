/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system_operation.repair_item;

import domain.EmployeeEngagement;
import domain.RepairItem;
import repository.Repository;
import system_operation.AbstractSO;
import system_operation.employee_engagement.AddEmployeeEngagementSO;
import validation.Validator;

/**
 *
 * @author Dragon
 */
public class AddRepairItemSO extends AbstractSO {

    public AddRepairItemSO() {
        super();
    }

    public AddRepairItemSO(Repository repository) {
        super(repository);
    }

    public void executeAsSuboperation(Object param) throws Exception {
        precondition(param);
        executeOperation(param);
    }

    @Override
    protected void precondition(Object param) throws Exception {
        Validator.startValidation()
                .throwIfInvalideParameterInstance(param, "Parameter must be an instance of RepairItem", RepairItem.class)
                .validateNotNull(((RepairItem) param).getRepair(), "Repair must not be null")
                .validateNumberIsNonNegative(((RepairItem) param).getOrderNumber(), "OrderNumber must be a non negative integer")
                .validateNotNull(((RepairItem) param).getStartDate(), "Start date must not be null")
                .validateNotNull(((RepairItem) param).getEndDate(), "End date must not be null")
                .validateIsStartDateBeforeEndDate(((RepairItem) param).getStartDate(), ((RepairItem) param).getEndDate(), "Start date must be before end date")
                .validateNumberIsNonNegative(((RepairItem) param).getEmployeeExpense(), "EmployeeExpense must be a non negative number")
                .validateNumberIsNonNegative(((RepairItem) param).getAdditionalExpense(), "AdditionalExpense must be a non negative number")
                .validateNumberIsNonNegative(((RepairItem) param).getAdditionalRevenue(), "AdditionalRevenuemust be a non negative number")
                .validateNotNull(((RepairItem) param), "Service must not be null")
                .validateListIsNotEmpty(((RepairItem) param).getEmployeeEngagements(), "EmployeeEngagement list must not be empty")
                .throwIfInvalide();
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.insertRecord(param);

        AddEmployeeEngagementSO addEmployeeEngagementSO = new AddEmployeeEngagementSO(repository);

        for (EmployeeEngagement employeeEngagement : ((RepairItem) param).getEmployeeEngagements()) {
            addEmployeeEngagementSO.executeAsSuboperation(employeeEngagement);
        }
    }
}
