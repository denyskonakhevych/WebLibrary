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
import model.db.dao.BookDao;
import model.db.entities.Book;

/**
 *
 * @author koxa
 */
public class BookDaoJdbc implements BookDao{

    private static final String SELECT_ALL_BOOKS = "SELECT * from book";
    private static final String INSERT_BOOK = "INSERT INTO book VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList();
        try (ConnectionProxy myConnection = ConnectionFactory.getConnection();
                Statement stmt = myConnection.createStatement()) {
            
            ResultSet rs = stmt.executeQuery(SELECT_ALL_BOOKS);
            while(rs.next()){
                int isbn = rs.getInt("isbn");
                String title = rs.getString("title");
                Integer ganre = rs.getInt("ganre");
                String description = rs.getString("description");
                String author = rs.getString("author");
    
                Book book = new Book(isbn, title, ganre, description, author);
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
    public Book getBook(int isbn) {
        Book book = null;
        try (ConnectionProxy myConnection = ConnectionFactory.getConnection();
                Statement stmt = myConnection.createStatement()) {
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM book WHERE isbn = '" + isbn + "'");
            while(rs.next()){
                String title = rs.getString("title");
                Integer ganre = rs.getInt("ganre");
                String description = rs.getString("description");
                String author = rs.getString("author");
                book = new Book(isbn, title, ganre, description, author);
            }
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return book;
    }

    @Override
    public void insert(Book book) {
        try (ConnectionProxy dbConnection = ConnectionFactory.getConnection()) {
            
            PreparedStatement preparedStatement = dbConnection.prepareStatement(INSERT_BOOK);
            preparedStatement.setInt(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getGanre());
            preparedStatement.setString(4, book.getDescription());
            preparedStatement.setString(5, book.getAuthor());
            preparedStatement.executeUpdate();
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Book b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Book b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}