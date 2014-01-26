/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.dao.implementation.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.db.common.DbTask;
import model.db.connection.exception.ConnectionPoolLibraryException;
import model.db.connection.pool.ConnectionFactory;
import model.db.connection.pool.ConnectionProxy;
import model.db.dao.BookDao;
import model.db.dao.CardDao;
import model.db.dao.ReadingRoomDao;
import model.db.entities.ReadingRoom;

/**
 *
 * @author koxa
 */
public class ReadingRoomDaoJdbc implements ReadingRoomDao{
    
    private static final String GET_BY_STATUS = "SELECT * FROM reading_room WHERE status = ?";
    private static final String GET_BY_ISBN_CARD_STATUS = "SELECT * FROM reading_room WHERE isbn = ? AND card_id = ? AND status = ?";
    private static final String INSERT_READING_ROOM = "INSERT INTO reading_room VALUES (?, ?, ?, ?)";
    private static final String CHANGE_READING_ROOM = "UPDATE reading_room SET status = ? WHERE isbn = ? AND card_id = ? AND status = ?";
    
    @Override
    public List<ReadingRoom> getByStatus(ReadingRoom.Status status) {
        
        List<ReadingRoom> readingRooms = new ArrayList();
        try (ConnectionProxy dbConnection = ConnectionFactory.getConnection()) {
            
            PreparedStatement preparedStatement = dbConnection.prepareStatement(GET_BY_STATUS);
            preparedStatement.setString(1, status.toString());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int isbn = rs.getInt("isbn");
                int cardId = rs.getInt("card_id");
                Calendar date = Calendar.getInstance();
                date.setTime(rs.getDate("date"));
                
                BookDao bookDao = new BookDaoJdbc();
                CardDao cardDao = new CardDaoJdbc();
                ReadingRoom readingRoom = new ReadingRoom(bookDao.getBook(isbn), cardDao.getById(cardId), date, status);
                readingRoom.setStatus(status);
                
                readingRooms.add(readingRoom);
            }
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return readingRooms;
    } 

    @Override
    public void insert(ReadingRoom readingRoom) {
        try (ConnectionProxy dbConnection = ConnectionFactory.getConnection()) {
            
            PreparedStatement preparedStatement = dbConnection.prepareStatement(INSERT_READING_ROOM);
            preparedStatement.setInt(1, readingRoom.getBook().getIsbn());
            preparedStatement.setInt(2, readingRoom.getCard().getId());
            java.sql.Date dateSql = new java.sql.Date(readingRoom.getDate().getTime().getYear(), 
                    readingRoom.getDate().getTime().getMonth(), readingRoom.getDate().getTime().getDay());
            preparedStatement.setDate(3, dateSql);
            preparedStatement.setString(4, readingRoom.getStatus().toString());
            preparedStatement.executeUpdate();
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void manage(ReadingRoom readingRoom) {
        ReadingRoom.Status requiredStatus = ReadingRoom.Status.TAKEN;
        ReadingRoom.Status previousStatus = ReadingRoom.Status.ORDERED;
        
        changeStatus(readingRoom, requiredStatus, previousStatus);
    }

    @Override
    public void complete(ReadingRoom readingRoom) {
        ReadingRoom.Status requiredStatus = ReadingRoom.Status.RETURNED;
        ReadingRoom.Status previousStatus = ReadingRoom.Status.TAKEN;
        
        changeStatus(readingRoom, requiredStatus, previousStatus);
    }
    
    private void changeStatus(ReadingRoom readingRoom, ReadingRoom.Status requiredStatus, ReadingRoom.Status previousStatus) {
        try (ConnectionProxy dbConnection = ConnectionFactory.getConnection()) {
            
            PreparedStatement preparedStatement = dbConnection.prepareStatement(CHANGE_READING_ROOM);
            preparedStatement.setString(1, requiredStatus.toString());
            preparedStatement.setInt(2, readingRoom.getBook().getIsbn());
            preparedStatement.setInt(3, readingRoom.getCard().getId());
            preparedStatement.setString(4, previousStatus.toString());
            preparedStatement.executeUpdate();
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ReadingRoom getByIsbnCardStatus(int isbn, int cardId, ReadingRoom.Status status) {
        ReadingRoom readingRoom = null;
        try (ConnectionProxy dbConnection = ConnectionFactory.getConnection()) {
            
            PreparedStatement preparedStatement = dbConnection.prepareStatement(GET_BY_ISBN_CARD_STATUS);
            preparedStatement.setInt(1, isbn);
            preparedStatement.setInt(2, cardId);
            preparedStatement.setString(3, status.toString());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Calendar date = Calendar.getInstance();
                date.setTime(rs.getDate("date"));
                BookDao bookDao = new BookDaoJdbc();
                CardDao cardDao = new CardDaoJdbc();
                readingRoom = new ReadingRoom(bookDao.getBook(isbn), cardDao.getById(cardId), date, status);
                break;
            }
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        } catch (ConnectionPoolLibraryException ex) {
            Logger.getLogger(UserDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return readingRoom;
    }
}