/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import inject.servlet.ApplicationContextServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.server.facade.ServerFacade;

/**
 *
 * @author koxa
 */
public class CartController extends ApplicationContextServlet {
    
    public static final String COMMAND_PARAMETER_NAME = "command";
    private static final String COMMAND_SUBMIT_ORDER = "submitOrder";
    private static final String COMMAND_EXECUTE_ORDER = "executeOrder";

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
        response.setContentType("text/html;charset=UTF-8");
        
        String command = request.getParameter(COMMAND_PARAMETER_NAME);
        ServerFacade serverFacade = new ServerFacade(request);
        switch (command) {
            case COMMAND_SUBMIT_ORDER:
                request.getRequestDispatcher("./order.jsp").forward(request, response);
                break;
            case COMMAND_EXECUTE_ORDER:
                serverFacade.proceedOrder();
                response.sendRedirect("./BookController.do?command=viewAll"); 
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
