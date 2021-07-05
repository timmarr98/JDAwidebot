import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class playHang extends ListenerAdapter {

    private final EventWaiter waiter;
    private int i;
    private boolean game = false;
    private HashMap<Integer, String> hangView = new HashMap<>();
    private HashSet<Character> wordChars = new HashSet<>();
    private HashSet<Character> bank = new HashSet<>();
    private String mysteryWord;
    private String gameOwner;
    private char[] charArr = new char[mysteryWord.length()];


    public playHang(EventWaiter waiter) {
        this.waiter = waiter;
        game = false;
        i = 0;
        hangView.put(0," ------\n" + "|     |\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------" );
        hangView.put(1, " ------\n" + "|     |\n" + "|     0\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
        hangView.put(2, " ------\n" + "|     |\n" + "|     0\n" + "|     +\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------\n");
        hangView.put(3, " ------\n" + "|     |\n" + "|     0\n" + "|    -+\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
        hangView.put(4," ------\n" + "|     |\n" + "|     0\n" + "|    -+-\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
        hangView.put(5, " ------\n" + "|     |\n" + "|     0\n" + "|   /-+-\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
        hangView.put(6, " ------\n" + "|     |\n" + "|     0\n" + "|   /-+-/\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
        hangView.put(7, " ------\n" + "|     |\n" + "|     0\n" + "|   /-+-/\n" + "|     |\n" + "|\n" + "|\n" + "|\n" + "----------");
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        TextChannel textChannel = event.getGuild().getTextChannelsByName("hangman", true).get(0);

        if (event.getMessage().getContentRaw().equals("!hangman")) {
            if(game == true)
            {
                textChannel.sendMessage("There is currently a game in progress hosted by " + gameOwner).queue();
            }
            else {
                TextChannel tc = event.getChannel();
                String partyLeader = event.getAuthor().getId();
                System.out.println(partyLeader);
                event.getChannel().sendMessage("Hi, please DM me your word/phrase!").queue((message) ->
                        waiter.waitForEvent(PrivateMessageReceivedEvent.class, e ->
                        {
                            return e.getAuthor().getId().equals(partyLeader);
                        }, e -> sender(event, e.getMessage().getContentRaw(), textChannel, e.getAuthor().getName()), 15, TimeUnit.SECONDS, () -> event.getChannel().sendMessage("Took too long").queue()));
            }
            }
        if(event.getChannel() == textChannel && game == true && !event.getAuthor().isBot()) {

            if (event.getMessage().getContentRaw().equals(mysteryWord)) {
                textChannel.sendMessage("You've correctly guessed the word " + mysteryWord + "!").queue();
                reset();
            }
            if(event.getMessage().getContentRaw().length() ==1)
            {
                char letter = event.getMessage().getContentRaw().charAt(0);
                if(bank.contains(letter))
                {
                    System.out.println(letter);
                    textChannel.sendMessage("This letter has already been used, check the bank!").queue();
                    i++;
                }
                bank.add(letter);
                if(wordChars.contains(letter))
                {
                    textChannel.sendMessage(hangView.get(i));
                    textChannel.sendMessage(hangView.get(i) +"\n"+"Letter "+ letter + " is in the word!").queue();

                }
                if(!wordChars.contains(letter))
                {
                    i++;
                    textChannel.sendMessage("Letter " + letter + " is NOT a valid letter").queue()
                }
                if(mysteryWord.equals(new String(charArr)))
                {
                    textChannel.sendMessage("The word has been discovered! The word was " + mysteryWord);
                }


            }
        }
    }

    public void sender(GuildMessageReceivedEvent event, String word, TextChannel t,String partyLeader) {
        game = true;

        t.sendMessage("RECEIVED").queue();
        mysteryWord = word;
        for (int x=0; x<word.length();x++)
        {
            if(!wordChars.contains(mysteryWord.charAt(i)))
            {
                wordChars.add(mysteryWord.charAt(i));
            }
        }
        gameOwner = partyLeader;
        Arrays.fill(charArr, '_');
        System.out.println

    }

    public void reset(){
        game = false;
        mysteryWord = "";
        i =0;

    }

//    @Override
//    public void onMessageReceived(MessageReceivedEvent e)
//    {
//        if(e.getMessage().getContentRaw().equals("e") && game == true )
//        {
//            System.out.println("2sdsd2dsd");
//        }
//    }
}
//   public void onMessage
//
//}
