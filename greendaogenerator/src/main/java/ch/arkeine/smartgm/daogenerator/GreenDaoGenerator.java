package ch.arkeine.smartgm.daogenerator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class GreenDaoGenerator {

    public static String identifiedDataObjectInterface = "IdentifiedDataObject";
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "ch.arkeine.smartgm.model");
        schema.enableKeepSectionsByDefault();

        /* ============================================ */
        // ENTITIES DECLARATION
        /* ============================================ */

        Entity universe = schema.addEntity("Universe");
        universe.addIdProperty();
        universe.addStringProperty("name");
        universe.addStringProperty("description");
        universe.implementsInterface(identifiedDataObjectInterface);

        Entity game = schema.addEntity("Game");
        game.addIdProperty();
        game.addStringProperty("Name");
        game.addStringProperty("description");
        game.implementsInterface(identifiedDataObjectInterface);

        Entity wiki = schema.addEntity("Wiki");
        wiki.addIdProperty();
        wiki.addStringProperty("name");
        wiki.addStringProperty("description");
        wiki.implementsInterface(identifiedDataObjectInterface);

        Entity timeLine = schema.addEntity("Timeline");
        timeLine.addIdProperty();
        timeLine.addDateProperty("date");
        timeLine.addStringProperty("description");
        timeLine.implementsInterface(identifiedDataObjectInterface);

        Entity table = schema.addEntity("Table");
        table.addIdProperty();
        table.addDateProperty("name");
        table.addStringProperty("description");
        table.implementsInterface(identifiedDataObjectInterface);

        Entity tableItem = schema.addEntity("Tableitem");
        tableItem.addIdProperty();
        tableItem.addDateProperty("name");
        tableItem.addStringProperty("description");
        tableItem.addIntProperty("weight");
        tableItem.implementsInterface(identifiedDataObjectInterface);

        Entity dice = schema.addEntity("Dice");
        dice.addIdProperty();
        dice.addIntProperty("face");
        dice.addIntProperty("count");
        dice.implementsInterface(identifiedDataObjectInterface);

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
            tableToMany.setName("tableitems");
        }

        new DaoGenerator().generateAll(schema, "app/src/main/java/");
    }
}
