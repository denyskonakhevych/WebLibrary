/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.dao;

import java.util.List;
import model.db.entities.Book;
import model.db.entities.BookCatalogueItem;

/**
 *
 * @author koxa
 */
public interface BookCatalogueItemDao {
    
    List<BookCatalogueItem> getAllBooks();
    
    BookCatalogueItem getBook(int isbn);
    
    List<BookCatalogueItem> getBooksByGanreId(Integer ganreId);
    
    void decreaseItemCount(int isbn);
    
    void increaseItemCount(int isbn);
    
    int insert(Book b);
    
    void update(Book b);
    
    void delete(Book b);
}
