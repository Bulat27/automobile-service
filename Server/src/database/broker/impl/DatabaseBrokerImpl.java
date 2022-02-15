/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.broker.impl;

import database.broker.DatabaseBroker;
import database.connection.DBConnectionFactory;
import domain.GeneralDObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dragon
 */
public class DatabaseBrokerImpl extends DatabaseBroker {

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
    public List<GeneralDObject> findRecords(GeneralDObject gdo) throws Exception {
        String query = "SELECT * FROM " + gdo.getTableName();//TODO: Add the JOIN for gdo.getJOIN() or something like that!
        List<GeneralDObject> listGDO = new ArrayList<>();

        try (Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                listGDO.add(gdo.getNewRecord(rs));
            }
            return listGDO;
        }
    }

}
