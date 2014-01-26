package inject;

/**
 *
 * @author koxa
 */
public interface ApplicationContext {
    
    public void init (String xml);
    
    public Object getBean(String name);
    
    public <T> T getBean(String name, Class<T> clazz);
}
