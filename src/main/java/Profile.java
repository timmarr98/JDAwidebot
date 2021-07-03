import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import javax.security.auth.login.LoginException;
import java.awt.*;
import java.net.UnknownHostException;

public class Profile extends ListenerAdapter {
    MongoDB newnew = new MongoDB();
    /*
        Will add every new member of the server to the database with some static fields
     */
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event)
    {
        String user_id = event.getMember().getId();
        Member user = event.getMember();
        try {
            newnew.addToDatabase(user_id, user.getEffectiveName(),user.getUser().getDiscriminator(),"null");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    /**
     * Checks for user messages of '!profile' which returns an embedded
     * display of the profile being mentioned, or the user's profile.
     * @param event
     */
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

            String user_id = event.getAuthor().getId();


            try {
                if(newnew.doesExist(user_id) == false )
                {
                    newnew.addToDatabase(user_id, "null", "null", "null");
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            embd.setColor(Color.cyan);
            embd.setTitle(newnew.userInfo(user_id)[0]);
            embd.setDescription((newnew.userInfo(user_id)[1]));
            embd.setFooter("InfoBot Message");
            embd.addField("Age",newnew.userInfo(user_id)[2],true);
            embd.setThumbnail(event.getAuthor().getAvatarUrl());
            event.getChannel().sendMessage(embd.build()).queue();

            //call a method/function that gets the info from a json file to show the user's profile
            /*
               The profile will contain the user's preferred name, age, role
             */

        }

        if(event.getMessage().getContentRaw().contains("!profile") && event.getMessage().getMentionedMembers().size() ==1)
        {
            String user_id = event.getMessage().getMentionedMembers().get(0).getId();
            String [] userinfo = new String[3];


            try {
                if(newnew.doesExist(user_id) == false )
                {
                    newnew.addToDatabase(user_id, "null", "null", "null");
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            userinfo = newnew.userInfo(user_id);

            embd.setColor(Color.cyan);
            embd.setTitle(userinfo[0]);
            embd.setThumbnail(event.getMessage().getMentionedMembers().get(0).getUser().getAvatarUrl());
            embd.setDescription(userinfo[1]);
            embd.setFooter("InfoBot Message");
            embd.addField("Age",userinfo[2],true);//            System.out.println(event.getMessage().getMentionedMembers().get(0).getUser().getAvatarUrl());
            event.getChannel().sendMessage(embd.build()).queue();


        }
        if(event.getMessage().getContentRaw().contains("!bio"))
        {
            String user_id = event.getAuthor().getId();
            newnew.bio(user_id, event.getMessage().getContentRaw().substring(4));
        }

        if(event.getMessage().getContentRaw().contains("!age"))
        {
            String user_id = event.getAuthor().getId();
            newnew.age(user_id, event.getMessage().getContentRaw().substring(4));

        }
        if(event.getMessage().getContentRaw().contains("!name"))
        {
            String user_id = event.getAuthor().getId();
            newnew.name(user_id, event.getMessage().getContentRaw().substring(5));

        }
        }

    }
