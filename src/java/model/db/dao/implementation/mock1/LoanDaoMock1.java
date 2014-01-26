/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.dao.implementation.mock;

import java.util.ArrayList;
import java.util.List;
import model.db.dao.LoanDao;
import model.db.entities.Loan;

/**
 *
 * @author koxa
 */
public class LoanDaoMock1 implements LoanDao{
    
    private static List<Loan> allLoans;
    
    static {
        allLoans = new ArrayList<>();
    }

    @Override
    public void insert(Loan loan) {
        allLoans.add(loan);
    }

    @Override
    public void complete(Loan loan) {
        loan.setStatus(Loan.Status.RETURNED);
    }

    @Override
    public void manage(Loan loan) {
        loan.setStatus(Loan.Status.TAKEN);
    }

    @Override
    public List<Loan> getByStatus(Loan.Status status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Loan getByIsbnCardStatus(int isbn, int cardId, Loan.Status status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}