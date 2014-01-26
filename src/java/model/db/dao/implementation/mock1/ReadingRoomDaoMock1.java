/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.dao.implementation.mock;

import java.util.ArrayList;
import java.util.List;
import model.db.dao.ReadingRoomDao;
import model.db.entities.ReadingRoom;

/**
 *
 * @author koxa
 */
public class ReadingRoomDaoMock1 implements ReadingRoomDao{
    
    private static List<ReadingRoom> allReadingRooms;
    
    static {
        allReadingRooms = new ArrayList<>();
    }
    
    @Override
    public void insert(ReadingRoom readingRoom) {
        allReadingRooms.add(readingRoom);
    }

    @Override
    public void manage(ReadingRoom readingRoom) {
        readingRoom.setStatus(ReadingRoom.Status.TAKEN);
    }

    @Override
    public void complete(ReadingRoom readingRoom) {
        readingRoom.setStatus(ReadingRoom.Status.RETURNED);
    }

    @Override
    public List<ReadingRoom> getByStatus(ReadingRoom.Status status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReadingRoom getByIsbnCardStatus(int isbn, int cardId, ReadingRoom.Status status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}