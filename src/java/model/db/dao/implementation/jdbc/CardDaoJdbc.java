/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.dao.implementation.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.db.connection.exception.ConnectionPoolLibraryException;
import model.db.connection.pool.ConnectionFactory;
import model.db.connection.pool.ConnectionProxy;
import model.db.dao.CardDao;
import model.db.dao.UserDao;
import model.db.entities.Card;
import model.db.entities.User;

/**
 *
 * @author koxa
 */
public class CardDaoJdbc implements CardDao{

    private static final String INSERT_CARD = "INSERT INTO card (user) VALUES (?)";
    
    @Override
    public void insert(Card card) {
        try (ConnectionProxy dbConnection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(INSERT_CARD);
            preparedStatement.setString(1, card.getUser().getLogin());
            preparedStatement.executeUpdate();
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Card getByUser(User user) {
        Card card = null;
        try (ConnectionProxy myConnection = ConnectionFactory.getConnection();
                Statement stmt = myConnection.createStatement()) {
            
            ResultSet rs = stmt.executeQuery("SELECT * from card WHERE user = '" + user.getLogin() + "'");
            while(rs.next()){                
                card = new Card(rs.getInt("id"), user);
            }
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return card;
    }

    @Override
    public Card getById(int id) {
        Card card = null;
        try (ConnectionProxy myConnection = ConnectionFactory.getConnection();
                Statement stmt = myConnection.createStatement()) {
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM card WHERE id = " + id);
            while(rs.next()){
                UserDao userDao = new UserDaoJdbc();
                card = new Card(id, userDao.getUserByNickname(rs.getString("user")));
            }
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return card;
    }
}