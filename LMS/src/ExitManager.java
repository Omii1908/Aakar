package src;

import java.util.Scanner;

public class ExitManager {
    private Scanner scan;

    public ExitManager(Scanner scan) {
        this.scan = scan;
    }

    public void closeApplication() {
        System.out.print("\n\n\t**EXIT MENU**\nDo you really want to exit?\n1. Yes\n2. No\n[CHOICE]: ");
        int choice = scan.nextInt();
        if (choice == 1) {
            System.out.println("\nClosing connection...\n\nTHANK YOU & HAVE A NICE DAY!!");
            System.exit(0);
        } else {
            System.out.println("\nReturning to main menu...\n");
        }
    }
}
