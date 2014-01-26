package model.db.connection.exception;

/**
 *
 * @author koxa
 */
public class ConnectionPoolLibraryException extends LibraryException {

    public ConnectionPoolLibraryException() {
        super();
    }
    
    public ConnectionPoolLibraryException(String message) {
        super(message);
    }
}