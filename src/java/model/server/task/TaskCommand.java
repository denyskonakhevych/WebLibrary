package model.server.task;

import java.util.List;
import model.db.common.DbTask;

/**
 *
 * @author koxa
 */
public interface TaskCommand {
    
    public static enum Type {
        LOAN,
        READING_ROOM
    }
    
    List<? extends DbTask> getByStatus( DbTask.Status status);
    
    void manageTask(int isbn, int cardId, DbTask.Status status);
    
    void completeTask(int isbn, int cardId, DbTask.Status status);
}
