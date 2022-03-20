package system_operation.employee_engagement;

import domain.EmployeeEngagement;
import repository.Repository;
import system_operation.AbstractSO;
import validation.Validator;

/**
 *
 * @author Dragon
 */
public class GetEmployeeEngagementsByFKConditionSO extends AbstractSO {

    public GetEmployeeEngagementsByFKConditionSO() {
        super();
    }

    public GetEmployeeEngagementsByFKConditionSO(Repository repository) {
        super(repository);
    }

    public void executeAsSuboperation(Object param) throws Exception {
        precondition(param);
        executeOperation(param);
    }

    @Override
    protected void precondition(Object param) throws Exception {
        Validator.startValidation()
                .throwIfInvalideParameterInstance(param, "Parameter must be an instance of EmployeeEngagement", EmployeeEngagement.class);
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        EmployeeEngagement employeeEngagement = (EmployeeEngagement) param;
        result = repository.findRecords(employeeEngagement, employeeEngagement.getFKWhereCondition());
    }

    public Object getResult() {
        return result;
    }
}
