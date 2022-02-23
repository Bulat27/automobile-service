/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.database.broker.impl;

import repository.database.broker.DatabaseBroker;
import repository.database.connection.DBConnectionFactory;
import domain.GeneralDObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        String query = "SELECT * FROM " + gdo.getTableName();//TODO: Add the JOIN for gdo.getJOIN() or something like that!
        if(whereCondition != null) query += " WHERE " + whereCondition;
        
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

//     public boolean executeUpdate(String upit) {
//        Statement st = null;
//        boolean signal = false;
//        try {
//            st = conn.createStatement();
//            int rowcount = st.executeUpdate(upit);
//            if (rowcount > 0) {
//                signal = true;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(BrokerBazePodataka1.class.getName()).log(Level.SEVERE, null, ex);
//            signal = false;
//        } finally {
//            close(null, st, null);
//        }
//        return signal;
//    }
    //Works for INSERT and UPDATE, not for delete
    public void executePreparedStatementUpdate(String query, GeneralDObject gdo) throws Exception {
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            gdo.setPreparedStatementParameters(ps);
            int rowCount = ps.executeUpdate();
            if (rowCount <= 0) {
                throw new Exception("No rows affected, failed insert or update");
            }
        }
    }

    @Override
    public void deleteRecord(GeneralDObject gdo) throws Exception {
        String query = "DELETE FROM " + gdo.getTableName() + " WHERE " + gdo.getPKWhereCondition();
        //TODO: Maybe add execute NonPreparedUpdate
        try (Statement st = connection.createStatement();) {
            int rowCount = st.executeUpdate(query);
            if (rowCount <= 0) {
                throw new Exception("No rows affected, failed delete");
            }
        }
    }
}
