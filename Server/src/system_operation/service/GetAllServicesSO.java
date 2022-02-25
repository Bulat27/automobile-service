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
public class GetAllServicesSO extends AbstractSO{

    @Override
    protected void precondition(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        result = repository.findRecords(new Service(), null);
    }

    public Object getResult() {
        return result;
    } 
}
