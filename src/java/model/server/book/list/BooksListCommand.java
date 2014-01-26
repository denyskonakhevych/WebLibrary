/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.server.book.list;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author koxa
 */
public interface BooksListCommand {
    
    void execute(HttpServletRequest request);
}
