package model.server.cart;

import inject.Inject;
import inject.clazz.ApplicationContextClass;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.db.dao.BookDao;
import model.db.entities.Book;

/**
 *
 * @author koxa
 */
public class ShoppingCartCommandRemove extends ApplicationContextClass implements ShoppingCartCommand{

    private static final String SHOPPING_CART_ATTRIBUTE_NAME = "shoppingCart";
    private static final String ISBN_PARAMETER_NAME = "isbn";
    
    @Inject("BookDao")
    BookDao bookDao;
    
    @Override
    public void execute(HttpServletRequest request) {
        
        //BookDao bookDao = new BookDaoJdbc();
        
        HttpSession session = request.getSession(true);
        Integer isbn = Integer.parseInt(request.getParameter(ISBN_PARAMETER_NAME)); // todo: if not number
        
        ShoppingCart oldShoppingCart;
        ShoppingCart newShoppingCart;
        
        AtomicReference<ShoppingCart> shoppingCartHolder = (AtomicReference<ShoppingCart>) session.getAttribute(SHOPPING_CART_ATTRIBUTE_NAME);
        Book book = bookDao.getBook(isbn);
        do {
            oldShoppingCart = shoppingCartHolder.get();
            if (oldShoppingCart == null) {
                newShoppingCart = new ShoppingCart(new ArrayList());
            } else {
                newShoppingCart = new ShoppingCart(oldShoppingCart.getBooks());
            }
            newShoppingCart.removeBook(book);
        } while (!shoppingCartHolder.compareAndSet(oldShoppingCart, newShoppingCart));
    }
}