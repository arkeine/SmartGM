package ch.arkeine.smartgm.daogenerator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class GreenDaoGenerator {
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "ch.arkeine.smartgm.model");

        /* ============================================ */
        // ENTITIES DECLARATION
        /* ============================================ */

        Entity universe = schema.addEntity("Universes");
        universe.addIdProperty();
        universe.addStringProperty("name");
        universe.addStringProperty("description");

        Entity game = schema.addEntity("Games");
        game.addIdProperty();
        game.addStringProperty("Name");
        game.addStringProperty("description");

        Entity wiki = schema.addEntity("Wikis");
        wiki.addIdProperty();
        wiki.addStringProperty("name");
        wiki.addStringProperty("description");

        Entity timeLine = schema.addEntity("Timelines");
        timeLine.addIdProperty();
        timeLine.addDateProperty("date");
        timeLine.addStringProperty("description");

        Entity table = schema.addEntity("Tables");
        table.addIdProperty();
        table.addDateProperty("name");
        table.addStringProperty("description");

        Entity tableItem = schema.addEntity("Tableitems");
        tableItem.addIdProperty();
        tableItem.addDateProperty("name");
        tableItem.addStringProperty("description");
        tableItem.addIntProperty("weight");

        Entity dice = schema.addEntity("Dices");
        dice.addIdProperty();
        dice.addIntProperty("face");
        dice.addIntProperty("count");

        /* ============================================ */
        // RELATIONS DECLARATION
        /* ============================================ */

        // relation universe-game
        {
            Property fk = game.addLongProperty("fk_universe_id").getProperty();
            game.addToOne(universe, fk);
            ToMany universeToMany = universe.addToMany(game, fk);
            universeToMany.setName("games");
        }
        // relation universe-wiki
        {
            Property fk = wiki.addLongProperty("fk_universe_id").getProperty();
            wiki.addToOne(universe, fk);
            ToMany universeToMany = universe.addToMany(wiki, fk);
            universeToMany.setName("wikis");
        }
        // relation universe-table
        {
            Property fk = table.addLongProperty("fk_universe_id").getProperty();
            table.addToOne(universe, fk);
            ToMany universeToMany = universe.addToMany(table, fk);
            universeToMany.setName("tables");
        }
        // relation universe-dice
        {
            Property fk = dice.addLongProperty("fk_universe_id").getProperty();
            dice.addToOne(universe, fk);
            ToMany universeToMany = universe.addToMany(dice, fk);
            universeToMany.setName("dices");
        }
        // relation game-timeline
        {
            Property fk = timeLine.addLongProperty("fk_game_id").getProperty();
            timeLine.addToOne(game, fk);
            ToMany gameToMany = game.addToMany(timeLine, fk);
            gameToMany.setName("timelines");
        }
        // relation table-tableItem
        {
            Property fk = tableItem.addLongProperty("fk_table_id").getProperty();
            tableItem.addToOne(table, fk);
            ToMany tableToMany = table.addToMany(tableItem, fk);
            tableToMany.setName("tableitem");
        }

        new DaoGenerator().generateAll(schema, "app/src/main/java/");
    }
}
