package ch.arkeine.smartgm;

/**
 * Manage the user selections, like game or character in the way to display
 * filtered data from this selections
 */
public class MainFilterManager {

	/* ============================================ */
    // LISTENER
    /* ============================================ */

    public void setOnGameFilterChangeListener(GameFilterChangeListener listener) {
        this.gameChangeListener = listener;
    }

    public void setOnCharacterFilterChangeListener(CharacterFilterChangeListener listener) {
        this.characterChangeListener = listener;
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public boolean isGameSelected() {
        return gameId != null;
    }

    public boolean isCharacterSelected() {
        return characterId != null;
    }

    public Long getGameId() {
        return gameId;
    }

    public Long getCharacterId() {
        return characterId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
        //if(checkFireEvent(this.gameId, gameId))
        gameChangeListener.onGameSelectionChanged(gameId);
    }


    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
        //if(checkFireEvent(this.characterId, characterId))
        characterChangeListener.onCharacterSelectionChanged(characterId);
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    /**
     * @return Return true if an event should be fire
     */
    private boolean checkFireEvent(Long curentId, Long newId) {
        // Warning usage of short circuit operators
        return (newId != null && !newId.equals(curentId)) ||
                (curentId != null);
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private GameFilterChangeListener gameChangeListener;
    private CharacterFilterChangeListener characterChangeListener;
    private Long gameId;
    private Long characterId;

    /* ============================================ */
    // INTERNAL CLASS / INTERFACE
    /* ============================================ */

    public interface GameFilterChangeListener {
        void onGameSelectionChanged(Long gameId);
    }

    public interface CharacterFilterChangeListener {
        void onCharacterSelectionChanged(Long characterId);
    }
}
