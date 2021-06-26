import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.awt.*;

public class Profile extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        EmbedBuilder embd = new EmbedBuilder();
        if(event.getAuthor().isBot()) return;
        System.out.println("Message from " + event.getAuthor().getName());
        if(event.getMessage().getContentRaw().equals("!profile"))
        {
            /*
            Embedded profile that users can call for other people
             */
            embd.setColor(Color.cyan);
            embd.setTitle(event.getAuthor().getName());
            embd.setThumbnail(event.getAuthor().getAvatarUrl());
            event.getChannel().sendMessage(embd.build()).queue();
            //call a method/function that gets the info from a json file to show the user's profile
            /*
               The profile will contain the user's preferred name, age, role
             */
            String user_id = event.getAuthor().getId();

        }
        if(event.getMessage().getContentRaw().contains("!profile") && event.getMessage().getMentionedMembers().size() ==1)
        {
            embd.setColor(Color.cyan);
            embd.setTitle(event.getMessage().getMentionedMembers().get(0).getNickname());
            embd.setThumbnail(event.getMessage().getMentionedMembers().get(0).getUser().getAvatarUrl());
//            System.out.println(event.getMessage().getMentionedMembers().get(0).getUser().getAvatarUrl());
            event.getChannel().sendMessage(embd.build()).queue();
        }

        }

    }
//    public static void main(String[] args) {
//
//
//    }



