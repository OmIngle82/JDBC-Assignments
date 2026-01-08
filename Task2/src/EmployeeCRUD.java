import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeCRUD {
    public static void main(String[] args) {
        
        // Mandatory Output
        System.out.println("Name: Om Ingle");
        System.out.println("Batch: 3rd Year IT");
        System.out.println("----------------------------------------");

        String url = "jdbc:mysql://localhost:3306/test_db";
        String username = "root";
        String password = "om@ingle."; 

        Connection con = null;
        Statement stmt = null;

        try {
            // 1. Connect to DB
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            stmt = con.createStatement();
            System.out.println("Database Connected.");

            // 2. Create Table
            String createSQL = "CREATE TABLE IF NOT EXISTS employee ("
                    + "id INT PRIMARY KEY, name VARCHAR(50), email VARCHAR(50), "
                    + "city VARCHAR(50), contact VARCHAR(20))";
            stmt.executeUpdate(createSQL);
            System.out.println("Table 'employee' Ready.");

            // 3. Insert Data
            String insertSQL = "INSERT INTO employee VALUES "
                    + "(18, 'Virat Kohli', 'viratKohli@gmail.com', 'Delhi', '9876543210')";
            stmt.executeUpdate(insertSQL);
            System.out.println("Inserted: Virat Kohli (ID 18)");

            // 4. Update Data
            String updateSQL = "UPDATE employee SET city = 'Mumbai' WHERE id = 18";
            stmt.executeUpdate(updateSQL);
            System.out.println("Updated: Virat Kohli moved to Mumbai.");

            // 5. Delete Data
            String deleteSQL = "DELETE FROM employee WHERE id = 18";
            stmt.executeUpdate(deleteSQL);
            System.out.println("Deleted: Record ID 18 removed.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
                System.out.println("Resources Closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}