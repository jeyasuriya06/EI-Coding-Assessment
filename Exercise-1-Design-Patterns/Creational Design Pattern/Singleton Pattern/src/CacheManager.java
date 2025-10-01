package src;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CacheManager implements Serializable{

    private static CacheManager instance;
    private Map<String, Object> cache;

    private CacheManager() {
        if (instance != null) { // To protect against Reflection attack on Singleton Patterns
            throw new IllegalArgumentException("Object already exists");
        }
        cache = new HashMap<>();
    }

    public static CacheManager getInstance() {
        if (instance == null) {
            synchronized (CacheManager.class) { // Double check to ensure thread safety and optimize performance
                if (instance == null) {
                    instance = new CacheManager();
                }
            }
        }

        return instance;
    }

    public void put(String key, Object value) {
        cache.put(key, value);
    }

    public Object get(String key) {
        Object value = cache.get(key);
        return value;
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }

}