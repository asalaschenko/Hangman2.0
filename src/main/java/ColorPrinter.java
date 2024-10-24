public class ColorPrinter {

    enum Color{
        RED("\u001B[31m"),
        YELLOW("\u001B[33m"),
        RED_BACKGROUND("\u001B[41m"),
        RESET("\u001B[0m");

        private final String code;

        Color(String code){
            this.code = code;
        }

        public String getCode(){
            return code;
        }
    }

    public static void printRedText(String text){
        System.out.println(Color.RED.getCode() + text + Color.RESET.getCode());
    }

    public static void printYellowText(String text){
        System.out.println(Color.YELLOW.getCode() + text + Color.RESET.getCode());
    }

    public static void printRedBackgroundText(String text){
        System.out.println(Color.RED_BACKGROUND.getCode() + text + Color.RESET.getCode());
    }

}
