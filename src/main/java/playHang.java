import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/**
 * playHang allows users to play the Hangman game. The user has to prompt the bot with
 * '!hangman' in order to initiate the game.
 *  STEP 1: !hangman
 *  STEP 2: Direct Message Infobot with the phrase or word to be guessed
 *  STEP 3: Users input letters or full phrases that was given by the game owner
 */
public class playHang extends ListenerAdapter {

    private final EventWaiter waiter;
    private int i;
    private boolean game = false;
    private HashMap<Integer, String> hangView = new HashMap<>();
    private HashSet<Character> wordChars = new HashSet<>();
    private HashSet<Character> bank = new HashSet<>();
    private String mysteryWord;
    private String gameOwner;
    private char[] charArr;
    private int fails = 8;


    public playHang(EventWaiter waiter) {
        this.waiter = waiter;
        game = false;
        i = 0;
        //hangView is a static HashMap that displays the hangman text
        hangView.put(0, " ------\n" + "|     |\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
        hangView.put(1, " ------\n" + "|     |\n" + "|     0\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
        hangView.put(2, " ------\n" + "|     |\n" + "|     0\n" + "|     +\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------\n");
        hangView.put(3, " ------\n" + "|     |\n" + "|     0\n" + "|    -+\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
        hangView.put(4, " ------\n" + "|     |\n" + "|     0\n" + "|    -+-\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
        hangView.put(5, " ------\n" + "|     |\n" + "|     0\n" + "|   /-+-\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
        hangView.put(6, " ------\n" + "|     |\n" + "|     0\n" + "|   /-+-/\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
        hangView.put(7, " ------\n" + "|     |\n" + "|     0\n" + "|   /-+-/\n" + "|     |\n" + "|\n" + "|\n" + "|\n" + "----------");
        hangView.put(8, "  ------\n" + "|     |\n" + "|     0\n" + "|   /-+-/\n" + "|     |\n" + "|     |\n" + "|\n" + "|\n" + "----------");
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        TextChannel textChannel = event.getGuild().getTextChannelsByName("hangman", true).get(0);

        if (event.getMessage().getContentRaw().equals("!hangman")) {
            if (game == true) {
                textChannel.sendMessage("There is currently a game in progress hosted by " + gameOwner).queue();
            } else {
                TextChannel tc = event.getChannel();
                String partyLeader = event.getAuthor().getId();
                System.out.println(partyLeader);
                event.getChannel().sendMessage("Hi, please DM me your word/phrase!").queue((message) ->
                        waiter.waitForEvent(PrivateMessageReceivedEvent.class, e ->
                        {
                            return e.getAuthor().getId().equals(partyLeader);
                        }, e -> sender(event, e.getMessage().getContentRaw(), textChannel, e.getAuthor().getId()), 15, TimeUnit.SECONDS, () -> event.getChannel().sendMessage("Took too long").queue()));
            }
        }
        if (event.getChannel() == textChannel && game == true && !event.getAuthor().isBot() && !event.getAuthor().getId().equals(gameOwner)) {

            if (event.getMessage().getContentRaw().equals(mysteryWord)) {
                textChannel.sendMessage("You've correctly guessed the word " + mysteryWord + "!").queue();
                reset();
            }
            if (event.getMessage().getContentRaw().length() == 1) {
                char letter = event.getMessage().getContentRaw().charAt(0);
                if (bank.contains(letter)) {
                    System.out.println(letter);
                    textChannel.sendMessage("This letter has already been used, check the bank!").queue();
                    i++;
                }
                bank.add(letter);
                if (wordChars.contains(letter)) {
                    textChannel.sendMessage(hangView.get(i));
                    textChannel.sendMessage(hangView.get(i) + "\n" + "Letter " + letter + " is in the word! "+" FAILS: "+ i + "\n").queue();
                    for (int i = 0; i < mysteryWord.length(); i++) {
                        if (mysteryWord.charAt(i) == letter) {
                            charArr[i] = letter;
                        }
                    }
                    textChannel.sendMessage(String.copyValueOf(charArr)).queue();
                } else if (!wordChars.contains(letter)) {
                    i++;
                    textChannel.sendMessage(hangView.get(i)).queue();
                    textChannel.sendMessage("Letter " + letter + " is NOT a valid letter "  + "  FAILS: " + i).queue();
                    textChannel.sendMessage(String.copyValueOf(charArr)).queue();
                    if (i >= fails) {
                        textChannel.sendMessage("Sorry you have over 8 fails. Type '!hangman' to play again").queue();
                        reset();
                    }
                }
                if (mysteryWord.equals(String.copyValueOf(charArr))) {
                    textChannel.sendMessage("The word has been discovered! The word was " + mysteryWord).queue();
                    reset();
                }


            }
        }
    }

    /**
     * Start up the hangman game
     * @param event passing the event details
     * @param word the word that needs to be guessed
     * @param t the textChannel that the bot will be sending messages into
     * @param partyLeader the owner of the hangman game (should not be able to play)
     */
    public void sender(GuildMessageReceivedEvent event, String word, TextChannel t, String partyLeader) {
        game = true;

        t.sendMessage("RECEIVED").queue();

        mysteryWord = word;
        for (int x = 0; x < word.length(); x++) {
            if (!wordChars.contains(mysteryWord.charAt(x))) {
                wordChars.add(mysteryWord.charAt(x));
            }
        }
        gameOwner = partyLeader;
        charArr = new char[mysteryWord.length()];
        for(int y=0; y<charArr.length; y++)
        {
            if(mysteryWord.charAt(y) != ' ')
            {
                charArr[y] = '-';
            }
            else
            {
                charArr[y] = ' ';
            }
        }
//        Arrays.fill(charArr, '-');
        t.sendMessage(hangView.get(i)).queue();
        t.sendMessage(String.copyValueOf(charArr) + "  FAILS: " + i).queue();
    }

    /**
     * Reset all the game states, ready for another game
     */
    public void reset() {
        game = false;
        mysteryWord = "";
        i = 0;
        gameOwner = "";
        wordChars.clear();
        bank.clear();
    }
}