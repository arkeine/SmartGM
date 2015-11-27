package ch.arkeine.smartgm.model.object;

import java.util.Random;

/**
 * Created by arkeine on 11/9/15.
 */
public class Dice extends ObjectWithGameIdentifier {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public Dice(long id, long gameId, int nbFaces) {
        super(id, gameId);
        this.nbFaces = uniforming(nbFaces);
        this.random = new Random();
    }

    /* ============================================ */
    // OVERRRIDE
    /* ============================================ */

    @Override
    public String toString() {
        return "Dice{" +
                "nbFaces=" + nbFaces +
                '}';
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

    private int nbFaces;
    private Random random;

    /* ============================================ */
    // CONSTANTS
    /* ============================================ */

    public final static int MINIMAL_FACE_NUMBER = 2;
    public final static int MAXIMAL_FACE_NUMBER = 100;
}
