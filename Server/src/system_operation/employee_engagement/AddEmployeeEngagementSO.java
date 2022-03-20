package system_operation.employee_engagement;

import domain.EmployeeEngagement;
import repository.Repository;
import system_operation.AbstractSO;
import validation.Validator;

/**
 *
 * @author Dragon
 */
public class AddEmployeeEngagementSO extends AbstractSO {

    public AddEmployeeEngagementSO() {
        super();
    }

    public AddEmployeeEngagementSO(Repository repository) {
        super(repository);
    }

    public void executeAsSuboperation(Object param) throws Exception {
        precondition(param);
        executeOperation(param);
    }

    @Override
    protected void precondition(Object param) throws Exception {
        Validator.startValidation()
                .throwIfInvalideParameterInstance(param, "Parameter must be an instance of EmployeeEngagement", EmployeeEngagement.class)
                .validateNotNull(((EmployeeEngagement) param).getEmployee(), "Employee must not be null")
                .validateNotNull(((EmployeeEngagement) param).getRepairItem(), "RepairItem must not be null")
                .validateNumberIsNonNegative(((EmployeeEngagement) param).getDuration(), "Duration must be a non negative integer")
                .throwIfInvalide();
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.insertRecord(param);
    }
}
