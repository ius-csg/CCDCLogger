package com.iuscsg;

import org.jibble.pircbot.*;

import java.util.Scanner;

public class Main {
    private static String name;
    private static String machine;
    private static IRCBot bot;
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Who are you? Enter a name: ");
        name = scan.next();
        System.out.println("What machine are you working on? This can be changed later");
        machine = scan.next();

        IRCBot bot = new IRCBot(name);
        // bot.connect(OURSERVER);
        bot.joinChannel("#events");
        System.out.println("################################################################################################");
        while(true) {
            System.out.println("0. Log local user password change");
            System.out.println("1. Log service password change");
            System.out.println("2. Change machines (Current: " + machine + ")");
            System.out.println("3. Change name (Current: " + name + ")");
            System.out.println("4. Change IRC bot nick (Current: " + bot.getNick() + ")");
            System.out.println("-1. Quit the program");
            int option = scan.nextInt();

            switch (option)
            {
                case 0: passwordLocal(); break;
                case 1: passwordService(); break;
                case 2: System.out.println("Enter the new machine name: "); machine = scan.next(); break;
                case 3: System.out.println("Enter a new name: "); name = scan.next();  break;
                case 4: System.out.println("Enter a new nick: "); bot.changeNick(scan.next()); break;
                default: bot.quitServer("Exited logger"); System.exit(0);
            }
        }

    }

    public static void passwordLocal() {
        System.out.println("Enter the username: ");
        String username = scan.next();
        bot.sendMessage("#event", "Local user password for " + username + " changed on " + machine + " by " + name);

    }

    public static void passwordService() {
        System.out.println("Enter a service name: ");
        String service = scan.next();
        System.out.println("Enter a username: ");
        String username = scan.next();
        bot.sendMessage("#event", "Service " + service + " user password for " + username + " changed on " + machine + " by " + name);

    }


}
