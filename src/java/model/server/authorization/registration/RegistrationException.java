package model.server.authorization.registration;

/**
 *
 * @author koxa
 */
public class RegistrationException extends Exception{
    
    private static final String WRONG_REGISTRION_DATA_EXCEPTION_MESSAGE = "Something wrong with registrion data";

    public RegistrationException() {
        super(WRONG_REGISTRION_DATA_EXCEPTION_MESSAGE);
    }

    public RegistrationException(String message) {
        super(message);
    }   
}