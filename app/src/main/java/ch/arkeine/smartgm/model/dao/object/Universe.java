package ch.arkeine.smartgm.model.dao.object;

import java.util.HashMap;
import java.util.Map;

/**
 * DAO pattern. Represent a universe store in the database in the object world.
 */
public class Universe extends ObjectDescribable {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public Universe(long id) {
        super(id);
        this.mapSettings = new HashMap<>();
    }

    /* ============================================ */
    // DELEGATE
    /* ============================================ */

    public String getOrDefault(Object key, String defaultValue) {
        if(mapSettings.containsKey(key))
            return mapSettings.get(key);
        else
            return defaultValue;
    }

    public String put(String key, String value) {
        return mapSettings.put(key, value);
    }

    public String remove(String key) {
        return mapSettings.remove(key);
    }

    public void clear() {
        mapSettings.clear();
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private Map<String, String> mapSettings;
}
