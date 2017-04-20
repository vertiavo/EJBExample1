package wipb.jee.clientdemo.ejb;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vertiavo on 07.04.17.
 */
@Singleton
public class CacheBean implements Serializable {
    private Map<String, CacheEntry> map = new HashMap<>();

    private static class CacheEntry {
        Integer data;

        public CacheEntry(Integer data) {
            this.data = data;
        }
    }

    public void put(String key, Integer value) {
        map.put(key, new CacheEntry(value));
    }

    public Integer get(String key) {
        CacheEntry entry = map.get(key);
        return entry != null ? entry.data : null;
    }

    public boolean contains(String key) {
        return map.containsKey(key);
    }

    public Object remove(String key) {
        CacheEntry entry = map.get(key);
        map.remove(key);
        return entry.data;
    }

    @Schedule(hour = "*", minute = "*/2", second = "0")
    private void cleanup() {
        System.out.println("cleanup called");
        map.clear();
    }

}
