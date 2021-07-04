import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class playHang extends ListenerAdapter {

    private final EventWaiter waiter;


    public playHang(EventWaiter waiter)
    {
        this.waiter = waiter;
    }

   @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
   {
       if(event.getMessage().getContentRaw().equals("!hangman"))
       {
           TextChannel tc = event.getChannel();
           String partyLeader = event.getAuthor().getId();
           System.out.println(partyLeader);
           event.getChannel().sendMessage("Hi, please DM me your word/phrase!").queue((message) ->
                   waiter.waitForEvent(PrivateMessageReceivedEvent.class, e ->
                   {

                       return e.getAuthor().getId().equals(partyLeader);
                   }, e -> tc.sendMessage(e.getMessage().getContentRaw()).queue(),7, TimeUnit.SECONDS,() -> event.getChannel().sendMessage("Took too long").queue()));
       }
   }

}
