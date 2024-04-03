import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dbManager {
    static final String DB_URL = "jdbc:sqlite:/home/eschyle/IdeaProjects/slackClone/slackUsers.db";

    public static void insertData(User data) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO Users (username, firstname, lastname, email, address, password) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, data.getUsername());
                pstmt.setString(2, data.getFirstName());
                pstmt.setString(3, data.getLastName());
                pstmt.setString(4, data.getEmail());
                pstmt.setString(5, data.getAddress());
                pstmt.setString(6, data.getPassword());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Check if a username-password combination exists in the database
    public static boolean checkLoginCredentials(String username, String password) {
        boolean exists = false;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        exists = true; // Username-password combination exists
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    // Get the list of all users from the database
    public static List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT * FROM users";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        String sz_login = rs.getString("username");
                        String sz_firstname = rs.getString("firstName");
                        String sz_lastname = rs.getString("lastName");
                        String sz_email = rs.getString("email");
                        String address = rs.getString("address");
                        String password = rs.getString("password");
                        User user = new User(
                            sz_login, sz_firstname, sz_lastname, sz_email, address, password, ""
                        );
                        userList.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
