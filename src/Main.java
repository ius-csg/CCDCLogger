import java.util.Scanner;

public class Main {
    private static String name;
    private static String machine;
    private static IRCBot bot = new IRCBot();
    private static Scanner scan = new Scanner(System.in);
    private static String chan = "#events";

    public static void main(String[] args) {
        bot.setVerbose(true); // Remove me later
        System.out.print("Who are you? Enter a name: ");
        name = scan.next();
        System.out.print("What machine are you working on? This can be changed later: ");
        machine = scan.next();
        System.out.print("Enter an IRC server address without port number: ");
        String server = scan.next();
        System.out.print("Enter the port number, or press enter for the default (6667): ");
        String port = scan.nextLine();
        try {
            System.out.println("Connecting...");
            if (port.isEmpty()) {
                bot.connect(server);
            } else {
                bot.connect(server, Integer.parseInt(port));
            }
        } catch (Exception e) {
            System.out.println("Unable to connect to IRC server! Check your server settings, and make sure your nick is not in use.");
            System.exit(1);
        }
        bot.joinChannel(chan);
        System.out.println("Connected to IRC using nick " + bot.getNick());
        System.out.println("################################################################################################");
        while(true) {
            System.out.println("0. Log local user password change");
            System.out.println("1. Log service password change");
            System.out.println("2. Other event");
            System.out.println("3. Change machines (Current: " + machine + ")");
            System.out.println("4. Change name (Current: " + name + ")");
            System.out.println("5. Change IRC bot nick (Current: " + bot.getNick() + ")");
            System.out.println("-1. Quit the program");
            int option = scan.nextInt();

            switch (option)
            {
                case 0: passwordLocal(); break;
                case 1: passwordService(); break;
                case 2:
                    otherEvent();
                    break;
                case 3:
                    System.out.print("Enter the new machine name: ");
                    machine = scan.next();
                    break;
                case 4:
                    System.out.print("Enter a new name: ");
                    name = scan.next();
                    break;
                case 5:
                    System.out.print("Enter a new nick: ");
                    bot.changeNick(scan.next());
                    break;
                default: bot.quitServer("Exited logger"); System.exit(0);
            }
        }

    }

    public static void passwordLocal() {
        System.out.print("Enter the username: ");
        String username = scan.next();
        bot.sendMessage(chan, "Local user password for " + username + " changed on " + machine + " by " + name);

    }

    public static void passwordService() {
        System.out.print("Enter a service name: ");
        String service = scan.next();
        System.out.print("Enter a username: ");
        String username = scan.next();
        bot.sendMessage(chan, "Service " + service + " user password for " + username + " changed on " + machine + " by " + name);

    }

    public static void otherEvent() {
        System.out.print("What did you do?: ");
        bot.sendMessage(chan, "Custom event: " + scan.next() + " changed on " + machine + " by " + name);
    }


}
