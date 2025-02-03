package src;

import java.io.BufferedReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RecommendationManager {
    private Connection con;
    private BufferedReader in;
    private Scanner scan;

    public RecommendationManager(Connection con, BufferedReader in, Scanner scan) {
        this.con = con;
        this.in = in;
        this.scan = scan;
    }

    // Recommend a book.
    public void recommendBook() {
        if (con != null) {
            try {
                String query = "SELECT * FROM book_details WHERE STATUS = 'Y'";
                PreparedStatement pst = con.prepareStatement(query);
                ResultSet rs = pst.executeQuery();
                List<String> books = new ArrayList<>();
                while (rs.next()) {
                    String book = "Accession: " + rs.getInt("accession") +
                                  " | Book: " + rs.getString("book_name") +
                                  " | Author: " + rs.getString("author_name");
                    books.add(book);
                }
                rs.close();
                pst.close();
                if (!books.isEmpty()) {
                    Random rand = new Random();
                    int index = rand.nextInt(books.size());
                    System.out.println("\n\t**Recommended Book**\n" + books.get(index) + "\n");
                    return;
                } else {
                    System.out.println("\n\tNo available books in database. Using proxy data.\n");
                }
            } catch (Exception e) {
                System.out.println("\n\tError in recommending book: " + e + "\nUsing proxy data.\n");
            }
        }
        recommendFromProxy();
    }

    // Fallback recommendation using proxy (hard-coded) data.
    private void recommendFromProxy() {
        String[] proxyBooks = {
            "Accession: 1001 | Book: 'The Great Gatsby' by F. Scott Fitzgerald",
            "Accession: 1002 | Book: '1984' by George Orwell",
            "Accession: 1003 | Book: 'To Kill a Mockingbird' by Harper Lee",
            "Accession: 1004 | Book: 'Pride and Prejudice' by Jane Austen",
            "Accession: 1005 | Book: 'Moby-Dick' by Herman Melville"
        };
        Random rand = new Random();
        int index = rand.nextInt(proxyBooks.length);
        System.out.println("\n\t**Recommended Book (Proxy Data)**\n" + proxyBooks[index] + "\n");
    }
}
