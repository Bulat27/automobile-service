/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system_operation.service;

import domain.Employee;
import domain.Service;
import system_operation.AbstractSO;

/**
 *
 * @author Dragon
 */
public class SaveServiceSO extends AbstractSO{

    @Override
    protected void precondition(Object param) throws Exception {
        if(param == null || !(param instanceof Service)){
            throw new Exception("Invalid parameter");//TODO: This can be generalized
        }
        //TODO: Add validator!
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.insertRecord(param);//TODO: CHECK OUT WHY THIS WORKS WITHOUT CASTING! GENERICS|!
    }
    
}
