package sqlconnectivety;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDB {

    // Consider using environment variables or a configuration file for these
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }

    public void addEmployee(int id, String name, String position) {
        String sql = "INSERT INTO Employee (id, name, position) VALUES (?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, position);
            pstmt.executeUpdate();
            System.out.println("Employee added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding employee: " + e.getMessage());
            // Consider logging the error for further investigation
        }
    }

    public void getEmployee(int id) {
        String sql = "SELECT * FROM Employee WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Position: " + rs.getString("position"));
            } else {
                System.out.println("Employee with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error getting employee: " + e.getMessage());
            
        }
    }

    public void updateEmployee(int id, String name, String position) {
        String sql = "UPDATE Employee SET name = ? , position = ? WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, position);
            pstmt.setInt(3, id);
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Employee updated successfully!");
            } else {
                System.out.println("Employee with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating employee: " + e.getMessage());
            
        }
    }

    public void deleteEmployee(int id) {
        String sql = "DELETE FROM Employee WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("Employee with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting employee: " + e.getMessage());
            // Consider logging the error for further investigation
        }
    }
}
