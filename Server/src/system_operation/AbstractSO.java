/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system_operation;

import database.broker.DatabaseBroker;
import database.broker.impl.DatabaseBrokerImpl;
import domain.GeneralDObject;

/**
 *
 * @author Dragon
 */
public abstract class AbstractSO {
    
    //TODO: Add a reference to a Database broker (if each SO needs it)
    protected final DatabaseBroker dbBroker;
    protected GeneralDObject result;

    public AbstractSO() {
        dbBroker = new DatabaseBrokerImpl();
    }
    
     public void execute(Object param) throws Exception {
        try {
            precondition(param);
            startTransaction();
            executeOperation(param);
            commitTransaction();
            System.out.println("Successful operation!");//TODO: Delete all of these "prints"
        } catch (Exception ex) {
            System.out.println("Unsuccessful operation!");
            rollbackTransaction();
            throw ex;
        }finally{
            closeConnection();//TODO: Check whether you have to close it each time?
        }
    }

    protected abstract void precondition(Object param);
    
    protected abstract void executeOperation(Object param) throws Exception;
     
    //TODO: Implement these when you implement the Database broker!
    private void startTransaction() throws Exception {
        dbBroker.connect();
    }

    private void commitTransaction() throws Exception {
        dbBroker.commitTransaction();
    }

    private void rollbackTransaction() throws Exception {
        dbBroker.rollbackTransaction();
    }

    private void closeConnection() throws Exception {
       dbBroker.closeConnection();
    }
     
     
}
