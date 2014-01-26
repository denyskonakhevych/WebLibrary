/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.dao.implementation.mock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.db.dao.BookCatalogueItemDao;
import model.db.entities.Book;
import model.db.entities.BookCatalogueItem;

/**
 *
 * @author koxa
 */
public class BookCatalogueItemDaoMock1 implements BookCatalogueItemDao{

    List<BookCatalogueItem> allBooks;
    
    {
        getAllBooks();
    }
    
    @Override
    public List<BookCatalogueItem> getAllBooks() {
        List<BookCatalogueItem> newBooks = new ArrayList<>();
        int number = 0;     
        
        newBooks.add( new BookCatalogueItem(number++, "NewBook1", 0, "NewBook1Description", "Author1", 10, 5));
        newBooks.add( new BookCatalogueItem(number++, "NewBook2", 1, "NewBook2Description", "Author2", 20, 10));
        newBooks.add( new BookCatalogueItem(number++, "NewBook3", 2, "NewBook3Description", "Author3", 30, 15));
        newBooks.add( new BookCatalogueItem(number++, "NewBook4", 0, "NewBook4Description", "Author1", 10, 5));
        newBooks.add( new BookCatalogueItem(number++, "NewBook5", 1, "NewBook5Description", "Author5", 20, 10));
        newBooks.add( new BookCatalogueItem(number++, "NewBook6", 2, "NewBook6Description", "Author3", 30, 15));
        newBooks.add( new BookCatalogueItem(number++, "NewBook7", 0, "NewBook7Description", "Author4", 10, 5));
        newBooks.add( new BookCatalogueItem(number++, "NewBook8", 1, "NewBook8Description", "Author2", 20, 10));
        newBooks.add( new BookCatalogueItem(number++, "NewBook9", 1, "NewBook9Description", "Author3", 30, 15));
        
        allBooks = newBooks;
        return newBooks;
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
    public BookCatalogueItem getBook(int isbn) {
        //Iterator<BookCatalogueItem> usersIterator = getAllBooks().iterator();
        Iterator<BookCatalogueItem> usersIterator = allBooks.iterator();
		while(usersIterator.hasNext()) {
			BookCatalogueItem book = usersIterator.next();
			if ( book.getBook().getIsbn() == isbn) {
                return book;
            }
		}
        return null;
    }

    @Override
    public List<BookCatalogueItem> getBooksByGanreId(Integer ganreId) {
        List<BookCatalogueItem> booksByGanre = new ArrayList<>();
        
        //Iterator<BookCatalogueItem> usersIterator = getAllBooks().iterator();
        Iterator<BookCatalogueItem> usersIterator = allBooks.iterator();
		while(usersIterator.hasNext()) {
			BookCatalogueItem book = usersIterator.next();
			if ( book.getBook().getGanre().equals(ganreId)) {
                booksByGanre.add(book);
            }
		}
        return booksByGanre;
    }

    @Override
    public void decreaseItemCount(int isbn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void increaseItemCount(int isbn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}