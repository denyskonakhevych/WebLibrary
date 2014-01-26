package model.server.task;

/**
 *
 * @author koxa
 */
public class TaskCommandFactory {
    
    public TaskCommand getCommand(TaskCommand.Type type) {
        if (type.equals(TaskCommand.Type.LOAN)) {
            return new LoanTaskCommand();
        } else if (type.equals(TaskCommand.Type.READING_ROOM)) {
            return new ReadingRoomTaskCommand();
        } else {
            return null;
        }
    }
}
