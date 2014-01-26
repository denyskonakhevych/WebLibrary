package model.db.dao;

import model.db.entities.Card;
import model.db.entities.User;

/**
 *
 * @author koxa
 */
public interface CardDao {
    
    void insert(Card card);
    
    Card getByUser(User user);
    
    Card getById(int id);
}
