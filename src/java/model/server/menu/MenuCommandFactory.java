/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.server.menu;

/**
 *
 * @author koxa
 */
public class MenuCommandFactory {
    
    public MenuCommand getMenu() {
        return new MenuCommandGetAllGanres();
    }
}
