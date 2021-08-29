import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import java.awt.Color;
import javax.security.auth.login.LoginException;


//@SuppressWarnings("deprecation")
public class Main extends ListenerAdapter {
    private final EventWaiter waiter = new EventWaiter();

    public static void main(String[] args) throws LoginException, InterruptedException {
        try
        {
            new Main().start();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }


    }
    public  void start()
    {
        try {
            String token = "  ";
            JDABuilder.createDefault(token).enableIntents(GatewayIntent.GUILD_MEMBERS,GatewayIntent.DIRECT_MESSAGES).addEventListeners(waiter, new playHang(waiter),new Main(), new Profile()).build().awaitReady();
        }
        catch(LoginException err)
        {
            err.printStackTrace();
        }
        catch(InterruptedException err)
        {
            err.printStackTrace();
        }
    }
    public EventWaiter getEventWaiter()
    {
        return waiter;
    }
    @Override
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event)
    {
        System.out.println(event.getAuthor().getId() + " from private message");
    }
    public boolean check(PrivateMessageReceivedEvent event)
    {
        System.out.print("check check check");
        return false;
    }
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        EmbedBuilder embd = new EmbedBuilder();
        if(event.getAuthor().isBot()) return;
        System.out.println("Message from " + event.getAuthor().getName());
        if(event.getMessage().getContentRaw().equals("!help"))
        {
            embd.setColor(Color.PINK);
            embd.setTitle("InfoBot Help Commands");
            embd.addField("!profile", "Allows users to pull up their personalized profile or another user's.", false );
            embd.addField("!name", "Set name", true);
            embd.addField("!age", "Set age", true);
            embd.addField("!bio", "Set bio",true);
            event.getChannel().sendMessage(embd.build()).queue();
        }

    }
}
