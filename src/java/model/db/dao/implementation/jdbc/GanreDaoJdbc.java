/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.dao.implementation.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.db.connection.exception.ConnectionPoolLibraryException;
import model.db.connection.pool.ConnectionFactory;
import model.db.connection.pool.ConnectionProxy;
import model.db.dao.GanreDao;
import model.db.entities.Ganre;

/**
 *
 * @author koxa
 */
public class GanreDaoJdbc implements GanreDao{

    private static final String GET_ALL_GANRES = "SELECT * FROM ganre";
    
    @Override
    public List<Ganre> getAllGanres() {
        List<Ganre> ganres = new ArrayList();
        try (ConnectionProxy myConnection = ConnectionFactory.getConnection();
                Statement stmt = myConnection.createStatement()) {
            
            ResultSet rs = stmt.executeQuery(GET_ALL_GANRES);
            while(rs.next()){
                Ganre ganre = new Ganre(rs.getInt("id"), rs.getString("name"));                
                ganres.add(ganre);
            }
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ganres;
    }

    @Override
    public Ganre getGanreByName(String ganreName) {
        if( ganreName == null) {
            return null;
        }
        Ganre ganre = null;
        try (ConnectionProxy myConnection = ConnectionFactory.getConnection();
                Statement stmt = myConnection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM ganre WHERE name = '" + ganreName + "'");
            while(rs.next()){
                ganre = new Ganre(rs.getInt("id"), rs.getString("name"));
            }
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ganre;
    }

    @Override
    public Ganre getGanreById(Integer ganreId) {
        if( ganreId == null) {
            return null;
        }
        Ganre ganre = null;
        try (ConnectionProxy myConnection = ConnectionFactory.getConnection();
                Statement stmt = myConnection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM ganre WHERE id = '" + ganreId.intValue() + "'");
            while(rs.next()){
                ganre = new Ganre(ganreId.intValue(), rs.getString("name"));
            }
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ganre;
    }
    
}
