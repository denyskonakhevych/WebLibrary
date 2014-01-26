/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.dao.implementation.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.db.connection.exception.ConnectionPoolLibraryException;
import model.db.connection.pool.ConnectionFactory;
import model.db.connection.pool.ConnectionProxy;
import model.db.dao.BookCatalogueItemDao;
import model.db.entities.Book;
import model.db.entities.BookCatalogueItem;

/**
 *
 * @author koxa
 */
public class BookCatalogueItemDaoJdbc implements BookCatalogueItemDao{

    private static final String SELECT_ALL_BOOKS = "SELECT * from book";
    private static final String GET_BOOK = "SELECT * FROM book WHERE isbn = ?";
    private static final String INCREASE_BOOK_COUNT = "UPDATE book SET avaliable = avaliable + 1 WHERE isbn = ?";
    private static final String DECREASE_BOOK_COUNT = "UPDATE book SET avaliable = avaliable - 1 WHERE isbn = ?";
    
    @Override
    public List<BookCatalogueItem> getAllBooks() {
        List<BookCatalogueItem> books = new ArrayList();
        try (ConnectionProxy myConnection = ConnectionFactory.getConnection();
                Statement stmt = myConnection.createStatement()) {
            
            ResultSet rs = stmt.executeQuery(SELECT_ALL_BOOKS);
            while(rs.next()){
                int isbn = rs.getInt("isbn");
                String title = rs.getString("title");
                Integer ganre = rs.getInt("ganre");
                String description = rs.getString("description");
                String author = rs.getString("author");
                int totalCount = rs.getInt("total_count");
                int avaliable = rs.getInt("avaliable");
    
                BookCatalogueItem book = new BookCatalogueItem(isbn, title, ganre, description, author, totalCount, avaliable);
                books.add(book);
            }
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }

    @Override
    public BookCatalogueItem getBook(int isbn) {
        BookCatalogueItem book = null;
        try (ConnectionProxy dbConnection = ConnectionFactory.getConnection()) {
            
            PreparedStatement preparedStatement = dbConnection.prepareStatement(GET_BOOK);
            //ResultSet rs = stmt.executeQuery("SELECT * FROM book WHERE isbn = '" + isbn + "'");
            preparedStatement.setInt(1, isbn);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                String title = rs.getString("title");
                Integer ganre = rs.getInt("ganre");
                String description = rs.getString("description");
                String author = rs.getString("author");
                int totalCount = rs.getInt("total_count");
                int avaliable = rs.getInt("avaliable");
    
                book = new BookCatalogueItem(isbn, title, ganre, description, author, totalCount, avaliable);
            }
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return book;
    }

    @Override
    public List<BookCatalogueItem> getBooksByGanreId(Integer ganreId) {
        
        List<BookCatalogueItem> books = new ArrayList();
        try (ConnectionProxy myConnection = ConnectionFactory.getConnection();
                Statement stmt = myConnection.createStatement()) {
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM book WHERE ganre = " + ganreId.intValue());
            while(rs.next()){
                int isbn = rs.getInt("isbn");
                String title = rs.getString("title");
                Integer ganre = rs.getInt("ganre");
                String description = rs.getString("description");
                String author = rs.getString("author");
                int totalCount = rs.getInt("total_count");
                int avaliable = rs.getInt("avaliable");
    
                BookCatalogueItem book = new BookCatalogueItem(isbn, title, ganre, description, author, totalCount, avaliable);
                books.add(book);
            }
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }

    @Override
    public int insert(Book b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Book b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Book b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void decreaseItemCount(int isbn) {
        try (ConnectionProxy dbConnection = ConnectionFactory.getConnection()) {
            
            //String userInsertQuery = "UPDATE book SET avaliable = avaliable - 1 WHERE isbn = ?";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(DECREASE_BOOK_COUNT);
            preparedStatement.setInt(1, isbn);
            
            preparedStatement.executeUpdate();
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void increaseItemCount(int isbn) {
        try (ConnectionProxy dbConnection = ConnectionFactory.getConnection()) {
            
            //String userInsertQuery = "UPDATE book SET avaliable = avaliable + 1 WHERE isbn = ?";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(INCREASE_BOOK_COUNT);
            preparedStatement.setInt(1, isbn);
            
            preparedStatement.executeUpdate();
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
