import java.lang.invoke.MethodHandles;

public class GameLevelFactory {

    public static final int EASY_KEY = 1;
    public static final int MEDIUM_KEY = 2;
    public static final int HARD_KEY = 3;

    public static GameLevel create(int key) throws Exception {
        return switch (key) {
            case EASY_KEY -> new GameEasyLevel();
            case MEDIUM_KEY -> new GameMediumLevel();
            case HARD_KEY -> new GameHardLevel();
            default -> throw new GameLevelFactoryCreateException("Call "
                    + Thread.currentThread().getStackTrace()[1].getMethodName()
                    + "() of "
                    + MethodHandles.lookup().lookupClass()
                    + " : key = " + key + " недопустимый ключ");
        };
    }
}
