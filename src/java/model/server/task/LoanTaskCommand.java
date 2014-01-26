package model.server.task;

import inject.Inject;
import inject.clazz.ApplicationContextClass;
import java.util.List;
import model.db.common.DbTask;
import model.db.dao.LoanDao;
import model.db.entities.Loan;

/**
 *
 * @author koxa
 */
public class LoanTaskCommand extends ApplicationContextClass implements TaskCommand {
    
    @Inject("LoanDao")
    LoanDao loanDao;
    
    @Override
    public List<? extends DbTask> getByStatus(DbTask.Status status) {
        List<Loan> orderedLoans = loanDao.getByStatus(status);
        
        return orderedLoans;
    }
    
    @Override
    public void manageTask(int isbn, int cardId, DbTask.Status status) {
        Loan loan = loanDao.getByIsbnCardStatus(isbn, cardId, status);
        loanDao.manage(loan);
    }

    @Override
    public void completeTask(int isbn, int cardId, DbTask.Status status) {
        Loan loan = loanDao.getByIsbnCardStatus(isbn, cardId, status);
        loanDao.complete(loan);
    }
}