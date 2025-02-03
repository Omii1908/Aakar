package src;

import java.io.BufferedReader;
import java.sql.*;
import java.util.Scanner;

public class BookManager {
    private Connection con;
    private BufferedReader in;
    private Scanner scan;

    public BookManager(Connection con, BufferedReader in, Scanner scan) {
        this.con = con;
        this.in = in;
        this.scan = scan;
    }

    // Search for a book by name.
    public void searchBook() {
        if (con == null) {
            System.out.println("\n\tDatabase connection not available. This feature requires a live connection.\n");
            return;
        }
        try {
            System.out.print("\n\t**Searching Tab**\nEnter the name of book: ");
            String search = in.readLine();

            String query = "SELECT * FROM book_details WHERE BOOK_NAME = ? AND STATUS = 'Y' ORDER BY accession";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, search);
            ResultSet rs = pst.executeQuery();

            int count = 0;
            while (rs.next()) {
                if (count == 0) {
                    System.out.println("\n\t**Book(s) Available**");
                    System.out.println("\tAcc no. \tBook Name \tAuthor Name \tPublication \tGenre \tEdition \tYear");
                }
                System.out.println("\t" + rs.getInt("accession") + "\t" + rs.getString("book_name") +
                        "\t" + rs.getString("author_name") + "\t" + rs.getString("publication") +
                        "\t" + rs.getString("genre") + "\t" + rs.getString("edition") +
                        "\t" + rs.getString("year_of_publish"));
                count++;
            }
            rs.close();
            pst.close();
            if (count == 0) {
                System.out.println("\n\tNo Books Available\n");
                return;
            }
            System.out.print("\nDo you want to issue the book?\n1. Yes\n2. No\n[CHOICE]: ");
            int choice = Integer.parseInt(in.readLine());
            if (choice == 1) {
                System.out.println("\n\tPlease use the Issue Book option from the main menu.\n");
            }
        } catch (Exception e) {
            System.out.println(e + "\n\tError in searching book. Try again.\n");
        }
    }

    // Books related queries menu.
    public void bookQueries() {
        if (con == null) {
            System.out.println("\n\tDatabase connection not available. This feature requires a live connection.\n");
            return;
        }
        while (true) {
            System.out.print("\n\n\t**BOOKS RELATED QUERIES**\n"
                    + "1. Back\n"
                    + "2. New Book Entry\n"
                    + "3. Delete Book\n"
                    + "4. Exit\n"
                    + "[CHOICE]: ");
            int ch = scan.nextInt();
            scan.nextLine(); // Consume newline
            switch (ch) {
                case 1:
                    return;
                case 2:
                    newBookEntry();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    new ExitManager(scan).closeApplication();
                    break;
                default:
                    System.out.println("\n\tWrong input! Please re-enter value.\n");
            }
        }
    }

    // Method for new book entry.
    private void newBookEntry() {
        try {
            String query = "SELECT accession FROM book_details ORDER BY accession DESC";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            int acc = 1;
            if (rs.next()) {
                acc = rs.getInt("accession") + 1;
            }
            rs.close();
            pst.close();

            System.out.println("\n**Fill the details**:");
            System.out.println("Accession no.: " + acc);
            System.out.print("Enter the Book Name: ");
            String bn = in.readLine();
            System.out.print("Enter the Author Name: ");
            String an = in.readLine();
            System.out.print("Enter the Name of Publisher: ");
            String publisher = in.readLine();
            System.out.print("Enter the Genre of the book: ");
            String genre = in.readLine();
            System.out.print("Enter the Edition (number only): ");
            int edition = Integer.parseInt(in.readLine());
            System.out.print("Enter the Year of Publication (YYYY): ");
            int yop = Integer.parseInt(in.readLine());
            System.out.print("Enter the number of copies: ");
            int copies = Integer.parseInt(in.readLine());

            query = "INSERT INTO book_details VALUES(?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(query);
            while (copies > 0) {
                pst.setInt(1, acc);
                pst.setString(2, bn);
                pst.setString(3, an);
                pst.setString(4, publisher);
                pst.setString(5, genre);
                pst.setInt(6, edition);
                pst.setInt(7, yop);
                pst.setString(8, "Y");
                pst.executeUpdate();
                acc++;
                copies--;
            }
            System.out.println("\n\tSUCCESSFULLY INSERTED!!\n");
            pst.close();
        } catch (Exception e) {
            System.out.println("\n\tError in new book entry: " + e + "\n");
        }
    }

    // Method for deleting a book.
    private void deleteBook() {
        try {
            System.out.print("\n**Deleting the book**\nEnter the Accession no. (number only): ");
            int acc = Integer.parseInt(in.readLine());
            System.out.print("\nDo you really want to delete the book?\n1. Yes\n2. No\n[CHOICE]: ");
            int choice = Integer.parseInt(in.readLine());
            if (choice == 1) {
                String query = "DELETE FROM book_details WHERE accession = ?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setInt(1, acc);
                pst.executeUpdate();
                pst.close();
                System.out.println("\n\tSUCCESSFULLY DELETED!!\n");
            } else {
                System.out.println("\nOperation canceled.\n");
            }
        } catch (Exception e) {
            System.out.println("\n\tError in deleting book: " + e + "\n");
        }
    }
}
