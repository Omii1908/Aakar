package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Main driver class
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("\n\n\t\tE-LIBRARY PORTAL\n");

        // Initialize the database connection.
        DatabaseManager dbManager = new DatabaseManager();
        Connection con = dbManager.connect();

        // Create a shared BufferedReader and Scanner for user input.
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan = new Scanner(System.in);

        // Instantiate the various managers with required dependencies.
        BookManager bookManager = new BookManager(con, in, scan);
        MemberManager memberManager = new MemberManager(con, in, scan);
        IssueManager issueManager = new IssueManager(con, in, scan);
        RecommendationManager recManager = new RecommendationManager(con, in, scan);
        ExitManager exitManager = new ExitManager(scan);

        if (con == null) {
            System.out.println("\n\tDatabase connection failed. Some features will use proxy data.\n");
        } else {
            System.out.println("\n\tWELCOME TO THE E-LIBRARY MANAGEMENT SYSTEM!!\n");
        }

        // Main menu loop.
        while (true) {
            System.out.print("\n\t*HOME PAGE*\n"
                    + "1. Search a Book\n"
                    + "2. Recommend a Book\n"
                    + "3. Books Related Queries\n"
                    + "4. Members Related Queries\n"
                    + "5. Issue Book\n"
                    + "6. Return Book\n"
                    + "7. Exit\n"
                    + "[CHOICE]: ");
            int choice = scan.nextInt();
            scan.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    bookManager.searchBook();
                    break;
                case 2:
                    recManager.recommendBook();
                    break;
                case 3:
                    bookManager.bookQueries();
                    break;
                case 4:
                    memberManager.memberQueries();
                    break;
                case 5:
                    issueManager.issueBook();
                    break;
                case 6:
                    issueManager.returnBook();
                    break;
                case 7:
                    exitManager.closeApplication();
                    break;
                default:
                    System.out.println("\n\tWrong input!! Please re-enter the value.\n");
            }
        }
    }
}
