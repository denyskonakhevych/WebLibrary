package model.server.authorization.authorization;

import inject.Inject;
import inject.clazz.ApplicationContextClass;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.db.dao.UserDao;

/**
 *
 * @author koxa
 */
public class Authorizator extends ApplicationContextClass{
    
    private static final String LOGIN_PARAMETER_NAME = "login";
    private static final String PASS_PARAMETER_NAME = "pass";
    private static final String USER_ATTRIBUTE_NAME = "user";
    private static final String LOGIN_TARGET_ATTRIBUTE_NAME = "login.target";
    private static final String AUT_ERROR_ATTRIBUTE_NAME = "aut_error";
    
    private static final String AUT_ERROR_MESSAGE = "Wrong password or username";

    private String login;
    private String pass;
    
    @Inject("UserDao")
    UserDao userDao;
    
    public Authorizator(HttpServletRequest request) {
        login = request.getParameter(LOGIN_PARAMETER_NAME);
        pass = request.getParameter(PASS_PARAMETER_NAME);
    }
    
    public void check(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        HttpSession session = request.getSession(true);
        //UserDao userDao = new UserDaoJdbc();
        
        if(userDao.checkUser(login, pass)) {
            //System.out.println("User Valid: username=" + login +"; pass=" + pass);
            // Valid login. Make a note in the session object.
            session.setAttribute(USER_ATTRIBUTE_NAME, userDao.getUserByNickname(login));
            // Try redirecting the client to the page he first tried to access
            try {
                String target = (String) session.getAttribute(LOGIN_TARGET_ATTRIBUTE_NAME);
                if (target != null) {
                    //System.out.println("try redirect to targer");
                    session.setAttribute(LOGIN_TARGET_ATTRIBUTE_NAME, null);
                    response.sendRedirect(target);
                } else {
                    redirectToHomepage(request, response);
                }
            } catch (Exception ignored) { 
                redirectToHomepage(request, response);
            }
        } else {
            //System.out.println("Wrong password or username : login=" + login + " password=" + pass);
            request.setAttribute(AUT_ERROR_ATTRIBUTE_NAME, AUT_ERROR_MESSAGE);
            request.getRequestDispatcher("./login.jsp").forward(request, response);
        }
    }
    
    private void redirectToHomepage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Couldn't redirect to the target. Redirect to the site's home page.
        
        String path = request.getRequestURI().substring(0, request.getContextPath().length());
        StringBuilder homepage = new StringBuilder();
        homepage.append(request.getScheme());
        homepage.append("://");
        homepage.append(request.getServerName());
        homepage.append(":");
        homepage.append(request.getServerPort());
        homepage.append(path);
        
        response.sendRedirect(homepage.toString());
    }
}
