/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.database.broker;

import repository.Repository;

/**
 *
 * @author Dragon
 * @param <T>
 */
public abstract class DatabaseBroker<T> implements Repository<T>{
    
    public abstract void connect() throws Exception;
    public abstract void closeConnection() throws Exception;
    public abstract void commitTransaction() throws Exception;
    public abstract void rollbackTransaction() throws Exception;//TODO: They will probably throw Exception!
}
