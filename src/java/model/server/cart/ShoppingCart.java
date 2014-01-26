/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.server.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.db.entities.Book;

/**
 *
 * @author koxa
 */
public class ShoppingCart implements Serializable{
    
    private List<Book> booksInCart;

    public ShoppingCart() {
        this.booksInCart = new ArrayList<>();
    }
    
    public ShoppingCart(List<Book> booksInCart) {
        this.booksInCart = booksInCart;
    }
    
    public List<Book> getBooks() {
        return booksInCart;
    }
    
    public void addBook(Book book) {
        booksInCart.add(book);
    }
    
    public void removeBook(Book book) {
        for(Book b : booksInCart) {
            if (b.getIsbn() == book.getIsbn()) {
                booksInCart.remove(b);
                break;
            }
        }
    }
}