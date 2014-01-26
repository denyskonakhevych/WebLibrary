/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.server.menu;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author koxa
 */
public interface MenuCommand {
    
    void execute(HttpServletRequest request);
}
