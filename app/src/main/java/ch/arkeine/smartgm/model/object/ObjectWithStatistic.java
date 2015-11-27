package ch.arkeine.smartgm.model.object;

import java.util.List;

/**
 * Created by arkeine on 11/24/15.
 */
public class ObjectWithStatistic extends ObjectWithGameIdentifier {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public ObjectWithStatistic(long id, long gameId) {
        super(id, gameId);
    }

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public List<Statistic> getStatistics() {
        return listStatistic;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private List<Statistic> listStatistic;
}
