public class GameMediumLevel extends GameLevel {

    private final static int MAX_USER_ERROR = 8;
    private final static int MIN_WORD_LENGTH = 6;
    private final static int MAX_WORD_LENGTH = 9;

    public GameMediumLevel() {
        super(MAX_USER_ERROR, MIN_WORD_LENGTH, MAX_WORD_LENGTH);
    }

}
