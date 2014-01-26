package model.db.entities;

import java.util.Calendar;
import model.db.common.DbTask;

/**
 *
 * @author koxa
 */
public class Loan extends DbTask{
    
    private Book book;
    private Card card;
    private Calendar loanDate;
    private Calendar returnDate;
    private Status status;

    public Loan() {
    }
    
    public Loan(Book book, Card card, Calendar loanDate, Calendar returnDate, Status status) {
        this.book = book;
        this.card = card;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Calendar getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Calendar loanDate) {
        this.loanDate = loanDate;
    }

    public Calendar getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Calendar returnDate) {
        this.returnDate = returnDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
