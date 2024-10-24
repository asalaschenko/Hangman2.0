import java.util.Scanner;

class Game {

    private Word word;
    private GameLevel gameLevel;
    private ErrorChars errorChars;
    private int countUserError;
    private final String stopGame = "EXIT";


    public Game(Word word, GameLevel gameLevel) {
        this.word = word;
        this.gameLevel = gameLevel;
        this.errorChars = new ErrorChars();
        countUserError = 0;
    }

    public void start() throws Exception {
        boolean gameLoopManage = true;
        String text;
        ColorPrinter.printYellowText("Игра началась ! " + "Чтобы выйти в главное меню введите \"EXIT\"");
        while(gameLoopManage){
            text = inputLetter();
            if(text.equalsIgnoreCase(stopGame)){
                ColorPrinter.printYellowText("Вы вышли из игры !");
                gameLoopManage = false;
            } else if(word.textContains(text.charAt(0))) {
                try {
                    word.openLetter(text.charAt(0));
                }catch(OpenLetterException oe){
                    ColorPrinter.printRedBackgroundText(oe.getMessage());
                    ColorPrinter.printRedBackgroundText("in " + this.getClass() + " method " + Thread.currentThread().getStackTrace()[1].getMethodName());
                }
                printLuckyMessage(text.charAt(0));
            }else{
                errorChars.append(text.charAt(0));
                countUserError++;
                printUnluckyMessage();
            }
            if(countUserError == gameLevel.getMaxUserError()){
                printResultOfGame(false);
                gameLoopManage = false;
            } else if(!word.isUnopenedLetters()) {
                printResultOfGame(true);
                gameLoopManage = false;
            }
        }
    }

    private String inputLetter(){
        String text = "";
        Scanner sc = new Scanner(System.in);
        while(true){
            monitor();
            System.out.println("Введите 1 букву или \"EXIT\"");
            text = sc.nextLine().toLowerCase();
            if(text.isEmpty()){
                System.out.println("Введите букву, бл* !");
                continue;
            }
            if(text.length()>1 && !text.equals("exit")) {
                System.out.println("Вы ввели больше одной буквы !");
                continue;
            }
            if(isEnglishLetter(text.charAt(0)) && !text.equals("exit")){
                System.out.println("Вы ввели английскую букву ! Введите букву в русской раскладке !");
                continue;
            }
            if(!isLetter(text.charAt(0)) && !text.equals("exit")){
                System.out.println("Вы ввели неверный символ ! Введите букву !");
                continue;
            }
            if(isEnteredLetter(text.charAt(0)) && !text.equals("exit")){
                System.out.println("Буква уже была !");
                continue;
            }
            if(text.equals("exit")){
                return stopGame;
            }else{
                return text;
            }
        }
    }

    public boolean isLetter(char letter) {
        boolean valid = (letter >= 'а' && letter <= 'я') || letter == 'ё' || (letter >= 'a' && letter <= 'z');
        return valid;
    }

    private boolean isEnteredLetter(char ch){
        return errorChars.contains(ch) || word.maskContains(ch);
    }

    public boolean isEnglishLetter(char letter) {
        boolean valid = (letter >= 'a' && letter <= 'z');
        return valid;
    }

    private void monitor() {
        int hp = gameLevel.getMaxUserError() - countUserError;
        printHealth(hp);
        printErrorChars();
        printMask();
    }

    private void printUnluckyMessage(){
        System.out.println("Такой буквы нет !");
    }

    private void printLuckyMessage(char ch){
        System.out.println("Такая буква есть !");
        for(char c : word.getMask()){
            if(c == ch){
                System.out.print("\u001B[33m");
                System.out.print(c);
                System.out.print("\u001B[0m");
            }else{
                System.out.print(c);
            }
        }
        System.out.println();
    }

    public void printErrorChars() {
        ColorPrinter.printRedText("Неправильные буквы: " + errorChars.getLetters());
    }

    public void printMask(){
        ColorPrinter.printRedText(String.valueOf(word.getMask()));
    }

    public void printResultOfGame(boolean result){
        String str = word.getText().substring(0, 1).toUpperCase() + word.getText().substring(1);
        if(result){
            System.out.println("Вы выиграли !");
        }else{
            System.out.println("Вы проиграли :(");
        }
        System.out.print("Слово: ");
        ColorPrinter.printYellowText(str);
        System.out.println();
    }

    private void printHealth(int hp) {
        String s = "%-" + hp + "s";
        ColorPrinter.printRedText("Осталось жизней: " + String.format(s, "|").replace(' ', '|') + " " + hp);
    }
}