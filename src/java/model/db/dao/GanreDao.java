/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.dao;

import java.util.List;
import model.db.entities.Ganre;

/**
 *
 * @author koxa
 */
public interface GanreDao {
    
    List<Ganre> getAllGanres();
    
    Ganre getGanreByName(String ganreName);
    
    Ganre getGanreById(Integer ganreId);
}
