package ch.arkeine.smartgm.model.dao.object;

import java.util.Random;

/**
 * DAO pattern. Represent a dice store in the database in the object world.
 */
public class Dice extends ObjectWithIdentifier {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public Dice(long id, long universeId, int nbFaces) {
        super(id);
        this.universeId = universeId;
        this.nbFaces = uniforming(nbFaces);
        this.random = new Random();
    }

    /* ============================================ */
    // OVERRRIDE
    /* ============================================ */

    @Override
    public String toString() {
        return String.valueOf(nbFaces);
    }

/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public int getNbFaces() {
        return nbFaces;
    }

    public void setNbFaces(int nbFaces) {
        this.nbFaces = uniforming(nbFaces);
    }

    public int getRandomValue() {
        // +1 cause nextInt is between [0-n[
        return random.nextInt(nbFaces) + 1;
    }

    public long getUniverseId() {
        return universeId;
    }

    public void setUniverseId(long universeId) {
        this.universeId = universeId;
    }


    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private int uniforming(int value) {
        value = value < MINIMAL_FACE_NUMBER ? MINIMAL_FACE_NUMBER : value;
        value = value > MAXIMAL_FACE_NUMBER ? MAXIMAL_FACE_NUMBER : value;
        return value;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private long universeId;
    private int nbFaces;
    private Random random;

    /* ============================================ */
    // CONSTANTS
    /* ============================================ */

    public final static int MINIMAL_FACE_NUMBER = 2;
    public final static int MAXIMAL_FACE_NUMBER = 100;
}
