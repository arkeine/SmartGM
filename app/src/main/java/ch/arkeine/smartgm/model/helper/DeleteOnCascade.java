package ch.arkeine.smartgm.model.helper;

import android.util.Log;

import ch.arkeine.smartgm.model.DaoSession;
import ch.arkeine.smartgm.model.Dice;
import ch.arkeine.smartgm.model.Game;
import ch.arkeine.smartgm.model.Table;
import ch.arkeine.smartgm.model.Timeline;
import ch.arkeine.smartgm.model.Universe;
import ch.arkeine.smartgm.model.Wiki;

/**
 * GreenDAO don't manage the on cascade delete.
 *
 * This class delete the given object and all children object
 */
public class DeleteOnCascade {

    public static void deleteUniverse(DaoSession s, Universe u){
        for(Wiki w : u.getWikis()) {
            deleteWiki(s, w);
        }
        for(Game g : u.getGames()) {
            deleteGame(s, g);
        }
        for(Dice d : u.getDices()) {
            deleteDice(s, d);
        }
        for(Table t : u.getTables()) {
            deleteTable(s, t);
        }

        s.getUniverseDao().delete(u);
    }

    public static void deleteTable(DaoSession s, Table t) {
        s.getTableDao().delete(t);
    }

    public static void deleteDice(DaoSession s, Dice d) {
        s.getDiceDao().delete(d);
    }

    public static void deleteGame(DaoSession s, Game g) {
        for(Timeline t : g.getTimelines()) {
            deleteTimeLine(s, t);
        }

        s.getGameDao().delete(g);
    }

    public static void deleteTimeLine(DaoSession s, Timeline t) {
        s.getTimelineDao().delete(t);
    }

    public static void deleteWiki(DaoSession s, Wiki w){
        s.getWikiDao().delete(w);
    }
}
