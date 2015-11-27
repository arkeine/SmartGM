package ch.arkeine.smartgm.model.object;

/**
 * Created by arkeine on 11/21/15.
 */
public class Statistic extends  ObjectWithIdentifier{

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public Statistic(long id, long characterId)
    {
        this(id, characterId, 0,0,0);
    }

    public Statistic(long id, long characterId, int min, int current, int max) {
        this(id, characterId, min, current, max, "");
    }

    public Statistic(long id, long characterId, int current, int max, int min, String name) {
        super(id);
        this.characterId = characterId;
        this.current = current;
        this.max = max;
        this.min = min;
        this.name = name;
    }

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public int getMin() {
        return min;
    }

    public boolean setMin(int min) {
        if(min <= current) {
            this.min = min;
            return true;
        }else
        {
            return false;
        }
    }

    public int getMax() {
        return max;
    }

    public boolean setMax(int max) {
        if(max >= current) {
            this.max = max;
            return true;
        }else
        {
            return false;
        }
    }

    public int getCurrent() {
        return current;
    }

    public boolean setCurrent(int current) {
        if(max >= current && current >= min) {
            this.current = current;
            return true;
        }else
        {
            return false;
        }
    }

    public long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(long characterId) {
        this.characterId = characterId;
    }

    public boolean setValues(int min, int current, int max) {
        if(max >= current && current >= min) {
            this.min = min;
            this.current = current;
            this.max = max;
            return true;
        }else
        {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private int min;
    private int max;
    private int current;
    private long characterId;
    private String name;
}
