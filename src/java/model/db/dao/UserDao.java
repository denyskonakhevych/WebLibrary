/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.dao;

import java.util.List;
import model.db.entities.User;

/**
 *
 * @author koxa
 */
public interface UserDao {
    
    public void insert(User user);
    
    public User getUserByNickname(String nickname);
    
    public boolean checkUser(String nickname, String password);
    
    List<User> getAllUsers();
    
}
