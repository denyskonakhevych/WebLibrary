package model.server.authorization.registration;

import inject.Inject;
import inject.clazz.ApplicationContextClass;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.db.dao.CardDao;
import model.db.dao.UserDao;
import model.db.entities.Card;
import model.db.entities.User;

/**
 *
 * @author koxa
 */
public class Registration extends ApplicationContextClass {
    private static final String NICK_PARAMETER_NAME = "nick";
    private static final String PASSWORD_PARAMETER_NAME = "password";
    private static final String FIRST_NAME_PARAMETER_NAME = "first_name";
    private static final String SECOND_NAME_PARAMETER_NAME = "second_name";
    private static final String ADDRESS_PARAMETER_NAME = "address";
    private static final String EMAIL_PARAMETER_NAME = "email";
    
    private static final String REQ_NICK_ATTRIBUTE_NAME = "req_nick";
    private static final String REQ_PASSWORD_ATTRIBUTE_NAME = "req_password";
    private static final String REQ_FIRST_NAME_ATTRIBUTE_NAME = "req_first_name";
    private static final String REQ_SECOND_NAME_ATTRIBUTE_NAME = "req_second_name";
    private static final String REQ_ADDRESS_ATTRIBUTE_NAME = "req_address";
    private static final String REQ_EMAIL_ATTRIBUTE_NAME = "req_email";
    
    private static final String INVALID_NICKNAME_MESSAGE = "Nick name isn’t valid! It may consists of letters, numbers and '_', '-' signs. The length of nick name must be from 3 to 20 characters";
    private static final String NICKNAME_ALREADY_EXISTS_MESSAGE = "User with this nickname already exists";
    private static final String INVALID_PASSWORD_MESSAGE = "Password isn’t valid! It may consists of at least one lower case letter, one upper case letters, one number, one special character (@, #, $, %, ^, &, +, =) and no whitespace allowed. The length of password must be from 8 to 64 characters";
    private static final String INVALID_FIRST_NAME_MESSAGE = "First name isn’t valid! It may consists of letters and the length of first name must be up to 32 characters";
    private static final String INVALID_LAST_NAME_MESSAGE = "Last name isn’t valid! It may consists of letters and the length of last name must be up to 32 characters";
    private static final String INVALID_EMAIL_MESSAGE = "Email isn’t valid!";
    
    private String nick;
    private String password;
    private String firstName;
    private String secondName;
    private String address;
    private String email;
    
    @Inject("UserDao")
    UserDao userDao;
    @Inject("CardDao")
    CardDao cardDao;

    public Registration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
        nick = request.getParameter(NICK_PARAMETER_NAME);
        request.setAttribute(REQ_NICK_ATTRIBUTE_NAME, nick);
        
        password = request.getParameter(PASSWORD_PARAMETER_NAME);
        request.setAttribute(REQ_PASSWORD_ATTRIBUTE_NAME, password);
        
        firstName = request.getParameter(FIRST_NAME_PARAMETER_NAME);
        request.setAttribute(REQ_FIRST_NAME_ATTRIBUTE_NAME, firstName);
        
        secondName = request.getParameter(SECOND_NAME_PARAMETER_NAME);
        request.setAttribute(REQ_SECOND_NAME_ATTRIBUTE_NAME, secondName);
        
        address = request.getParameter(ADDRESS_PARAMETER_NAME);
        request.setAttribute(REQ_ADDRESS_ATTRIBUTE_NAME, address);
        
        email = request.getParameter(EMAIL_PARAMETER_NAME);
        request.setAttribute(REQ_EMAIL_ATTRIBUTE_NAME, email);
    }
    
    public boolean Validate() throws RegistrationException {
        
        RegistrationValidation validator = new RegistrationValidation();
        
        if (!validator.validateNickname(nick)) {
            throw new RegistrationException(INVALID_NICKNAME_MESSAGE);
        } else if (userDao.getUserByNickname(nick) != null) {
            throw new RegistrationException(NICKNAME_ALREADY_EXISTS_MESSAGE);
        } else if (!validator.validatePassword(password)) {
            throw new RegistrationException(INVALID_PASSWORD_MESSAGE);
        } else if (!validator.validateFirstOrLastName(firstName)) {
            throw new RegistrationException(INVALID_FIRST_NAME_MESSAGE);
        } else if (!validator.validateFirstOrLastName(secondName)) {
            throw new RegistrationException(INVALID_LAST_NAME_MESSAGE);
        } else if (!validator.validateEmail(email)) {
            throw new RegistrationException(INVALID_EMAIL_MESSAGE);
        } else {
            return true;
        }
    }
    
    public void register() { 
        
        User user = new User();
        
        user.setLogin(nick);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setAddress(address);
        user.setEmail(email);
        user.setRole(User.Role.USER);
        userDao.insert(user);
        
        Card card = new Card();
        card.setUser(user);
        cardDao.insert(card);
    }
}