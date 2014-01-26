/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.dao.implementation.mock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.db.dao.BookDao;
import model.db.entities.Book;

/**
 *
 * @author koxa
 */
public class BookDaoMock1 implements BookDao{

    List<Book> allBooks;
    
    {
        getAllBooks();
    }
    
    @Override
    public List<Book> getAllBooks() {
        
        List<Book> newBooks = new ArrayList<>();
        int number = 0;     
        
        newBooks.add(new Book(number++, "NewBook1", 0, "NewBook1Description", "Author1"));
        newBooks.add(new Book(number++, "NewBook2", 1, "NewBook2Description", "Author2"));
        newBooks.add(new Book(number++, "NewBook3", 2, "NewBook3Description", "Author3"));
        newBooks.add( new Book(number++, "NewBook4", 0, "NewBook4Description", "Author1"));
        newBooks.add( new Book(number++, "NewBook5", 1, "NewBook5Description", "Author5"));
        newBooks.add( new Book(number++, "NewBook6", 2, "NewBook6Description", "Author3"));
        newBooks.add( new Book(number++, "NewBook7", 0, "NewBook7Description", "Author4"));
        newBooks.add( new Book(number++, "NewBook8", 1, "NewBook8Description", "Author2"));
        newBooks.add( new Book(number++, "NewBook9", 1, "NewBook9Description", "Author3"));
        
        /*
        AtomicReference<List<Book>> books = new AtomicReference<>();
        books.set(allBooks);
        
        while(!books.compareAndSet(allBooks, newBooks)){
            Thread.yield();
        }
        */
        allBooks = newBooks;
        return newBooks;
    }
    
    @Override
    public Book getBook(int isbn) {
        //Iterator<Book> usersIterator = getAllBooks().iterator();
        Iterator<Book> usersIterator = allBooks.iterator();
		while(usersIterator.hasNext()) {
			Book book = usersIterator.next();
			if ( book.getIsbn() == isbn) {
                return book;
            }
		}
        return null;
    }

    @Override
    public void insert(Book b) {
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

    
    
}
