package com.iuscsg;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	System.out.println("Who are you? Enter a name: ");
	String name = scan.next();
	System.out.println("What machine are you working on? This can be changed later");
	String machine = scan.next();
	System.out.println("################################################################################################");
	while(true) {
	    System.out.println("0. Log local user password change");
	    System.out.println("1. Log service password change");
	    System.out.println("2. Change machines");
	    System.out.println("3. Change username");
	    System.out.println("-1. Quit the program");
	    int option = scan.nextInt();

	    switch (option)
        {
            case 0: passwordLocal(); break;
            case 1: passwordService(); break;
            case 2: System.out.println("Enter the new machine name: "); machine = scan.next(); break;
            case 3: System.out.println("Enter a new name: "); name = scan.next(); break;
            default: System.exit(0);
        }
    }
    }

    public static void passwordLocal() {

    }

    public static void passwordService() {

    }
}
