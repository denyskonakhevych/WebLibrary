/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.entities;

/**
 *
 * @author koxa
 */
public class BookCatalogueItem {
    
    private Book book;
    private int totalCount;
    private int avaliable;
    
    public BookCatalogueItem(int isbn, String title, Integer ganre, String description, String author) {
        this(isbn, title, ganre, description, author, 0, 0);
    }
    
    public BookCatalogueItem(int isbn, String title, Integer ganre, String description, String author, int totalCount, int avaliable) {
        this.book = new Book(isbn, title, ganre, description, author);
        this.totalCount = totalCount;
        this.avaliable = avaliable;
    }
    
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    
    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int total_count) {
        this.totalCount = total_count;
    }

    public int getAvaliable() {
        return avaliable;
    }

    public void setAvaliable(int avaliable) {
        this.avaliable = avaliable;
    }
}
