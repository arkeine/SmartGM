package ch.arkeine.smartgm;

/**
 * Manage the user selections, like game or character in the way to display
 * filtered data from this selections
 */
public class MainFilterManager {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public MainFilterManager() {
        this.gameId = Constants.INVALID_ID;
        this.characterId = Constants.INVALID_ID;
        this.universeId = Constants.INVALID_ID;
    }

    /* ============================================ */
    // LISTENER
    /* ============================================ */

    public void setOnGameFilterChangeListener(GameFilterChangeListener listener) {
        this.gameChangeListener = listener;
    }

    public void setOnUniverseFilterChangeListener(UniverseFilterChangeListener listener) {
        this.universeChangeListener = listener;
    }

    public void setOnCharacterFilterChangeListener(CharacterFilterChangeListener listener) {
        this.characterChangeListener = listener;
    }

    public void refrechListeners(){
        if(gameChangeListener != null) gameChangeListener.onGameSelectionChanged(gameId);
        if(characterChangeListener != null) characterChangeListener.onCharacterSelectionChanged(characterId);
        if(universeChangeListener != null) universeChangeListener.onUniverseSelectionChanged(universeId);
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

    public boolean isUniverseSelected() {
        return universeId != null;
    }

    public Long getGameId() {
        return gameId;
    }

    public Long getUniverseId() {
        return universeId;
    }

    public Long getCharacterId() {
        return characterId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
        if(gameChangeListener != null) gameChangeListener.onGameSelectionChanged(gameId);
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
        if(characterChangeListener != null) characterChangeListener.onCharacterSelectionChanged(characterId);
    }

    public void setUniverseId(Long universeId) {
        this.universeId = universeId;
        if(universeChangeListener != null) universeChangeListener.onUniverseSelectionChanged(universeId);
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
    private UniverseFilterChangeListener universeChangeListener;
    private Long gameId;
    private Long characterId;
    private Long universeId;

    /* ============================================ */
    // INTERNAL CLASS / INTERFACE
    /* ============================================ */

    public interface GameFilterChangeListener {
        void onGameSelectionChanged(Long gameId);
    }

    public interface CharacterFilterChangeListener {
        void onCharacterSelectionChanged(Long characterId);
    }

    public interface UniverseFilterChangeListener {
        void onUniverseSelectionChanged(Long universeId);
    }
}
