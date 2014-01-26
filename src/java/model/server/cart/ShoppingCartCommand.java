/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.server.cart;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author koxa
 */
public interface ShoppingCartCommand {
    
    enum Command {
        ADD,
        REMOVE,
        PROCEED
    }
    
    void execute(HttpServletRequest request);
}
