package src;

import java.io.BufferedReader;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IssueManager {
    private Connection con;
    private BufferedReader in;
    private Scanner scan;

    public IssueManager(Connection con, BufferedReader in, Scanner scan) {
        this.con = con;
        this.in = in;
        this.scan = scan;
    }

    // Issue a book.
    public void issueBook() {
        if (con == null) {
            System.out.println("\n\tDatabase connection not available. This feature requires a live connection.\n");
            return;
        }
        try {
            System.out.print("\n**Issue Book**\nEnter Your Member No. (number only): ");
            int memberId = Integer.parseInt(in.readLine());
            System.out.print("Enter the Accession No.: ");
            int acc = Integer.parseInt(in.readLine());

            String query = "UPDATE book_details SET STATUS = 'N' WHERE ACCESSION = ? AND STATUS = 'Y'";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, acc);
            int flag = pst.executeUpdate();

            if (flag > 0) {
                query = "INSERT INTO issue_book(member_id, Accession, fine) VALUES(?,?,0)";
                pst = con.prepareStatement(query);
                pst.setInt(1, memberId);
                pst.setInt(2, acc);
                pst.executeUpdate();
                pst.close();
                System.out.println("\n\tBook ISSUED SUCCESSFULLY!!\n");
            } else {
                System.out.println("\n\tBook already issued or not available.\n");
            }
        } catch (Exception e) {
            System.out.println("\n\tError in issuing book: " + e + "\n");
        }
    }

    // Return a book.
    public void returnBook() {
        if (con == null) {
            System.out.println("\n\tDatabase connection not available. This feature requires a live connection.\n");
            return;
        }
        try {
            System.out.print("\n**Return Book**\nEnter Your Member No. (number only): ");
            int memberId = Integer.parseInt(in.readLine());
            String query = "SELECT b.accession, b.book_name, b.author_name, i.issue_date " +
                           "FROM book_details b INNER JOIN issue_book i ON b.accession = i.accession " +
                           "WHERE b.status = 'N' AND i.member_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, memberId);
            ResultSet rs = pst.executeQuery();

            int count = 0;
            List<Integer> accList = new ArrayList<>();
            List<String> dateList = new ArrayList<>();
            while (rs.next()) {
                if (count == 0) {
                    System.out.println("\n\t**Books Issued**");
                    System.out.println("\tAcc no. \tBook Name \tAuthor Name \tIssue Date");
                }
                int acc = rs.getInt("accession");
                System.out.println("\t" + acc + "\t" + rs.getString("book_name") +
                        "\t" + rs.getString("author_name") + "\t" + rs.getString("issue_date"));
                accList.add(acc);
                dateList.add(rs.getString("issue_date"));
                count++;
            }
            rs.close();
            pst.close();
            if (count == 0) {
                System.out.println("\n\tNo books issued.\n");
                return;
            }

            System.out.print("\nEnter the Accession No. to return: ");
            int accReturn = Integer.parseInt(in.readLine());
            System.out.print("Date Format [DD-MM-YYYY]. Enter today's date: ");
            String returnDate = in.readLine();

            // Find the issue date for this accession.
            String issueDate = null;
            for (int i = 0; i < accList.size(); i++) {
                if (accList.get(i) == accReturn) {
                    issueDate = dateList.get(i);
                    break;
                }
            }
            if (issueDate == null) {
                System.out.println("\n\tAccession number not found among issued books.\n");
                return;
            }
            LocalDate issued = LocalDate.parse(issueDate.substring(0, 10));
            // Convert returnDate from DD-MM-YYYY to YYYY-MM-DD.
            LocalDate returned = LocalDate.parse(returnDate.substring(6, 10) + "-" +
                                                   returnDate.substring(3, 5) + "-" +
                                                   returnDate.substring(0, 2));
            Period period = Period.between(issued, returned);
            int days = period.getDays();
            int fine = 0;
            String amountDue = "PAID";
            if (days > 7) {
                fine = days * 10;
                System.out.print("\nFine: " + fine + " for " + days + " days.\n"
                        + "Did the student pay the amount?\n1. PAID\n2. UNPAID\n[CHOICE]: ");
                int payChoice = Integer.parseInt(in.readLine());
                if (payChoice == 2) {
                    amountDue = "UNPAID";
                }
            }

            query = "UPDATE book_details SET STATUS = 'Y' WHERE ACCESSION = ? AND STATUS = 'N'";
            pst = con.prepareStatement(query);
            pst.setInt(1, accReturn);
            int flag = pst.executeUpdate();

            if (flag > 0) {
                query = "UPDATE issue_book SET RETURN_DATE = ?, FINE = ?, AMOUNT_DUE = ? WHERE ACCESSION = ?";
                pst = con.prepareStatement(query);
                pst.setString(1, returnDate);
                pst.setInt(2, fine);
                pst.setString(3, amountDue);
                pst.setInt(4, accReturn);
                pst.executeUpdate();
                pst.close();
                System.out.println("\n\tBook RETURNED SUCCESSFULLY!!\n");
            } else {
                System.out.println("\n\tBook already returned.\n");
            }
        } catch (Exception e) {
            System.out.println("\n\tError in returning book: " + e + "\n");
        }
    }
}
