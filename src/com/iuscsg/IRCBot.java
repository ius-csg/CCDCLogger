package com.iuscsg;
import org.jibble.pircbot.*;

public class IRCBot extends PircBot{
    public IRCBot(String name) {
        this.setName("Logger-" + (int) (Math.random() * 100));
    }
}
