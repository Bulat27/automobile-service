/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.database.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 */
public class DBConnectionFactory {

    private Connection connection;
    private static DBConnectionFactory instance;

    private DBConnectionFactory() {}

    public static DBConnectionFactory getInstance() {
        if (instance == null) {
            instance = new DBConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException, IOException {

        //TODO: Read from configuration file!
        if (connection == null || connection.isClosed()) {
            try {
                //Class.forName("com.mysql.cj.jdbc.Driver");
                //String url = "jdbc:mysql://localhost:3306/psdbd22001";
                
//                Properties properties = new Properties();
//                properties.load(new FileInputStream("config/dbconfig.properties"));

                String url = "jdbc:mysql://localhost:3306/automobile_service";
//                String url = properties.getProperty(MyServerConstants.DB_CONFIG_URL);
                String user = "root";
//                String user = properties.getProperty(MyServerConstants.DB_CONFIG_USERNAME);
                String password = "root";
//                String password = properties.getProperty("password");

                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
            } catch (SQLException ex) {
                System.out.println("Error while establishing the database connection: " + ex.getMessage());
                throw ex;
            }
        }
        return connection;
    }
    
    public void closeConnection() throws SQLException{
        if(connection != null && !connection.isClosed()) connection.close();
    }
    
    public void commitTransaction() throws SQLException{
        if(connection != null && !connection.isClosed()) connection.commit();
    }
    
    public void rollbackTransaction() throws SQLException{
        if(connection != null && !connection.isClosed()) connection.rollback();
    }
}
