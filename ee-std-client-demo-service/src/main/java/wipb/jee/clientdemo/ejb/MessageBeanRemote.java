package wipb.jee.clientdemo.ejb;

import javax.ejb.Remote;

/**
 *
 * @author dr
 */
@Remote
public interface MessageBeanRemote {

    String getMessage();
    void put(String key, Integer value);
    Integer get(String key);
    boolean contains(String key);
    Object remove(String key);
    
}
