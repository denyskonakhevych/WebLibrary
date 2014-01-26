/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.server.book.selected;

import inject.Inject;
import inject.clazz.ApplicationContextClass;
import javax.servlet.http.HttpServletRequest;
import model.db.dao.BookDao;
import model.db.dao.GanreDao;
import model.db.entities.Book;
import model.db.entities.Ganre;

/**
 *
 * @author koxa
 */
public class SelectedBookAdapter extends ApplicationContextClass{
    
    private static final String ISBN_PARAMETER_NAME = "isbn";
    private static final String SELECTED_BOOK_ATTRIBUTE_NAME = "selectedBook";
    
    @Inject("BookDao")
    BookDao bookDao;
    @Inject("GanreDao")
    GanreDao ganreDao;
    
    public void setSelectedBook(HttpServletRequest request) {
        
        Integer isbn = Integer.parseInt(request.getParameter(ISBN_PARAMETER_NAME)); // todo: if not number
        
        Book book = bookDao.getBook(isbn);
        //GanreDao ganreDao = new GanreDaoJdbc();
        Ganre ganre = ganreDao.getGanreById(book.getGanre());
        SelectedBook selectedBook = new SelectedBook(book.getIsbn(), 
                book.getTitle(), ganre, book.getDescription(), book.getAuthor());
        
        request.setAttribute(SELECTED_BOOK_ATTRIBUTE_NAME, selectedBook);
    }
}
