package tags;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpSession;
import model.db.entities.User;

/**
 *
 * @author koxa
 */

public class UserGreatings extends SimpleTagSupport {

    private static final String MESSAGES_FILE = "messages.messages";
    //private final static String MESSAGES_FILE = "WEB-INF.classes.messages.messages.";
    
    private User user;

    public UserGreatings() {
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public void doTag() throws JspException, IOException {

        JspWriter out = getJspContext().getOut();
        
        PageContext pageContext = (PageContext)getJspContext();
        HttpSession session = pageContext.getSession();
        ResourceBundle bundle = (ResourceBundle) session.getAttribute("messages");
        if (bundle == null) {
            Locale locale = new Locale("en", "US");
            //bundle = ResourceBundle.getBundle(MESSAGES_FILE, locale);
            bundle = ResourceBundle.getBundle(MESSAGES_FILE, locale);
        }
        String msg;
        if (user != null) {
            msg = bundle.getString("autentification.user.registered") + " " + user.getFirstName();
        } else {
            msg = bundle.getString("autentification.user.unregistered");
        }
        out.println(msg);
    }
}
