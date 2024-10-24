

public class Main {
    public static void main(String[] args) throws Exception {
        Menu menu = new Menu("russian_nouns.txt");
        menu.start();
    }

    public static <T extends Exception> void printTraceInfo(T e) {
        ColorPrinter.printRedBackgroundText(e.getMessage());
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        ColorPrinter.printRedBackgroundText("in " + stackTraceElements[2].getClassName() + " method " + stackTraceElements[2].getMethodName());
    }
}