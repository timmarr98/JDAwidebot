import java.util.HashMap;
import java.util.HashSet;

public class Hangman {
    private String mysteryWord;
    private boolean onGoing;
    private final int fails = 8;
    HashMap<Integer, String> hangView = new HashMap<>();

    public Hangman(String mysteryWord, Boolean onGoing)
    {
        this.mysteryWord = mysteryWord;
        this.onGoing = onGoing;
        hangView.put(0," ------\n" + "|     |\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------" );
        hangView.put(1, " ------\n" + "|     |\n" + "|     0\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
        hangView.put(2, " ------\n" + "|     |\n" + "|     0\n" + "|     +\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------\n");
        hangView.put(3, " ------\n" + "|     |\n" + "|     0\n" + "|    -+\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
        hangView.put(4," ------\n" + "|     |\n" + "|     0\n" + "|    -+-\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
        hangView.put(5, " ------\n" + "|     |\n" + "|     0\n" + "|   /-+-\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
        hangView.put(6, " ------\n" + "|     |\n" + "|     0\n" + "|   /-+-/\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
        hangView.put(7, " ------\n" + "|     |\n" + "|     0\n" + "|   /-+-/\n" + "|     |\n" + "|\n" + "|\n" + "|\n" + "----------");
    }

//    private void initGame(String mysteryWord)
//    {
//
//    }

    public String getMystertyWord()
    {
        return mysteryWord;
    }

    public Boolean getonGoing()
    {
        return onGoing;
    }

    public void playHangman()
    {
        onGoing = true;
        HashSet<Character> mysteryChars = new HashSet<>();
        for(int i=0; i< mysteryWord.length(); i++)
        {
            mysteryChars.add(mysteryWord.charAt(i));
        }
        int tries = 0;
        while(tries < fails)
        {

        }
    }
}
