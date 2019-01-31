package org.iuscsg.ccdclogger;

import java.util.Scanner;

public class Main {
    private static String name;
    private static String machine;
    private static IRCBotl bot = new IRCBotl();
    private static Scanner scan = new Scanner(System.in);
    private static String chan = "#events";
    

    public static void main(String[] args) {
       // bot.setVerbose(true); // testing
        System.out.print("Who are you? Enter a name: ");
        name = scan.nextLine();
        System.out.print("What machine are you working on? This can be changed later: ");
        machine = scan.nextLine();
        System.out.print("Enter an IRC server address without port number (using 6667): ");
        bot.setAutoNickChange(true);
        String server = scan.nextLine();
        try {
            System.out.println("Connecting...");
            bot.connect(server);
        } catch (Exception e) {
            System.out.println(
                    "Unable to connect to IRC server! Check your server settings, and try again later.");
            System.exit(1);
        }
        bot.joinChannel(chan);
        System.out.println("Connected to IRC using nick " + bot.getNick());
        System.out.println(
                "################################################################################################");
        while (true) {
            System.out.println("0. Log local user password change");
            System.out.println("1. Log service password change");
            System.out.println("2. Other event");
            System.out.println("3. Change machines (Current: " + machine + ")");
            System.out.println("4. Change name (Current: " + name + ")");
            System.out.println("5. Change IRC bot nick (Current: " + bot.getNick() + ")");
            System.out.println("6. Scrub server");
            System.out.println("-1. Quit the program");
            int option = Integer.parseInt(scan.nextLine());

            switch (option) {
            case 0:
                passwordLocal();
                break;
            case 1:
                passwordService();
                break;
            case 2:
                otherEvent();
                break;
            case 3:
                System.out.print("Enter the new machine name: ");
                machine = scan.nextLine();
                break;
            case 4:
                System.out.print("Enter a new name: ");
                name = scan.nextLine();
                break;
            case 5:
                System.out.print("Enter a new nick: ");
                bot.changeNick(scan.nextLine().replaceAll("\\s", ""));
                break;
            case 6:
            bot.sendMessage(chan, "[" + machine + "] [" + name + "] Scrub started :(");
            break;
            case -1:
                bot.setExit(true);
                bot.quitServer("Exited logger");
                System.exit(0);
            default:
                continue;
            }
        }

    }

    public static void passwordLocal() {
        System.out.print("Enter the username: ");
        String username = scan.nextLine();
        System.out.print("Enter the new password: ");
        String pass = scan.nextLine();
        bot.sendMessage(chan,
                "[" + machine + "] [" + name + "] Local user password for " + username + " changed to " + pass);

    }

    public static void passwordService() {
        System.out.print("Enter a service name: ");
        String service = scan.nextLine();
        System.out.print("Enter a username: ");
        String username = scan.nextLine();
        System.out.print("Enter the new password: ");
        String pass = scan.nextLine();
        bot.sendMessage(chan, "[" + machine + "] [" + name + "] [" + service + "] user password for " + username + " changed to " + pass);

    }

    public static void otherEvent() {
        System.out.print("What did you do now?: ");
        String event = scan.nextLine();
        bot.sendMessage(chan, "[" + machine + "] [" + name + "] Other: " + event);
    }

}
