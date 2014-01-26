/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author koxa
 */
public class MessageFactory {
    private static final String MESSAGES_FILE = "messages.messages";
    
    public Locale getLocale(String language) {
        if (language == null) {
            throw new IllegalArgumentException("Expected language but found: " + language);
        }
        Locale locale = Locale.getDefault();
        switch (language) {
            case "en":
                locale = Locale.US;
                break;
            case "ru":
                locale = new Locale("ru", "RU");
                break;
            default:
                // Required locale was not found. Returned deafault locale.
        }
        return locale;
    }
    
    public ResourceBundle getBundle(String language) {
        return getBundle(getLocale(language));
    }
    
    public ResourceBundle getBundle(Locale locale) {
        if (locale == null) {
            throw new IllegalArgumentException("Expected locale but found: " + locale);
        }
        try {
            return ResourceBundle.getBundle(MESSAGES_FILE, locale);
        } catch (MissingResourceException ex) {
            locale = Locale.getDefault();
            return ResourceBundle.getBundle(MESSAGES_FILE, locale);
        }
    }
}
