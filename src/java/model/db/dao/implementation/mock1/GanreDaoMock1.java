/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.dao.implementation.mock;

import java.util.ArrayList;
import java.util.List;
import model.db.dao.GanreDao;
import model.db.entities.Ganre;

/**
 *
 * @author koxa
 */
public class GanreDaoMock1 implements GanreDao{

    List<Ganre> allGanres;
    
    {
        getAllGanres();
    }
    
    @Override
    public List<Ganre> getAllGanres() {
        List<Ganre> newGanres = new ArrayList<>();
        int number = 0;
        newGanres.add(new Ganre(number++, "Fantastic"));
        newGanres.add(new Ganre(number++, "Prose"));
        newGanres.add(new Ganre(number++, "Comedy"));
        
        allGanres = newGanres;
        return newGanres;
    }

    @Override
    public Ganre getGanreByName(String ganreName) {
        if (ganreName == null) {
            return null;
        }
        //List<Ganre> currentGanres = getAllGanres();
        List<Ganre> currentGanres = allGanres;
        for (Ganre ganre : currentGanres) {
            if (ganre.getName().equals(ganreName)) {
                return ganre;
            }
        }
        return null;
    }

    @Override
    public Ganre getGanreById(Integer ganreId) {
        if (ganreId == null) {
            return null;
        }
        //List<Ganre> currentGanres = getAllGanres();
        List<Ganre> currentGanres = allGanres;
        for (Ganre ganre : currentGanres) {
            if (ganre.getGanreId().equals(ganreId)) {
                return ganre;
            }
        }
        return null;
    }
}