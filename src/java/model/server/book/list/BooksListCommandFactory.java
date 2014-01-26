/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.server.book.list;

import model.db.dao.GanreDao;
import model.db.dao.implementation.jdbc.GanreDaoJdbc;

/**
 *
 * @author koxa
 */
public class BooksListCommandFactory {
    
    public BooksListCommand getCommand(Integer ganreId) {
        
        GanreDao ganreDao = new  GanreDaoJdbc();
        if (ganreDao.getGanreById(ganreId) == null) {
            return new BooksListCommandGetDefault();
        } else {
            return new BooksListCommandGetByGanre(ganreId);
        }
    }
}
