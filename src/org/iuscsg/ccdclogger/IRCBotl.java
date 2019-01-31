package org.iuscsg.ccdclogger;
import org.jibble.pircbot.*;

public class IRCBotl extends PircBot {
    private boolean exit = false;
    public IRCBotl() {
        this.setName("Logger-" + (int) (Math.random() * 500));
    }
    protected void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason)
    {
        if (recipientNick.equals(this.getNick()))
        {
            System.out.println("Bot was kicked from #events! Rejoining right away...");
            this.joinChannel("#events");
        }
    }

    public void setExit(boolean status)
    {
        exit = status;
    }

    protected void onDisconnect()
    {
        if (!exit)
        {
            System.out.println("Connection lost! Attempting to reconnect...");
            try
            {
                this.reconnect();
                this.joinChannel("#events");
            } catch (Exception e) {
                System.out.println("Failed to reconnect. Exiting...");
                System.exit(2);
            }
        }
    }
}
