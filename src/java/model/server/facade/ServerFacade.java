/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.server.facade;

import javax.servlet.http.HttpServletRequest;
import model.server.book.list.BooksListCommand;
import model.server.book.list.BooksListCommandFactory;
import model.server.book.selected.SelectedBookAdapter;
import model.server.cart.ShoppingCartCommand;
import model.server.cart.ShoppingCartCommandFactory;
import model.server.menu.MenuCommand;
import model.server.menu.MenuCommandFactory;

/**
 *
 * @author koxa
 */
public class ServerFacade {
    
    HttpServletRequest request;

    public ServerFacade(HttpServletRequest request) {
        this.request = request;
    }
    
    public void setBooksList() {
        String categoryParameterName = "category";
        
        Integer category = request.getParameter(categoryParameterName) == null 
            ? null 
            : Integer.parseInt(request.getParameter(categoryParameterName));
        BooksListCommandFactory booksListCommandFactory = new BooksListCommandFactory();
        BooksListCommand booksListCommand = booksListCommandFactory.getCommand(category);
        booksListCommand.execute(request);
    }
    
    public void setSelectedBook() {
        SelectedBookAdapter selectedBookAdapter = new SelectedBookAdapter();
        selectedBookAdapter.setSelectedBook(request);
    }
    
    public void setMenu() {
        MenuCommandFactory menuCommandFactory = new MenuCommandFactory();
        MenuCommand menuCommand = menuCommandFactory.getMenu();
        menuCommand.execute(request);
    }
    
    public void addToCart() {
        ShoppingCartCommandFactory cartCommandFactory = new ShoppingCartCommandFactory();
        ShoppingCartCommand cartCommand = cartCommandFactory.getCommand(ShoppingCartCommand.Command.ADD);
        cartCommand.execute(request);
    }
    
    public void removeFromCart() {
        ShoppingCartCommandFactory cartCommandFactory = new ShoppingCartCommandFactory();
        ShoppingCartCommand cartCommand = cartCommandFactory.getCommand(ShoppingCartCommand.Command.REMOVE);
        cartCommand.execute(request);
    }
    
    public void proceedOrder() {
        ShoppingCartCommandFactory cartCommandFactory = new ShoppingCartCommandFactory();
        ShoppingCartCommand cartCommand = cartCommandFactory.getCommand(ShoppingCartCommand.Command.PROCEED);
        cartCommand.execute(request);
    }
}