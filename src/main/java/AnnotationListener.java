import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.util.*;

/**
 * Created by will on 8/3/16.
 */
public class AnnotationListener {
    private static MessageBuilder builder = new MessageBuilder(TruthBot.client);


    @EventSubscriber
    public void onReady(ReadyEvent event) {

        try {
            TruthBot.client.changeUsername("Truth Bot");
        } catch (RateLimitException | DiscordException e) {
            e.printStackTrace();
        }

    }

    @EventSubscriber
    public void onMessage(MessageReceivedEvent event) {
        if (event.getMessage().getContent().toLowerCase().contains("hello"))
            event.getClient().getDispatcher().dispatch(new HelloEvent(event.getMessage()));
    }

    @EventSubscriber
    public void onHelloMessage(HelloEvent event) {
        System.out.println(event.getMessage().getAuthor().getName() + " said hello!");
    }

    @EventSubscriber
    public void mockMessage(MessageReceivedEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {
        if (event.getMessage().getContent().equalsIgnoreCase("!truth")) {
            builder.withChannel(event.getMessage().getChannel()).withContent("Chris likes to play video games!").build();
        }
        else if (event.getMessage().getContent().equalsIgnoreCase("!truth2")) {
            builder.withChannel(event.getMessage().getChannel()).withContent("Kevin likes to eat sushi!").build();
        }
        else if (event.getMessage().getContent().equalsIgnoreCase("!truth3")) {
            builder.withChannel(event.getMessage().getChannel()).withContent("You're a real winner, " + event.getMessage().getAuthor().getName() + "!").build();
        }
        else if (event.getMessage().getContent().equalsIgnoreCase("!test1")) {
            for (int i = 1; i < 11; i++) {
                final int i2 = i;
                RequestBuffer.request(() -> {
                    try {
                        builder.withContent("Eat " + i2 + " cookies.").build();
                    } catch (DiscordException | MissingPermissionsException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
            }
        }
       // want to be able to pass in a name after the command and make it emote on that person






    }

}


