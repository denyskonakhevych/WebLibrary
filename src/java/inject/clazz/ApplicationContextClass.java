package inject.clazz;

import inject.ApplicationContext;
import inject.Inject;
import inject.impl.ApplicationContextImpl;
import java.lang.reflect.Field;

/**
 *
 * @author koxa
 */
public abstract class ApplicationContextClass {

    public ApplicationContextClass() {
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
            throw new RuntimeException("Can't inject: " + ex.getMessage());
        }
    }   
}