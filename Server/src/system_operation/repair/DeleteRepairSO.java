package system_operation.repair;

import system_operation.AbstractSO;

/**
 *
 * @author Dragon
 */
public class DeleteRepairSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.deleteRecord(param);
    }
}
