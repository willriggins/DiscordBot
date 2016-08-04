import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.obj.IMessage;

/**
 * Created by will on 8/3/16.
 */
public class HelloEvent extends Event {
    private final IMessage message;

    public HelloEvent(IMessage message) {
        this.message = message;
    }

    public IMessage getMessage() {
        return message;
    }
}
