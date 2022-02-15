/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.broker;

import domain.GeneralDObject;
import java.util.List;

/**
 *
 * @author Dragon
 */
public abstract class DatabaseBroker {
    
    public abstract void connect() throws Exception;
    public abstract void closeConnection() throws Exception;
    public abstract void commitTransaction() throws Exception;
    public abstract void rollbackTransaction() throws Exception;//TODO: They will probably throw Exception!
    public abstract List<GeneralDObject> findRecords(GeneralDObject gdo) throws Exception;
}
