package model.db.entities;

import java.io.Serializable;

/**
 *
 * @author koxa
 */
public class Book implements Serializable {
    
    private final int isbn;
    private String title;
    private Integer ganre;
    private String description;
    private String author;
    
    public Book(int isbn, String title, Integer ganre, String description, String author) {
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

    public Integer getGanre() {
        return ganre;
    }

    public void setGanre(Integer ganre) {
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
