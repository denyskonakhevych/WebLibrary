package model.db.connection.pool;

import model.db.connection.exception.ConnectionPoolLibraryException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import org.apache.log4j.Logger;

/**
 *
 * @author koxa
 */
public class ConnectionPool implements Runnable {
    
    //private static final Logger logger = Logger.getLogger(ConnectionPool.class.getName());
    private static final Logger logger = Logger.getLogger("db.logger");
    
     // Number of initial connections to make.   
    private int minConnectionCount = 5;
    
    private int maxConnectionCount = 10;
    
    private int currentConnectionCount = 0;
      
    // A list of available connections for use.   
    private Vector availableConnections = new Vector();   
      
    // A list of connections being used currently.   
    private Vector usedConnections = new Vector();   
      
    // The URL string used to connect to the database   
    private String urlString = null;   
      
    // The username used to connect to the database   
    private String userName = null;       
      
    // The password used to connect to the database   
    private String password = null;       
      
    // The cleanup thread   
    private Thread cleanupThread = null;   
           
                                                
    //Constructor   
    public ConnectionPool(String url, String user, String passwd) throws SQLException   
    {   
        // Initialize the required parameters   
        urlString = url;   
        userName = user;   
        password = passwd;   
  
        for (int cnt = 0; cnt < minConnectionCount; cnt++)
        {   
            log("Add new connection to the available list");
            
            availableConnections.addElement(createConnection());   
        }
        log("Create the cleanup thread");
        cleanupThread = new Thread(this);   
        cleanupThread.start();
    }       
    
    private Connection createConnection() throws SQLException   
    {   
        return DriverManager.getConnection(urlString, userName, password);   
    }   
       
    public synchronized Connection getConnection() throws SQLException, ConnectionPoolLibraryException
    {   
        Connection newConnxn = null;   
           
        if(availableConnections.isEmpty())   
        {   
            if (currentConnectionCount < maxConnectionCount) {
                log("Pool is empty. Create one more connectio");
                newConnxn = createConnection();
                log("Add this connection to the \"Used\" list");
                usedConnections.addElement(newConnxn);    
                currentConnectionCount++;
            } else {
                failLog("Too many connections");
                throw new ConnectionPoolLibraryException("Server is too busy. Try later.");
            }
        }   
        else   
        {   
            log("Connection exists. Got connection onject");
            newConnxn = (Connection)availableConnections.lastElement();   
            log("Remove it from the available list");
            availableConnections.removeElement(newConnxn);
            log("Add it to the used list");
            usedConnections.addElement(newConnxn);  
            currentConnectionCount++;
            log("Got connection from pool");
        }           
        return newConnxn;   
    }   
       
  
    public synchronized void returnConnection(Connection c)
    {   
        if(c != null)   
        {   
            log("Remove from used list");
            usedConnections.removeElement(c);
            log("Connection returned");
            availableConnections.addElement(c); 
        }   
    }               
       
    public int getAvailableConnectionsCount()   
    {   
        return availableConnections.size();   
    }   
       
    @Override
    public void run()   
    {   
        try   
        {   
            while(true)   
            {   
                synchronized(this)   
                {   
                    while(availableConnections.size() > minConnectionCount)   
                    {   
                        log("Clean up extra available connections");
                        Connection c = (Connection)availableConnections.lastElement();   
                        availableConnections.removeElement(c);   
                        log("Close the connection to the database");
                        c.close();   
                    }   
                }
                log("CLEANUP: Cleanup complited! Available Connections: " + getAvailableConnectionsCount());
                // Sleep for 1 minute   
                Thread.sleep(60000 * 1);   
            }       
        }   
        catch(SQLException sqlex)   
        {   
            failLog(sqlex.getMessage());
        }   
        catch(Exception e)   
        {   
            failLog(e.getMessage()); 
        }   
    }
    
    private static synchronized void log(String message) {
        logger.info(message);
    }
    
    private static synchronized void failLog(String message) {
        logger.error(message);
    }
}