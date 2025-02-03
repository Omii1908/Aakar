package src;

import java.io.BufferedReader;
import java.sql.*;
import java.util.Scanner;

public class MemberManager {
    private Connection con;
    private BufferedReader in;
    private Scanner scan;

    public MemberManager(Connection con, BufferedReader in, Scanner scan) {
        this.con = con;
        this.in = in;
        this.scan = scan;
    }

    // Menu for member-related operations.
    public void memberQueries() {
        if (con == null) {
            System.out.println("\n\tDatabase connection not available. This feature requires a live connection.\n");
            return;
        }
        while (true) {
            System.out.print("\n\n\t**MEMBERS RELATED QUERIES**\n"
                    + "1. Back\n"
                    + "2. New Member Entry\n"
                    + "3. Update Member Details\n"
                    + "4. Deactivate your Account\n"
                    + "5. Exit\n"
                    + "[CHOICE]: ");
            int ch = scan.nextInt();
            scan.nextLine(); // Consume newline
            switch (ch) {
                case 1:
                    return;
                case 2:
                    newMemberEntry();
                    break;
                case 3:
                    updateMemberDetails();
                    break;
                case 4:
                    deactivateMember();
                    break;
                case 5:
                    new ExitManager(scan).closeApplication();
                    break;
                default:
                    System.out.println("\n\tWrong input! Please re-enter value.\n");
            }
        }
    }

    // Method for new member entry.
    private void newMemberEntry() {
        try {
            String query = "SELECT member_id FROM member_details ORDER BY member_id DESC";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            int memberId = 1;
            if (rs.next()) {
                memberId = rs.getInt("member_id") + 1;
            }
            rs.close();
            pst.close();

            System.out.println("\n**Fill the details**:");
            System.out.println("Member ID: " + memberId);
            System.out.print("Enter Your Member Name: ");
            String name = in.readLine();
            System.out.print("Enter Your Phone No.: ");
            int phone = Integer.parseInt(in.readLine());
            System.out.print("Enter Your Address: ");
            String address = in.readLine();
            System.out.print("Enter Your Department: ");
            String dept = in.readLine();

            query = "INSERT INTO member_details VALUES(?,?,?,?,?,?)";
            pst = con.prepareStatement(query);
            pst.setInt(1, memberId);
            pst.setString(2, name);
            pst.setInt(3, phone);
            pst.setString(4, address);
            pst.setString(5, dept);
            pst.setString(6, "ACTIVE");
            pst.executeUpdate();
            pst.close();
            System.out.println("\n\tSUCCESSFULLY INSERTED!!\n");
        } catch (Exception e) {
            System.out.println("\n\tError in new member entry: " + e + "\n");
        }
    }

    // Method for updating member details.
    private void updateMemberDetails() {
        try {
            System.out.print("\n**Update Member Details**\nEnter Your Member No. (number only): ");
            int memberId = Integer.parseInt(in.readLine());
            System.out.print("Enter Your New Phone No.: ");
            int phone = Integer.parseInt(in.readLine());
            System.out.print("Enter Your New Address: ");
            String address = in.readLine();
            System.out.print("Enter Your New Department: ");
            String dept = in.readLine();

            String query = "UPDATE member_details SET PHONE_NO = ?, ADDRESS = ?, DEPARTMENT = ? WHERE MEMBER_ID = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, phone);
            pst.setString(2, address);
            pst.setString(3, dept);
            pst.setInt(4, memberId);
            pst.executeUpdate();
            pst.close();
            System.out.println("\n\tSUCCESSFULLY UPDATED!!\n");
        } catch (Exception e) {
            System.out.println("\n\tError in updating member details: " + e + "\n");
        }
    }

    // Method for deactivating a member.
    private void deactivateMember() {
        try {
            System.out.print("\n**Deactivate Account**\nEnter Your Member No. (number only): ");
            int memberId = Integer.parseInt(in.readLine());
            System.out.print("Confirm deactivation:\n1. Yes\n2. No\n[CHOICE]: ");
            int choice = Integer.parseInt(in.readLine());
            if (choice == 1) {
                String query = "UPDATE member_details SET STATUS = ? WHERE MEMBER_ID = ?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, "EXPIRED");
                pst.setInt(2, memberId);
                pst.executeUpdate();
                pst.close();
                System.out.println("\n\tSUCCESSFULLY DEACTIVATED!!\n");
            } else {
                System.out.println("\nOperation canceled.\n");
            }
        } catch (Exception e) {
            System.out.println("\n\tError in deactivating account: " + e + "\n");
        }
    }
}
