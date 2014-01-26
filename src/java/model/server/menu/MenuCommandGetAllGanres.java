package model.server.menu;

import inject.Inject;
import inject.clazz.ApplicationContextClass;
import javax.servlet.http.HttpServletRequest;
import model.db.dao.GanreDao;

/**
 *
 * @author koxa
 */
public class MenuCommandGetAllGanres extends ApplicationContextClass implements MenuCommand{

    @Inject("GanreDao")
    GanreDao ganreDao;
    
    @Override
    public void execute(HttpServletRequest request) {
        //GanreDao ganreDao = new  GanreDaoMock();
        
        Menu menu = new Menu(ganreDao.getAllGanres());
        
        String menuAttributeName = "menu";
        request.setAttribute(menuAttributeName, menu);
    }   
}