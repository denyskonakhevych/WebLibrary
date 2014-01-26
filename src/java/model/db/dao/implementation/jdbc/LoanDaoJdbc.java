/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.dao.implementation.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.db.common.DbTask;
import model.db.connection.exception.ConnectionPoolLibraryException;
import model.db.connection.pool.ConnectionFactory;
import model.db.connection.pool.ConnectionProxy;
import model.db.dao.BookDao;
import model.db.dao.CardDao;
import model.db.dao.LoanDao;
import model.db.entities.Loan;
import model.db.entities.ReadingRoom;

/**
 *
 * @author koxa
 */
public class LoanDaoJdbc implements LoanDao{
    
    private static final String GET_BY_STATUS = "SELECT * FROM loan WHERE status = ?";
    private static final String GET_BY_ISBN_CARD_STATUS = "SELECT * FROM loan WHERE isbn = ? AND card_id = ? AND status = ?";
    private static final String INSERT_LOAN = "INSERT INTO loan VALUES (?, ?, ?, ?, ?)";
    private static final String CHANGE_LOAN_STATUS = "UPDATE loan SET status = ? WHERE isbn = ? AND card_id = ? AND status = ?";
    
    @Override
    public List<Loan> getByStatus(Loan.Status status) {
        
        List<Loan> loans = new ArrayList();
        try (ConnectionProxy dbConnection = ConnectionFactory.getConnection()) {
            
            PreparedStatement preparedStatement = dbConnection.prepareStatement(GET_BY_STATUS);
            preparedStatement.setString(1, status.toString());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int isbn = rs.getInt("isbn");
                int cardId = rs.getInt("card_id");
                Calendar loanDate = Calendar.getInstance();
                loanDate.setTime(rs.getDate("loan_date"));
                Calendar returnDate = Calendar.getInstance();
                returnDate.setTime(rs.getDate("return_date"));
                
                BookDao bookDao = new BookDaoJdbc();
                CardDao cardDao = new CardDaoJdbc();
                Loan loan = new Loan(bookDao.getBook(isbn), cardDao.getById(cardId), loanDate, returnDate, status);
                loan.setStatus(status);
                
                loans.add(loan);
            }
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loans;
    } 

    @Override
    public void insert(Loan loan) {
        try (ConnectionProxy dbConnection = ConnectionFactory.getConnection()) {
            
            PreparedStatement preparedStatement = dbConnection.prepareStatement(INSERT_LOAN);
            preparedStatement.setInt(1, loan.getBook().getIsbn());
            preparedStatement.setInt(2, loan.getCard().getId());
            java.sql.Date loanDateSql = new java.sql.Date(loan.getLoanDate().getTime().getYear(), 
                    loan.getLoanDate().getTime().getMonth(), loan.getLoanDate().getTime().getDay());
            java.sql.Date reuturnDateSql = new java.sql.Date(loan.getReturnDate().getTime().getYear(), 
                    loan.getReturnDate().getTime().getMonth(), loan.getReturnDate().getTime().getDay());
            
            preparedStatement.setDate(3, loanDateSql);
            preparedStatement.setDate(4, reuturnDateSql);
            preparedStatement.setString(5, loan.getStatus().toString());
            preparedStatement.execute();
        } catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void manage(Loan loan) {
        
        Loan.Status requiredStatus = Loan.Status.TAKEN;
        Loan.Status previousStatus = Loan.Status.ORDERED;
        changeStatus(loan, requiredStatus, previousStatus);
    }

    @Override
    public void complete(Loan loan) {
        Loan.Status requiredStatus = Loan.Status.RETURNED;
        Loan.Status previousStatus = Loan.Status.TAKEN;
        changeStatus(loan, requiredStatus, previousStatus);
    }
    
    private void changeStatus(Loan loan, Loan.Status requiredStatus, Loan.Status previousStatus) {
        try (ConnectionProxy dbConnection = ConnectionFactory.getConnection()) {
            
            PreparedStatement preparedStatement = dbConnection.prepareStatement(CHANGE_LOAN_STATUS);
            preparedStatement.setString(1, requiredStatus.toString());
            preparedStatement.setInt(2, loan.getBook().getIsbn());
            preparedStatement.setInt(3, loan.getCard().getId());
            preparedStatement.setString(4, previousStatus.toString());
            
            preparedStatement.executeUpdate();
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Loan getByIsbnCardStatus(int isbn, int cardId, Loan.Status status) {
        
        Loan loan = null;
        try (ConnectionProxy dbConnection = ConnectionFactory.getConnection()) {
            
            PreparedStatement preparedStatement = dbConnection.prepareStatement(GET_BY_ISBN_CARD_STATUS);
            preparedStatement.setInt(1, isbn);
            preparedStatement.setInt(2, cardId);
            preparedStatement.setString(3, status.toString());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Calendar loanDate = Calendar.getInstance();
                loanDate.setTime(rs.getDate("loan_date"));
                Calendar returnDate = Calendar.getInstance();
                returnDate.setTime(rs.getDate("return_date"));
                
                BookDao bookDao = new BookDaoJdbc();
                CardDao cardDao = new CardDaoJdbc();
                loan = new Loan(bookDao.getBook(isbn), cardDao.getById(cardId), loanDate, returnDate, status);
                break;
            }
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loan;
    }
}