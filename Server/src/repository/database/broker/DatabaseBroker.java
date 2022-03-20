
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
    public abstract void rollbackTransaction() throws Exception;
}
