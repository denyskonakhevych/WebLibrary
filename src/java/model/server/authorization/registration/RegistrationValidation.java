package model.server.authorization.registration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author koxa
 */
public class RegistrationValidation {
    
    private static final String NICK_NAME_EXPRESSION = "^[A-Za-z0-9_-]{3,20}$";
    
    /*
        ^                 # start-of-string
        (?=.*[0-9])       # a digit must occur at least once
        (?=.*[a-z])       # a lower case letter must occur at least once
        (?=.*[A-Z])       # an upper case letter must occur at least once
        (?=.*[@#$%^&+=])  # a special character must occur at least once
        (?=\S+$)          # no whitespace allowed in the entire string
        .{8,}             # anything, at least eight places though
        $                 # end-of-string
    */
    private static final String PASSWORD_EXPRESSION = 
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,64}$";
    
    private static final String FIRST_AND_LAST_NAME_EXPRESSION = "^[A-ZА-Яa-zа-я]{1,32}$";
    
    private static final String EMAIL_EXPRESSION = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    private static final Pattern NICK_NAME_PATTERN = Pattern.compile(NICK_NAME_EXPRESSION);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_EXPRESSION);
    private static final Pattern FIRST_AND_LAST_NAME_PATTERN = Pattern.compile(FIRST_AND_LAST_NAME_EXPRESSION);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_EXPRESSION);
    
    public boolean validateNickname(String nick){
        Matcher m = NICK_NAME_PATTERN.matcher(nick);
        return m.matches(); 
    } 
    
    public boolean validatePassword(String nick){
        Matcher m = PASSWORD_PATTERN.matcher(nick);
        return m.matches(); 
    } 
    
    public boolean validateFirstOrLastName(String nick){
        Matcher m = FIRST_AND_LAST_NAME_PATTERN.matcher(nick);
        return m.matches(); 
    }
    
    public boolean validateEmail(String nick){
        Matcher m = EMAIL_PATTERN.matcher(nick);
        return m.matches(); 
    }
}