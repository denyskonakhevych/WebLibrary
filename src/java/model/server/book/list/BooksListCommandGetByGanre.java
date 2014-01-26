/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class BooksListCommandGetByGanre extends ApplicationContextClass implements BooksListCommand{

    private static final String BOOKS_ATTRIBUTE_NAME = "books";
    
    private Integer ganreId;
    
    @Inject("BookCatalogueItemDao")
    BookCatalogueItemDao allBooksDao;

    public BooksListCommandGetByGanre(Integer ganreId) {
        this.ganreId = ganreId;
    }
    
    @Override
    public void execute(HttpServletRequest request) {
        List<BookCatalogueItem> allBooks = allBooksDao.getBooksByGanreId(ganreId);
        request.setAttribute(BOOKS_ATTRIBUTE_NAME, allBooks);
    }
}