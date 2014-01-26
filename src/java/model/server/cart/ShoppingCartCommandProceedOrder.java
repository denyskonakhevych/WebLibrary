/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.server.cart;

import inject.Inject;
import inject.clazz.ApplicationContextClass;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicReference;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.db.dao.CardDao;
import model.db.dao.LoanDao;
import model.db.dao.ReadingRoomDao;
import model.db.entities.Book;
import model.db.entities.Card;
import model.db.entities.Loan;
import model.db.entities.ReadingRoom;
import model.db.entities.User;

/**
 *
 * @author koxa
 */
public class ShoppingCartCommandProceedOrder extends ApplicationContextClass implements ShoppingCartCommand{
    
    private static final String LOAN_TYPE_PARAMETER_NAME = "loanType";
    private static final String USER_ATTRIBUTE_NAME = "user";
    private static final String LOAN_TYPE_READING_CARD = "readingCard";
    private static final String LOAN_TYPE_READING_ROOM = "readingRoom";

    @Inject("LoanDao")
    LoanDao loanDao;
    @Inject("CardDao")
    CardDao cardDao;
    @Inject("ReadingRoomDao")
    ReadingRoomDao readingRoomDao;
    
    @Override
    public void execute(HttpServletRequest request) {
        
        HttpSession session = request.getSession(true);
        String loanType = request.getParameter(LOAN_TYPE_PARAMETER_NAME);
        ShoppingCart shoppingCart;
        AtomicReference<ShoppingCart> shoppingCartHolder = (AtomicReference<ShoppingCart>) session.getAttribute("shoppingCart");
        do {
            try {
                shoppingCart = shoppingCartHolder.get();
                User user = (User) session.getAttribute(USER_ATTRIBUTE_NAME);
                
                switch (loanType) {
                    case LOAN_TYPE_READING_CARD:
                        {
                            //LoanDao loanDao = new LoanDaoJdbc();
                            //CardDao cardDao = new CardDaoJdbc();
                            Card card = cardDao.getByUser(user);
                            Calendar loanDate = Calendar.getInstance();
                            Calendar returnDate = Calendar.getInstance();
                            returnDate.add(Calendar.DATE, 7);
                            for (Book book : shoppingCart.getBooks()) {
                                Loan loan = new Loan(book, card, loanDate, returnDate, Loan.Status.ORDERED);
                                loanDao.insert(loan);
                            }
                            break;
                        }
                    case LOAN_TYPE_READING_ROOM:
                        {
                            //ReadingRoomDao readingRoomDao = new ReadingRoomDaoJdbc();
                            Card card = cardDao.getByUser(user);
                            Calendar date = Calendar.getInstance();
                            for (Book book : shoppingCart.getBooks()) {
                                ReadingRoom readingRoom = new ReadingRoom(book, card, date, ReadingRoom.Status.ORDERED);
                                readingRoomDao.insert(readingRoom);
                            }
                            break;
                        }
                }
            } catch (ClassCastException ex) {
                return;
            }
        } while (!shoppingCartHolder.compareAndSet(shoppingCart, null));
    }
    
}
