package system_operation.service_book;

import domain.ServiceBook;
import system_operation.AbstractSO;
import validation.Validator;

/**
 *
 * @author Dragon
 */
public class AddServiceBookSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
        Validator.startValidation()
                .throwIfInvalideParameterInstance(param, "Parameter must be an instance of ServiceBook", ServiceBook.class)
                .validateValueIsAllAlphabets(((ServiceBook) param).getClientFirstName(), "Client first name must not be null or empty and must contain only alphabetic characters")
                .validateValueIsAllAlphabets(((ServiceBook) param).getClientLastName(), "Client last name must not be null or empty and must contain only alphabetic characters")
                .validateNotNullOrEmpty(((ServiceBook) param).getVehicleDescription(), "Vehicle decription must not be null or empty")
                .validateNotNull(((ServiceBook) param).getInitialDate(), "Initial date must not be null")
                .throwIfInvalide();
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.insertRecord(param);
    }
}
