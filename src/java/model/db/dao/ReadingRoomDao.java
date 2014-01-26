/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.dao;

import java.util.List;
import model.db.common.DbTask;
import model.db.entities.ReadingRoom;

/**
 *
 * @author koxa
 */
public interface ReadingRoomDao {
    
    List<ReadingRoom> getByStatus(ReadingRoom.Status status);
    
    ReadingRoom getByIsbnCardStatus(int isbn, int cardId, ReadingRoom.Status status);
    
    void insert(ReadingRoom readingRoom);
    
    void manage(ReadingRoom readingRoom);
    
    void complete(ReadingRoom readingRoom);
}
