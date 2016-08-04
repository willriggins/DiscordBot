import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

/**
 * Created by will on 8/3/16.
 */
public class AnnotationListener {

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
            new MessageBuilder(TruthBot.client).withChannel(event.getMessage().getChannel()).withContent("Chris likes to play video games!").build();
        }
        else if (event.getMessage().getContent().equalsIgnoreCase("!truth2")) {
            new MessageBuilder(TruthBot.client).withChannel(event.getMessage().getChannel()).withContent("Kevin likes to eat sushi!").build();
        }
        else if (event.getMessage().getContent().equalsIgnoreCase("!truth3")) {
            new MessageBuilder(TruthBot.client).withChannel(event.getMessage().getChannel()).withContent(event.getMessage().getAuthor() + " , you're a real cunt!");
        }
    }
}


