/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.server.menu;

import java.util.List;
import model.db.entities.Ganre;

/**
 *
 * @author koxa
 */
public class Menu {
    List<Ganre> menuItems;

    public Menu(List<Ganre> menuItems) {
        this.menuItems = menuItems;
    }

    public List<Ganre> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<Ganre> menuItems) {
        this.menuItems = menuItems;
    }
}
