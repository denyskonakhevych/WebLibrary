/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.server.cart;

/**
 *
 * @author koxa
 */
public class ShoppingCartCommandFactory {
    
    public ShoppingCartCommand getCommand(ShoppingCartCommand.Command command) {
        if (command.equals(ShoppingCartCommand.Command.ADD)) {
            return new ShoppingCartCommandAdd();
        } else if (command.equals(ShoppingCartCommand.Command.REMOVE)) {
            return new ShoppingCartCommandRemove();
        } else if (command.equals(ShoppingCartCommand.Command.PROCEED)) {
            return new ShoppingCartCommandProceedOrder();
        } else {
            return null;
        }
    }
}
