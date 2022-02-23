/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system_operation.service;

import domain.Service;
import system_operation.AbstractSO;

/**
 *
 * @author Dragon
 */
public class GetServicesByConditionSO extends AbstractSO{

    @Override
    protected void precondition(Object param) throws Exception {
        //TODO: Add validator!
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Service service = (Service) param;
        result = repository.findRecords(service, service.getWhereCondition());
    }

    public Object getResult() {
        return result;
    }
}
