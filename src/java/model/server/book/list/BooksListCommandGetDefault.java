package model.server.book.list;

import inject.Inject;
import inject.clazz.ApplicationContextClass;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.db.dao.BookCatalogueItemDao;
import model.db.entities.BookCatalogueItem;

/**
 *
 * @author koxa
 */
public class BooksListCommandGetDefault extends ApplicationContextClass implements BooksListCommand{
    private static final String BOOKS_ATTRIBUTE_NAME = "books";
    
    @Inject("BookCatalogueItemDao")
    BookCatalogueItemDao allBooksDao;
    
    @Override
    public void execute(HttpServletRequest request) {
        List<BookCatalogueItem> allBooks = allBooksDao.getAllBooks();
        request.setAttribute(BOOKS_ATTRIBUTE_NAME, allBooks);
    }
}