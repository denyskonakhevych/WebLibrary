/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import messages.MessageFactory;
import model.server.facade.ServerFacade;

/**
 *
 * @author koxa
 */
public class BookController extends HttpServlet {
    
    //private static final String MESSAGES_FILE = "listeners.messages";
    
    private static final String COMMAND_PARAMETER_NAME = "command";
    private static final String COMMAND_VIEW_ALL = "viewAll";
    private static final String COMMAND_ADD_TO_CART = "addToCart";
    private static final String COMMAND_REMOVE_FROM_CART = "removeFromCart";
    private static final String COMMAND_VIEW_DETAILS = "viewDetails";
    private static final String COMMAND_CHANGE_LANGUAGE = "changeLanguage";

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String command = request.getParameter(COMMAND_PARAMETER_NAME);
        ServerFacade serverFacade = new ServerFacade(request);
        switch (command) {
            case COMMAND_VIEW_ALL:
                serverFacade.setBooksList();
                serverFacade.setMenu();
                request.getRequestDispatcher("./books_list.jsp").forward(request, response);
                break;
            case COMMAND_ADD_TO_CART:
                serverFacade.addToCart();
                response.sendRedirect(request.getHeader("referer"));
                break;
            case COMMAND_REMOVE_FROM_CART:
                serverFacade.removeFromCart();
                response.sendRedirect(request.getHeader("referer"));
                break;
            case COMMAND_VIEW_DETAILS:
                serverFacade.setSelectedBook();
                request.getRequestDispatcher("./book_details.jsp").forward(request, response);
                break;
            case COMMAND_CHANGE_LANGUAGE:
                String language = request.getParameter("language");
                MessageFactory messageFactory = new MessageFactory();
                ResourceBundle bundle = messageFactory.getBundle(language);
                
                HttpSession session = request.getSession();
                session.setAttribute("messages", bundle);
                response.sendRedirect(request.getHeader("referer"));
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
