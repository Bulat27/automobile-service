package system_operation.service_book;

import system_operation.AbstractSO;

/**
 *
 * @author Dragon
 */
public class DeleteServiceBookSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
        //TODO: Add Validator if neccessary!
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.deleteRecord(param);
    }

}
