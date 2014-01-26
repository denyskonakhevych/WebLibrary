package inject.impl;

import inject.ApplicationContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author koxa
 */
public class ApplicationContextImpl implements ApplicationContext{
    
    private static final String APP_CONTEXT_PROPERTY_FILE_PATH = "C:/Users/koxa/Documents/NetBeansProjects/WebLibrary/build/web/WEB-INF/classes/inject/applicationContext.properties";

    @Override
    public void init(String xml) {
    }

    @Override
    public Object getBean(String name) {
        return getBean(name, Object.class);
    }

    @Override
    public <T> T getBean(String name, Class<T> clazz) {
        
        try(FileInputStream in = new FileInputStream(APP_CONTEXT_PROPERTY_FILE_PATH)){
            Properties props = new Properties();
            props.load(in);
            String beanClass = props.getProperty(name);
            return (T) newInstance(beanClass);
        } catch (IOException ex) {
            Logger.getLogger(ApplicationContextImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("No property file: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ApplicationContextImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("No entity for name '" + name + "'");
        } catch (InstantiationException ex) {
            Logger.getLogger(ApplicationContextImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ApplicationContextImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new RuntimeException("No such bean declared: " + name);
    }
    
    private Object newInstance(String beanClassName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> beanClass = Class.forName(beanClassName);
        return beanClass.newInstance();
    }
}