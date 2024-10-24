public class GameEasyLevel extends GameLevel {

    private final static int MAX_USER_ERROR = 7;
    private final static int MIN_WORD_LENGTH = 3;
    private final static int MAX_WORD_LENGTH = 5;

    public GameEasyLevel() {
        super(MAX_USER_ERROR, MIN_WORD_LENGTH, MAX_WORD_LENGTH);
    }

}
