import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
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
            System.out.println("Database Connected.");

            // 1. Create Table
            Statement stmt = con.createStatement();
            String createSQL = "CREATE TABLE IF NOT EXISTS product ("
                    + "id INT PRIMARY KEY, name VARCHAR(50), "
                    + "price DOUBLE, quantity INT)";
            stmt.executeUpdate(createSQL);
            System.out.println("Table 'product' Ready.");

            // 2. Insert Product
            System.out.println("\n--- Insert Product ---");
            System.out.print("Enter ID: "); int id = sc.nextInt(); sc.nextLine();
            System.out.print("Enter Name: "); String name = sc.nextLine();
            System.out.print("Enter Price: "); double price = sc.nextDouble();
            System.out.print("Enter Quantity: "); int qty = sc.nextInt();

            String insertSQL = "INSERT INTO product VALUES (?, ?, ?, ?)";
            PreparedStatement pst1 = con.prepareStatement(insertSQL);
            pst1.setInt(1, id);
            pst1.setString(2, name);
            pst1.setDouble(3, price);
            pst1.setInt(4, qty);
            
            if(pst1.executeUpdate() > 0) System.out.println("Product Inserted.");

            // 3. Update Quantity
            System.out.println("\n--- Update Quantity ---");
            System.out.print("Enter ID to Update: "); int uId = sc.nextInt();
            System.out.print("Enter New Quantity: "); int newQty = sc.nextInt();

            String updateSQL = "UPDATE product SET quantity = ? WHERE id = ?";
            PreparedStatement pst2 = con.prepareStatement(updateSQL);
            pst2.setInt(1, newQty);
            pst2.setInt(2, uId);
            
            if(pst2.executeUpdate() > 0) System.out.println("Quantity Updated.");
            else System.out.println("ID Not Found.");

            // 4. Delete Product
            System.out.println("\n--- Delete Product ---");
            System.out.print("Enter ID to Delete: "); int dId = sc.nextInt();

            String deleteSQL = "DELETE FROM product WHERE id = ?";
            PreparedStatement pst3 = con.prepareStatement(deleteSQL);
            pst3.setInt(1, dId);
            
            if(pst3.executeUpdate() > 0) System.out.println("Product Deleted.");
            else System.out.println("ID Not Found.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
    
}