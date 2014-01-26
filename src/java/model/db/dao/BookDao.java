/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.dao;

import java.util.List;
import model.db.entities.Book;

/**
 *
 * @author koxa
 */
public interface BookDao {
    
    List<Book> getAllBooks();
    Book getBook(int isbn);
    void insert(Book book);
    void update(Book book);
    void delete(Book book);
}
