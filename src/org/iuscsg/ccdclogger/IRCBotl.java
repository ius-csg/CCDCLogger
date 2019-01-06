package org.iuscsg.ccdclogger;
import org.jibble.pircbot.*;

public class IRCBotl extends PircBot{
    public IRCBotl() {
        this.setName("Logger-" + (int) (Math.random() * 500));
    }
}
