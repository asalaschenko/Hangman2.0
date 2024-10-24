public abstract class GameLevel {

    private final int maxUserError;
    private final int minWordLength;
    private final int maxWordLength;

    public GameLevel(int maxUserError, int minWordLength, int maxWordLength) {
        this.maxUserError = maxUserError;
        this.minWordLength = minWordLength;
        this.maxWordLength = maxWordLength;
    }

    public int getMaxUserError() {
        return maxUserError;
    }

    public int getMinWordLength() {
        return minWordLength;
    }

    public int getMaxWordLength() {
        return maxWordLength;
    }

}
