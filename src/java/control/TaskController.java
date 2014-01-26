/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import inject.servlet.ApplicationContextServlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.db.common.DbTask;
import model.db.entities.Loan;
import model.db.entities.ReadingRoom;
import model.server.task.TaskCommand;
import model.server.task.TaskCommandFactory;

/**
 *
 * @author koxa
 */
public class TaskController extends ApplicationContextServlet {
    
    private static final String COMMAND_PARAMETER_NAME = "command";
    private static final String VIEW_ALL_ORDERED_LOANS = "viewAllOrderedLoans";
    private static final String VIEW_ALL_TAKEN_LOANS = "viewAllTakenLoans";
    private static final String VIEW_ALL_ORDERED_READING_ROOMS = "viewAllOrderedReadingRooms";
    private static final String VIEW_ALL_TAKEN_READING_ROOMS = "viewAllTakenReadingRooms";
    private static final String PROCEED_ORDERED_LOAN = "proceedOrderedLoan";
    private static final String COMPLETE_ORDERED_LOAN = "completeOrderedLoan";
    private static final String PROCEED_ORDERED_READING_ROOM = "proceedOrderedReadingRoom";
    private static final String COMPLETE_ORDERED_READING_ROOM = "completeOrderedReadingRoom";
    
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
        
        switch (command.intern()) {
            case VIEW_ALL_ORDERED_LOANS:
                {
                    TaskCommandFactory taskCommandFactory = new TaskCommandFactory();
                    TaskCommand taskCommand = taskCommandFactory.getCommand(TaskCommand.Type.LOAN);
                    List<Loan> orderedLoans = (List<Loan>) taskCommand.getByStatus(DbTask.Status.ORDERED);
                    
                    request.setAttribute("ordered_loans", orderedLoans);
                    request.getRequestDispatcher("./tasks_list.jsp").forward(request, response);
                    break;
                }
            case VIEW_ALL_TAKEN_LOANS:
                {
                    TaskCommandFactory taskCommandFactory = new TaskCommandFactory();
                    TaskCommand taskCommand = taskCommandFactory.getCommand(TaskCommand.Type.LOAN);
                    List<Loan> takenLoans = (List<Loan>) taskCommand.getByStatus(DbTask.Status.TAKEN);
                    
                    request.setAttribute("taken_loans", takenLoans);
                    request.getRequestDispatcher("./tasks_list.jsp").forward(request, response);
                    break;
                }
            case VIEW_ALL_ORDERED_READING_ROOMS:
                {
                    TaskCommandFactory taskCommandFactory = new TaskCommandFactory();
                    TaskCommand taskCommand = taskCommandFactory.getCommand(TaskCommand.Type.READING_ROOM);
                    List<ReadingRoom> orderedReadingRooms = (List<ReadingRoom>) taskCommand.getByStatus(DbTask.Status.ORDERED);
                    
                    request.setAttribute("ordered_reading_rooms", orderedReadingRooms);
                    request.getRequestDispatcher("./tasks_list.jsp").forward(request, response);
                    break;
                }
            case VIEW_ALL_TAKEN_READING_ROOMS:
                {
                    TaskCommandFactory taskCommandFactory = new TaskCommandFactory();
                    TaskCommand taskCommand = taskCommandFactory.getCommand(TaskCommand.Type.READING_ROOM);
                    List<ReadingRoom> takenReadingRooms = (List<ReadingRoom>) taskCommand.getByStatus(DbTask.Status.TAKEN);
                    
                    request.setAttribute("taken_reading_rooms", takenReadingRooms);
                    request.getRequestDispatcher("./tasks_list.jsp").forward(request, response);
                    break;
                }
            case PROCEED_ORDERED_LOAN:
                {
                    int isbn = Integer.valueOf(request.getParameter("isbn"));
                    int cardId = Integer.valueOf(request.getParameter("card"));
                    
                    TaskCommandFactory taskCommandFactory = new TaskCommandFactory();
                    TaskCommand taskCommand = taskCommandFactory.getCommand(TaskCommand.Type.LOAN);
                    taskCommand.manageTask(isbn, cardId, DbTask.Status.ORDERED);
                    
                    response.sendRedirect(request.getHeader("referer"));
                    break;
                }
            case COMPLETE_ORDERED_LOAN:
                {
                    int isbn = Integer.valueOf(request.getParameter("isbn"));
                    int cardId = Integer.valueOf(request.getParameter("card"));
                    
                    TaskCommandFactory taskCommandFactory = new TaskCommandFactory();
                    TaskCommand taskCommand = taskCommandFactory.getCommand(TaskCommand.Type.LOAN);
                    taskCommand.completeTask(isbn, cardId, DbTask.Status.TAKEN);
                    
                    response.sendRedirect(request.getHeader("referer"));
                    break;
                }
            case PROCEED_ORDERED_READING_ROOM:
                {
                    int isbn = Integer.valueOf(request.getParameter("isbn"));
                    int cardId = Integer.valueOf(request.getParameter("card"));
                    
                    TaskCommandFactory taskCommandFactory = new TaskCommandFactory();
                    TaskCommand taskCommand = taskCommandFactory.getCommand(TaskCommand.Type.READING_ROOM);
                    taskCommand.manageTask(isbn, cardId, DbTask.Status.ORDERED);
                    
                    response.sendRedirect(request.getHeader("referer"));
                    break;
                }
            case COMPLETE_ORDERED_READING_ROOM:
                {
                    int isbn = Integer.valueOf(request.getParameter("isbn"));
                    int cardId = Integer.valueOf(request.getParameter("card"));
                    
                    TaskCommandFactory taskCommandFactory = new TaskCommandFactory();
                    TaskCommand taskCommand = taskCommandFactory.getCommand(TaskCommand.Type.READING_ROOM);
                    taskCommand.completeTask(isbn, cardId, DbTask.Status.TAKEN);
                    
                    response.sendRedirect(request.getHeader("referer"));
                    break;
                }
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
