package listeners;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import messages.MessageFactory;
import model.server.cart.ShoppingCart;

/**
 * Web application lifecycle listener.
 *
 * @author koxa
 */
public class SessionInitializer implements ServletContextListener, HttpSessionListener {
    
    private static final String SHOPPING_CART_ATTRIBUTE_NAME = "shoppingCart";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //NOP
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //NOP
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setAttribute(SHOPPING_CART_ATTRIBUTE_NAME, new AtomicReference<ShoppingCart>());
        MessageFactory messageFactory = new MessageFactory();
        ResourceBundle bundle = messageFactory.getBundle("en");
        
        session.setAttribute("messages", bundle);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //NOP
    }
}
