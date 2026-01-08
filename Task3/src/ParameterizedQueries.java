import java.sql.*;
import java.util.Scanner;

public class ParameterizedQueries {
    public static void main(String[] args) {
        
        // Mandatory Output
        System.out.println("Name: Om Ingle");
        System.out.println("Batch: 3rd Year IT");
        System.out.println("----------------------------------------");

        String url = "jdbc:mysql://localhost:3306/test_db";
        String user = "root";
        String pass = "om@ingle.";

        Scanner sc = new Scanner(System.in);

        try (Connection con = DriverManager.getConnection(url, user, pass)) {
            System.out.println("DB Connected.");

            // 1. Insert Data
            System.out.println("\n--- 1. Insert New Employee ---");
            System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine(); 
            System.out.print("Name: "); String name = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();
            System.out.print("City: "); String city = sc.nextLine();
            System.out.print("Contact: "); String contact = sc.nextLine();

            String insertSQL = "INSERT INTO employee VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst1 = con.prepareStatement(insertSQL);
            pst1.setInt(1, id); 
            pst1.setString(2, name); 
            pst1.setString(3, email);
            pst1.setString(4, city); 
            pst1.setString(5, contact);
            
            if(pst1.executeUpdate() > 0) System.out.println("Inserted.");

            // 2. Update Contact
            System.out.println("\n--- 2. Update Contact ---");
            System.out.print("Enter ID to Update: "); int uId = sc.nextInt(); sc.nextLine();
            System.out.print("New Contact: "); String newContact = sc.nextLine();

            String updateSQL = "UPDATE employee SET contact = ? WHERE id = ?";
            PreparedStatement pst2 = con.prepareStatement(updateSQL);
            pst2.setString(1, newContact); 
            pst2.setInt(2, uId);
            
            if(pst2.executeUpdate() > 0) System.out.println("Updated.");
            else System.out.println("ID Not Found.");

            // 3. Search by City
            System.out.println("\n--- 3. Search by City ---");
            System.out.print("Enter City: "); String sCity = sc.nextLine();

            String selectSQL = "SELECT * FROM employee WHERE city = ?";
            PreparedStatement pst3 = con.prepareStatement(selectSQL);
            pst3.setString(1, sCity);
            ResultSet rs = pst3.executeQuery();

            System.out.println("Search Results:");
            while(rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getString("contact"));
            }

        } catch (Exception e) { 
            e.printStackTrace(); 
        } finally { 
            sc.close(); 
        }
    }
}