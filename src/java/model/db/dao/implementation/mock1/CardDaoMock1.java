/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.dao.implementation.mock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.db.dao.CardDao;
import model.db.dao.UserDao;
import model.db.dao.implementation.jdbc.UserDaoJdbc;
import model.db.entities.Card;
import model.db.entities.User;

/**
 *
 * @author koxa
 */
public class CardDaoMock1 implements CardDao{

    private static List<Card> allCards;
    
    static {
        allCards = new ArrayList<>();
        //UserDao userDao = new UserDaoMock();
        UserDao userDao = new UserDaoJdbc();
        int number = 0;
        allCards.add(new Card(number++, userDao.getUserByNickname("josh_1")));
        allCards.add(new Card(number++, userDao.getUserByNickname("mike_2")));
        allCards.add(new Card(number++, userDao.getUserByNickname("bill_3")));
    }
    
    @Override
    public void insert(Card card) {
        card.setId(allCards.size() - 1);
        allCards.add(card);
    }

    @Override
    public Card getByUser(User user) {
        
        Iterator<Card> cardsIterator = allCards.iterator();
		while(cardsIterator.hasNext()) {
			Card card = cardsIterator.next();
			if ( card.getUser().getLogin().equals(user.getLogin())) {
                return card;
            }
		}
        return null;
    }

    @Override
    public Card getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}