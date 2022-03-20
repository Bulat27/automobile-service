package system_operation.service;

import domain.Service;
import system_operation.AbstractSO;
import validation.Validator;

/**
 *
 * @author Dragon
 */
public class GetServicesByConditionSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
        Validator.startValidation()
                .throwIfInvalideParameterInstance(param, "Parameter must be an instance of Service", Service.class);
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Service service = (Service) param;
        result = repository.findRecords(service, service.getAttributeValuesWhereCondition());
    }

    public Object getResult() {
        return result;
    }
}
