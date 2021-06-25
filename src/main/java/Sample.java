import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Sample extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event)
    {
        if(event.getAuthor().isBot()) return;
        System.out.println("Message from " + event.getAuthor().getName());
        if(event.getMessage().getContentRaw().equals("!hell"))
        {
            event.getChannel().sendMessage("lol").queue();
        }

        }
    }
//    public static void main(String[] args) {
//
//
//    }



