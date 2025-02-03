package src;

import java.sql.*;

public class DatabaseManager {
    public Connection connect() {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try {
                con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "<username>", "<password>");
                con.setAutoCommit(true);
                System.out.println("\n\tConnection Established with Server!!");
            } catch (SQLException e) {
                System.out.println("\n\tUnable to connect with the Database...\n\tSome features will use proxy data.");
            }
        } catch (Exception e) {
            System.out.println("\n\tDriver is missing. Please fix the driver!!\n");
        }
        return con;
    }
}
