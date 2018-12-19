import org.jibble.pircbot.*;

public class IRCBot extends PircBot{
    public IRCBot() {
        this.setName("Logger-" + (int) (Math.random() * 500));
    }
}
