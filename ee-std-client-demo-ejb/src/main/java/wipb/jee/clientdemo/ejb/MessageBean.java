package wipb.jee.clientdemo.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;

/**
 * 
 * @author dr
 */
@Stateless
public class MessageBean implements MessageBeanRemote, Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private CacheBean cacheBean;
    
    @Override
    public String getMessage() {
        return "Hello from Remote EJB Bean!";
    }

    @Override
    public void put(String key, Integer value) {
        cacheBean.put(key, value);
    }

    public Integer get(String key) {
        return cacheBean.get(key);
    }

    @Override
    public boolean contains(String key) {
        return cacheBean.contains(key);
    }

    @Override
    public Object remove(String key) {
        return cacheBean.remove(key);
    }
}
