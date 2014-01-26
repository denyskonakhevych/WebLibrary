/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.dao.implementation.jdbc;

import model.db.connection.exception.ConnectionPoolLibraryException;
import model.db.connection.pool.ConnectionFactory;
import model.db.connection.pool.ConnectionProxy;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.db.dao.UserDao;
import model.db.entities.User;

/**
 *
 * @author koxa
 */
public class UserDaoJdbc implements UserDao{
    
    private static final String INSERT_USER = "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_USERS = "SELECT * from user";

    @Override
    public void insert(User user) {
        try (ConnectionProxy dbConnection = ConnectionFactory.getConnection();
                Statement stmt = dbConnection.createStatement()) {
            
            PreparedStatement preparedStatement = dbConnection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getSecondName());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getRole().toString());
            
            preparedStatement.executeUpdate();
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<User> getAllUsers() {
        
        List<User> users = new ArrayList();
        try (ConnectionProxy myConnection = ConnectionFactory.getConnection();
                Statement stmt = myConnection.createStatement()) {
            
            ResultSet rs = stmt.executeQuery(SELECT_ALL_USERS);
            while(rs.next()){
                User user = new User();
                user.setLogin(rs.getString("nick"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setSecondName(rs.getString("second_name"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setRole(User.Role.valueOf(rs.getString("role").toUpperCase()));
                
                users.add(user);
            }
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @Override
    public User getUserByNickname(String nickname) {
        User user = null;
        try (ConnectionProxy myConnection = ConnectionFactory.getConnection();
                Statement stmt = myConnection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * from user WHERE nick = '" + nickname + "'");
            while(rs.next()){
                user = new User();
                user.setLogin(rs.getString("nick"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setSecondName(rs.getString("second_name"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setRole(User.Role.valueOf(rs.getString("role").toUpperCase()));
            }
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public boolean checkUser(String nickname, String password) {
        
        try (ConnectionProxy myConnection = ConnectionFactory.getConnection();
                Statement stmt = myConnection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * from user WHERE nick = '" + nickname + "' AND password = '" + password + "'");
            while(rs.next()){
                return true;
            }
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}