package model.db.entities;

import java.util.Calendar;
import model.db.common.DbTask;

/**
 *
 * @author koxa
 */
public class ReadingRoom extends DbTask{
    
    private Book book;
    private Card card;
    Calendar date;
    Status status;

    public ReadingRoom() {
    }

    public ReadingRoom(Book book, Card card, Calendar date, Status status) {
        this.book = book;
        this.card = card;
        this.date = date;
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

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}