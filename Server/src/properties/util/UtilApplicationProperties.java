/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package properties.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Dragon
 */
public class UtilApplicationProperties {

    private final Properties properties;
    private static UtilApplicationProperties instance;

    private static final String APPLICATION_PROPERTIES_FILE_PATH = "config/application.properties";
    private static final String APPLICATION_PROPERTIES_DB_URL = "db.url";
    private static final String APPLICATION_PROPERTIES_DB_USERNAME = "db.username";
    private static final String APPLICATION_PROPERTIES_DB_PASSWORD = "db.password";
    private static final String APPLICATION_PROPERTIES_SERVER_PORT = "server.port";

    private UtilApplicationProperties() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(new File(APPLICATION_PROPERTIES_FILE_PATH)));
    }

    public static UtilApplicationProperties getInstance() throws IOException {
        if (instance == null) {
            instance = new UtilApplicationProperties();
        }
        return instance;
    }
    
    public String getDatabaseURL(){
        return properties.getProperty(APPLICATION_PROPERTIES_DB_URL);
    }
    
    public String getDatabaseUsername(){
        return properties.getProperty(APPLICATION_PROPERTIES_DB_USERNAME);
    }
    
    public String getDatabasePassword(){
        return properties.getProperty(APPLICATION_PROPERTIES_DB_PASSWORD);
    }
    
    public int getServerPort(){
        return Integer.parseInt(properties.getProperty(APPLICATION_PROPERTIES_SERVER_PORT));
    }
}
