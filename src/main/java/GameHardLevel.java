public class GameHardLevel extends GameLevel {

    private final static int MAX_USER_ERROR = 9;
    private final static int MIN_WORD_LENGTH = 10;
    private final static int MAX_WORD_LENGTH = 20;

    public GameHardLevel() {
        super(MAX_USER_ERROR, MIN_WORD_LENGTH, MAX_WORD_LENGTH);
    }

}
