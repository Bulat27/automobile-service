package system_operation.service_book;

import domain.ServiceBook;
import system_operation.AbstractSO;
import validation.Validator;

/**
 *
 * @author Dragon
 */
public class DeleteServiceBookSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
        Validator.startValidation()
                .throwIfInvalideParameterInstance(param, "Parameter must be an instance of ServiceBook", ServiceBook.class);
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.deleteRecord(param);
    }
}
