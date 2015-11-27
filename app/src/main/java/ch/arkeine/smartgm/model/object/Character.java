package ch.arkeine.smartgm.model.object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arkeine on 11/21/15.
 */
public class Character extends ObjectWithGameIdentifier {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public Character(long id, long gameId)
    {
        this(id, gameId, "");
    }

    public Character(long id, long gameId, String name)
    {
        super(id, gameId);
        this.listStatistic = new ArrayList<>(10);
        this.listEquipped = new ArrayList<>(10);
        this.listInventories = new ArrayList<>(10);
        this.name = name;
    }

    /* ============================================ */
    // OVERRRIDE
    /* ============================================ */

    @Override
    public String toString() {
        return "Character{" +
                "listStatistic=" + listStatistic +
                ", listEquipped=" + listEquipped +
                ", listInventories=" + listInventories +
                ", inCharacterShortcut=" + inCharacterShortcut +
                '}';
    }

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInCharacterShortcut() {
        return inCharacterShortcut;
    }

    public void setInCharacterShortcut(boolean inCharacterShortcut) {
        this.inCharacterShortcut = inCharacterShortcut;
    }

    public List<Statistic> getStatistics() {
        return listStatistic;
    }

    public List<Item> getEquipped() {
        return listEquipped;
    }

    public List<Inventory> getInventories() {
        return listInventories;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private List<Statistic> listStatistic;
    private List<Item> listEquipped;
    private List<Inventory> listInventories;
    private boolean inCharacterShortcut;
    private String name;

}
