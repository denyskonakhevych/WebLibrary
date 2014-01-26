/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.entities;

/**
 *
 * @author koxa
 */
public class Card {
    
    private Integer id;
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Card() {
    }

    public Card(Integer id, User user) {
        this.id = id;
        this.user = user;
    }
}