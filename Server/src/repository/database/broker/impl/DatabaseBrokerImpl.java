package repository.database.broker.impl;

import repository.database.broker.DatabaseBroker;
import repository.database.connection.DBConnectionFactory;
import domain.GeneralDObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dragon
 */
public class DatabaseBrokerImpl extends DatabaseBroker<GeneralDObject> {

    private Connection connection;

    @Override
    public void connect() throws Exception {
        connection = DBConnectionFactory.getInstance().getConnection();
    }

    @Override
    public void closeConnection() throws Exception {
        DBConnectionFactory.getInstance().closeConnection();
    }

    @Override
    public void commitTransaction() throws Exception {
        DBConnectionFactory.getInstance().commitTransaction();
    }

    @Override
    public void rollbackTransaction() throws Exception {
        DBConnectionFactory.getInstance().rollbackTransaction();
    }

    @Override
    public List<GeneralDObject> findRecords(GeneralDObject gdo, String whereCondition) throws Exception {
        String query = "SELECT * FROM " + gdo.getTableName() + gdo.getJoinCondition();
        if (whereCondition != null) {
            query += " WHERE " + whereCondition;
        }

        List<GeneralDObject> listGDO = new ArrayList<>();

        try (Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                listGDO.add(gdo.getNewRecord(rs));
            }
            return listGDO;
        }
    }

    @Override
    public void insertRecord(GeneralDObject gdo) throws Exception {
        String query = "INSERT INTO " + gdo.getTableName() + "(" + gdo.getInsertionColumns() + ") VALUES(" + gdo.getAtrPlaceHolders() + ")";
        executePreparedStatementUpdate(query, gdo);
    }

    @Override
    public void updateRecord(GeneralDObject gdo) throws Exception {
        String query = "UPDATE " + gdo.getTableName() + " SET " + gdo.getUpdateColumnsWithPlaceHolders() + " WHERE " + gdo.getPKWhereCondition();
        executePreparedStatementUpdate(query, gdo);
    }

    //Works for INSERT and UPDATE, not for delete
    public void executePreparedStatementUpdate(String query, GeneralDObject gdo) throws Exception {
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            gdo.setPreparedStatementParameters(ps);
            int rowCount = ps.executeUpdate();

            if (rowCount <= 0) {
                throw new Exception("No rows affected, failed insert or update");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    gdo.setAutoGeneratedKey(rs.getLong(1));
                }
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new Exception("Structural constraint of the table '" + gdo.getTableName() + "' is violated!");
        }
    }

    @Override
    public void deleteRecord(GeneralDObject gdo) throws Exception {
        String query = "DELETE FROM " + gdo.getTableName() + " WHERE " + gdo.getPKWhereCondition();

        try (Statement st = connection.createStatement()) {
            int rowCount = st.executeUpdate(query);

            if (rowCount <= 0) {
                throw new Exception("No rows affected, failed delete");
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new Exception("Structural constraint of the table '" + gdo.getTableName() + "' is violated!");
        }
    }

    @Override
    public void deleteRecords(GeneralDObject gdo, String whereCondition) throws Exception {
        String query = "DELETE FROM " + gdo.getTableName() + " WHERE " + whereCondition;

        try (Statement st = connection.createStatement()) {
            st.executeUpdate(query);
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new Exception("Structural constraint of the table '" + gdo.getTableName() + "' is violated!");
        }
    }
}
