package system_operation.employee;

import domain.Employee;
import system_operation.AbstractSO;
import thread.coordinator.ThreadCoordinator;
import validation.Validator;

/**
 *
 * @author Dragon
 */
public class DeleteEmployeeSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
        Validator.startValidation()
                .throwIfInvalideParameterInstance(param, "Parameter must be an instance of Employee", Employee.class);

        ThreadCoordinator.getInstance().throwIfAlreadyAuthenticated((Employee) param,
                "Employee with username: " + ((Employee) param).getUsername() + " cannot be deleted because he is currently logged in.");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.deleteRecord(param);
    }
}
