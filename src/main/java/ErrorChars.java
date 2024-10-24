import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ErrorChars {

    private List<Character> errorLetter;

    public ErrorChars(){
        errorLetter = new ArrayList<>();
    }

    public void append(char ch){
        errorLetter.add(ch);
    }

    public boolean contains(char ch){
        return errorLetter.contains(ch);
    }

    public String getLetters(){
        return errorLetter.stream().map(Object::toString).sorted().collect(Collectors.joining(","));
    }
}
