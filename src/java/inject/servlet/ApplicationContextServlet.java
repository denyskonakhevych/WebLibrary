/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inject.servlet;

import inject.ApplicationContext;
import inject.Inject;
import inject.impl.ApplicationContextImpl;
import java.lang.reflect.Field;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author koxa
 */
public class ApplicationContextServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {
            ApplicationContext appCtx = new ApplicationContextImpl();
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Inject annotation = field.getAnnotation(Inject.class);
                if (annotation != null) {
                    String beanName;
                    beanName = annotation.value();
                    Object bean = appCtx.getBean(beanName);
                    if (bean == null) {
                        throw new Exception("There isn't bean with name '" + beanName + "'");
                    }
                    field.set(this, bean);
                }
            }
        } catch (Exception ex) {
            throw new ServletException("Can't inject: " + ex.getMessage());
        }
    }
}
