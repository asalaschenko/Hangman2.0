import java.util.Arrays;

public class Word {

    private String text;
    private char[] mask;
    private final char MASK_SYMBOL = '-';

    Word(String text){
        this.text = text;
        mask = new char[text.length()];
        Arrays.fill(mask, MASK_SYMBOL);
    }

    public void openLetter(char ch)throws Exception{
        boolean isInside = false;

        for(int i = 0; i<text.length(); i++) {
            if(text.charAt(i) == ch) {
                mask[i] = ch;
                isInside = true;
            }
        }
        if(!isInside){
            throw new WordOpenLetterException("Call " + Thread.currentThread().getStackTrace()[1].getMethodName() + "() of " + this.getClass() + " : ch '" + ch + "' is not inside the word");
        }
    }

    public boolean maskContains(char letter){
        boolean isInside = String.valueOf(mask).contains(String.valueOf(letter));
        return isInside;
    }

    public boolean textContains(char letter){
        boolean isInside = text.contains(String.valueOf(letter));
        return isInside;
    }

    public boolean isUnopenedLetters(){
        return String.valueOf(mask).contains("-");
    }

    public String getText(){
        return text;
    }

    public char[] getMask(){
        return mask;
    }
}
