import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class playHang extends ListenerAdapter {

    private final EventWaiter waiter;
    private int i;
    private boolean game = false;
    private HashMap<Integer, String> hangView = new HashMap<>();
    private String mysteryWord;
    private String gameOwner;


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
                textChannel.sendMessage("There is currently a game in progress hosted by " + gameOwner);
            }
            else {
                TextChannel tc = event.getChannel();
                String partyLeader = event.getAuthor().getId();
                System.out.println(partyLeader);
                event.getChannel().sendMessage("Hi, please DM me your word/phrase!").queue((message) ->
                        waiter.waitForEvent(PrivateMessageReceivedEvent.class, e ->
                        {
                            return e.getAuthor().getId().equals(partyLeader);
                        }, e -> sender(event, e.getMessage().getContentRaw(), textChannel, partyLeader), 15, TimeUnit.SECONDS, () -> event.getChannel().sendMessage("Took too long").queue()));
            }
            }


//        if(event.getMessage().getContentRaw().equals("e") && game == true && event.getChannel() == textChannel &&  !event.getAuthor().isBot())
//        {
//            textChannel.sendMessage("Welcome to Hangman! " + hangView.get(i) ).queue();
//        }
        if(event.getMessage().getContentRaw().equals(mysteryWord)&&  !event.getAuthor().isBot())
        {
            textChannel.sendMessage("You've correctly guessed the word " + mysteryWord + "!").queue();
            reset();
        }
    }

    public void sender(GuildMessageReceivedEvent event, String word, TextChannel t,String partyLeader) {
        game = true;
        t.sendMessage(word).queue();
        mysteryWord = word;
        gameOwner = partyLeader;

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
