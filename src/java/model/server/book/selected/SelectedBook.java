/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.server.book.selected;

import model.db.entities.Ganre;

/**
 *
 * @author koxa
 */
public class SelectedBook {
    
    private final int isbn;
    private String title;
    private Ganre ganre;
    private String description;
    private String author;

    public SelectedBook(int isbn, String title, Ganre ganre, String description, String author) {
        this.isbn = isbn;
        this.title = title;
        this.ganre = ganre;
        this.description = description;
        this.author = author;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Ganre getGanre() {
        return ganre;
    }

    public void setGanre(Ganre ganre) {
        this.ganre = ganre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
