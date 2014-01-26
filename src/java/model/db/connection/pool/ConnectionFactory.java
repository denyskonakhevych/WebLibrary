/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.connection.pool;

import model.db.connection.exception.ConnectionPoolLibraryException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author koxa
 */
public class ConnectionFactory {
    
    private final static String LOG_PROPERTY_FILE_PATH = "C:/Users/koxa/Documents/NetBeansProjects/WebLibrary/build/web/WEB-INF/classes/model/db/connection/pool/log4j.xml";
    private final static String DB_PROPERTY_FILE_PATH = "C:/Users/koxa/Documents/NetBeansProjects/WebLibrary/build/web/WEB-INF/classes/model/db/connection/pool/db.properties";
    
    private final static String DB_SERVER = "server";
    private final static String DB_DATABASE = "database";
    private final static String USER = "user";
    private final static String PASS = "pass";
    
    private static ConnectionPool connectionPool;
    private static final Logger logger = Logger.getLogger("db.logger");
    
    static {
        DOMConfigurator.configure(LOG_PROPERTY_FILE_PATH);
        String connectionString;
        String connectionUser;
        String connectionPass;
        
        try(FileInputStream in = new FileInputStream(DB_PROPERTY_FILE_PATH)){
            Properties props = new Properties();
            props.load(in);
            connectionString = props.getProperty(DB_SERVER) + "/" + props.getProperty(DB_DATABASE);
            connectionUser = props.getProperty(USER);
            connectionPass = props.getProperty(PASS);
            
            connectionPool = new ConnectionPool(connectionString, connectionUser, connectionPass);
            
            log("Connection with db established successfully");
        } catch (IOException ex) {
            failLog(ex.getMessage());
        } catch (SQLException ex) {
            failLog(ex.getMessage());
        } catch (Exception ex) {
            failLog(ex.getMessage());
        }
    }
    
    /**
     *
     * @return
     * @throws SQLException
     * @throws ConnectionPoolLibraryException
     */
    public static synchronized ConnectionProxy getConnection() throws SQLException, ConnectionPoolLibraryException {
        return new ConnectionProxy(connectionPool);
    }
    
    private static synchronized void log(String message) {
        logger.info(message);
    }
    
    private static synchronized void failLog(String message) {
        logger.error(message);
    }
}
