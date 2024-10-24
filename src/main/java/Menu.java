import java.util.Random;
import java.util.Scanner;

class Menu{

    private String fileName;

    public Menu(String fileName){
        this.fileName = fileName;
    }

    public void start() throws Exception {
        ColorPrinter.printYellowText("Приветствую в игре Виселица !\n");
        boolean gameLoopManage = true;
        Scanner sc = new Scanner (System.in);
        while(gameLoopManage){
            printStartMessage();
            switch (sc.nextLine().toUpperCase()){
                case "GO":
                    startGame();
                    break;
                case "EXIT":
                    gameLoopManage = false;
                    ColorPrinter.printYellowText("GOOD BYE !");
                    break;
                case "HELP":
                    printHelpMessage();
                    break;
                default:
                    System.out.println("Неверно.");
                    System.out.println("Введите одно из значений GO/HELP/EXIT");
                    break;
            }
        }
    }

    public void startGame() throws Exception {
        GameLevel gameLevel = inputGameLevel();
        LoaderWord loader;
        if(gameLevel != null){
            try{
            loader = new LoaderWord(fileName);
            String word = loader.loadWord(generateRandomLength(gameLevel));
            Game game = new Game(new Word(word), gameLevel);
            game.start();
            } catch (NullPointerException e) {
                ColorPrinter.printRedBackgroundText("Проверьте имя файла filename !");
                ColorPrinter.printRedBackgroundText("in " + this.getClass() + " method " + Thread.currentThread().getStackTrace()[1].getMethodName());
            }catch(OpenLetterException e){
                ColorPrinter.printRedBackgroundText(e.getMessage());
                ColorPrinter.printRedBackgroundText("in " + this.getClass() + " method " + Thread.currentThread().getStackTrace()[1].getMethodName());
            }
        }
    }

    private GameLevel inputGameLevel() {
        System.out.println("Введите уровень игры:");
        System.out.printf("%d - EASY \n", GameLevelFactory.EASY_KEY);
        System.out.printf("%d - MEDIUM \n", GameLevelFactory.MEDIUM_KEY);
        System.out.printf("%d - HARD \n", GameLevelFactory.HARD_KEY);
        System.out.printf("EXIT - выход в главное меню \n");

        int key = getValidNumberOfLevel();
        if(key==4)
            return null;
        try {
            return GameLevelFactory.create(key);
        }catch(GameLevelFactoryCreateException e){
            ColorPrinter.printRedBackgroundText(e.getMessage());
            ColorPrinter.printRedBackgroundText("in " + this.getClass() + " method " + Thread.currentThread().getStackTrace()[1].getMethodName());
            return null;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private int getValidNumberOfLevel(){
        Scanner sc = new Scanner (System.in);
        while(true){
            switch (sc.nextLine().toUpperCase()) {
                case ("1"):
                    return 1;
                case ("2"):
                    return 2;
                case ("3"):
                    return 3;
                case ("EXIT"):
                    return 4;
                default:
                    System.out.println("Неверно. Введите одно из значений 1(EASY)/2(MEDIUM)/3(HARD)/EXIT(Выход в главное меню)");
                    break;
            }
        }
    }

    private static void printStartMessage(){
        System.out.println("""
                ГЛАВНОЕ МЕНЮ\
                
                Напиши: \
                
                "GO", чтобы начать ;\
                
                "HELP", чтобы прочитать правила игры ;\
                
                "EXIT" для выхода из игры.""");
    }

    private static int generateRandomLength(GameLevel gameLevel) {
        Random r = new Random();
        int lengthWord = r.nextInt(gameLevel.getMaxWordLength()- gameLevel.getMinWordLength() + 1) + gameLevel.getMinWordLength();
        return lengthWord;
    }


    private void printHelpMessage() {
        ColorPrinter.printYellowText("Программа загадывает слово. Вам необходимо, отгадывая буквы, разгадать всё слово. " +
                "Вы можете совершить не более:\n" + "для EASY   - " + new GameEasyLevel().getMaxUserError() + " неудачных попыток \n" +
                "для MEDIUM - " + new GameMediumLevel().getMaxUserError() + " неудачных попыток \n" +
                "для HARD   - " + new GameHardLevel().getMaxUserError() + " неудачных попыток \n");
    }
}