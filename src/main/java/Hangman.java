//import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
//import net.dv8tion.jda.api.entities.TextChannel;
//import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
//import net.dv8tion.jda.api.hooks.ListenerAdapter;
//import org.w3c.dom.Text;
//
//import java.util.HashMap;
//import java.util.HashSet;
//
//public class Hangman extends ListenerAdapter {
//    private final GuildMessageReceivedEvent event;
//    private String mysteryWord;
//    private boolean onGoing;
//    private final int fails = 8;
//    private HashMap<Integer, String> hangView = new HashMap<>();
//    private TextChannel tc;
//    private String id;
//    private EventWaiter waiter;
//    private int i =0;
//
////    private HashSet<Character> bank = new HashSet<>();
//    public Hangman(String mysteryWord, Boolean onGoing, GuildMessageReceivedEvent event, String id, EventWaiter waiter)
//    {
//        this.mysteryWord = mysteryWord;
//        this.onGoing = onGoing;
//        hangView.put(0," ------\n" + "|     |\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------" );
//        hangView.put(1, " ------\n" + "|     |\n" + "|     0\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
//        hangView.put(2, " ------\n" + "|     |\n" + "|     0\n" + "|     +\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------\n");
//        hangView.put(3, " ------\n" + "|     |\n" + "|     0\n" + "|    -+\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
//        hangView.put(4," ------\n" + "|     |\n" + "|     0\n" + "|    -+-\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
//        hangView.put(5, " ------\n" + "|     |\n" + "|     0\n" + "|   /-+-\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
//        hangView.put(6, " ------\n" + "|     |\n" + "|     0\n" + "|   /-+-/\n" + "|\n" + "|\n" + "|\n" + "|\n" + "----------");
//        hangView.put(7, " ------\n" + "|     |\n" + "|     0\n" + "|   /-+-/\n" + "|     |\n" + "|\n" + "|\n" + "|\n" + "----------");
//        this.event = event;
//        this.tc = tc;
//        tc = event.getChannel();
//        this.id = id;
//        this.waiter = waiter;
//    }
////    @Override
////    public void onGuildMessageReceived(GuildMessageReceivedEvent e)
////    {
////        if (e.getChannel() == event.getGuild().getTextChannelsByName("hangman", true).get(0) && onGoing == true && )
////        {
////
////        }
//////        else
//////        {
//////
//////        }
////    }
//
//
//    public String getMystertyWord()
//    {
//        return mysteryWord;
//    }
//
//    public Boolean getonGoing()
//    {
//        return onGoing;
//    }
//
//    public void playHangman()
//    {
//        TextChannel textChannel = event.getGuild().getTextChannelsByName("hangman",true).get(0);
//        HashSet<Character> bank = new HashSet<>();
//        int i =0; //The amount of fails
////        tc.sendMessage(hangView.get(0)).queue();
//
//            textChannel.sendMessage("Welcome to Hangman \n " + hangView.get(i)).queue((message) ->
//                    waiter.waitForEvent(GuildMessageReceivedEvent.class,
//                            e->
//                            {
//                                if(e.getAuthor().getId().equals(id) || textChannel != e.getChannel() || e.getMessage().getContentRaw().length()>1)
//                                    return false;
//                                else
//                                    return true;
//                            },
//                            e->
//                            {
//                                if(bank.contains(e.getMessage().getContentRaw().charAt(0)))
//                                {
//                                    textChannel.sendMessage("This letter has already been used").queue();
//
//                                }
//                                if(!bank.contains(e.getMessage().getContentRaw()))
//                                {
//                                    bank.add(e.getMessage().getContentRaw().charAt(0));
//                                }
//
//                                textChannel.sendMessage("NOT from partyleader").queue();
//
//                            }
//            ));
//
//
//    }
//
//}
