import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static void main(String[] args) {
        
        // Mandatory Output Format
        System.out.println("Name: Om Ingle");
        System.out.println("Batch: IT");
        System.out.println("----------------------------------------");

        String url = "jdbc:mysql://localhost:3306/test_db";
        String username = "root";
        String password = "om@ingle."; 

        Connection con = null;

        try {
            // 1. Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded.");

            // 2. Establish Connection
            con = DriverManager.getConnection(url, username, password);

            // 3. Verify Connection
            if (con != null) {
                System.out.println("Connection Established Successfully!");
            }

        } catch (ClassNotFoundException e) {
            // Handles missing driver class
            System.out.println("Error: Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            // Handles database connection failure
            System.out.println("Error: Connection failed.");
            e.printStackTrace();
        } finally {
            // 4. Close Resources
            try {
                if (con != null) con.close();
                System.out.println("Connection Closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}