package model.server.task;

import inject.Inject;
import inject.clazz.ApplicationContextClass;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.db.common.DbTask;
import model.db.dao.ReadingRoomDao;
import model.db.entities.ReadingRoom;

/**
 *
 * @author koxa
 */
public class ReadingRoomTaskCommand extends ApplicationContextClass implements TaskCommand {

    @Inject("ReadingRoomDao")
    ReadingRoomDao readingRoomDao;
    
    @Override
    public List<? extends DbTask>/*void*/ getByStatus(/*HttpServletRequest request, */DbTask.Status status) {
        List<ReadingRoom> orderedReadingRooms = readingRoomDao.getByStatus(status);
        //request.setAttribute("ordered_loans", orderedReadingRooms);
        return orderedReadingRooms;
    }

    @Override
    public void manageTask(int isbn, int cardId, DbTask.Status status) {        
        ReadingRoom readingRoom = readingRoomDao.getByIsbnCardStatus(isbn, cardId, status);
        readingRoomDao.manage(readingRoom);
    }

    @Override
    public void completeTask(int isbn, int cardId, DbTask.Status status) {
        ReadingRoom readingRoom = readingRoomDao.getByIsbnCardStatus(isbn, cardId, status);
        readingRoomDao.complete(readingRoom);
    }
}