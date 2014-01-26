package filters;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.db.entities.User;
import org.apache.log4j.Logger;

/**
 *
 * @author koxa
 */
public class LoginFilter implements Filter {
    
    private static final String COMMAND_PARAMETER_NAME = "command";
    private static final String USER_ATTRIBUTE_NAME = "user";
    private static final String LOGIN_TARGET_ATTRIBUTE_NAME = "login.target";
    
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        
        String command = request.getParameter(COMMAND_PARAMETER_NAME);
        String requestURI = request.getRequestURI() + "?command=" + command;
        
        if (requestURI.contains("/CartController.do?command=submitOrder")) {
            HttpSession session = request.getSession(true);
            User user = (User) session.getAttribute(USER_ATTRIBUTE_NAME);
            if (user == null) {
                log("User is anonimus. Send to login form");
                session.setAttribute(LOGIN_TARGET_ATTRIBUTE_NAME, requestURI);                
                response.sendRedirect("./login.do");
            } else {
                log("User is ok: " + user.toString());
                chain.doFilter(req, resp);
            }
        } else {
            chain.doFilter(req, resp);
        }
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
        // NOP
    }

    /**
     * Init method for this filter
     */
    @Override
    public void init(FilterConfig filterConfig) {
        //NOP
    }
    
    public void log(String message) {
        Logger logger = Logger.getLogger("aut.logger");
        logger.info(message);
    }
}