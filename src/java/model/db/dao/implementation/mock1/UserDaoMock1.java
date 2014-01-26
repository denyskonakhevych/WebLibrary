/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.dao.implementation.mock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.db.dao.UserDao;
import model.db.entities.User;
import model.db.entities.User.Role;

/**
 *
 * @author koxa
 */
public class UserDaoMock1 implements UserDao{
    
    private static List<User> allUsers;
    
    static {
        allUsers = new ArrayList<>();
        int number = 0;
        allUsers.add(new User(number++, "josh_1", "pass1", Role.USER, "Josh", "Bush", "Address", "josh.bush@aol.com"));
        allUsers.add(new User(number++, "mike_2", "pass2", Role.USER, "Mike", "Tison", "Address", "mike.tison@aol.com"));
        allUsers.add(new User(number++, "bill_3", "pass3", Role.MODERATOR, "Bill", "Gates", "Address", "bill.gates@aol.com"));
    }
    
    @Override
    public void insert(User user) {
        user.setId(allUsers.size());
        //System.out.println("Try to add: " + user);
        allUsers.add(user);
        //System.out.println("Last user: " + allUsers.get(allUsers.size()-1));
    }

    @Override
    public List<User> getAllUsers() {
        return allUsers;
    }
    
    @Override
    public User getUserByNickname(String nickname) {
        Iterator<User> usersIterator = allUsers.iterator();
		while(usersIterator.hasNext()) {
			User user = usersIterator.next();
			if (user.getLogin().equals(nickname)) {
                return user;
            }
		}
        return null;
    }    
    
    @Override
    public boolean checkUser(String nickname, String password) {
        //Iterator<User> usersIterator = getAllUsers().iterator();
        Iterator<User> usersIterator = allUsers.iterator();
		while(usersIterator.hasNext()) {
			User user = usersIterator.next();
			if (user.getLogin().equals(nickname) && user.getPassword().equals(password)) {
                return true;
            }
		}
        return false;
    }
}