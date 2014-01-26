/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.entities;

/**
 *
 * @author koxa
 */
public class Ganre {
    
    private final Integer GANRE_ID;
    private String name;

    public Ganre(Integer ganre_id, String name) {
        this.GANRE_ID = ganre_id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGanreId() {
        return GANRE_ID;
    }
}
