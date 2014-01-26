package model.db.dao;

import java.util.List;
import model.db.common.DbTask;
import model.db.entities.Loan;

/**
 *
 * @author koxa
 */
public interface LoanDao {
    
    List<Loan> getByStatus(Loan.Status status);
    
    Loan getByIsbnCardStatus(int isbn, int cardId, DbTask.Status status/*Loan.Status status*/);
    
    void insert(Loan loan);
    
    void manage(Loan loan);
    
    void complete(Loan loan);
}
