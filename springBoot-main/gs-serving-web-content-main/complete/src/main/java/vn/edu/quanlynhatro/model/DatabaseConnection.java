package vn.edu.quanlynhatro.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 * Lớp quản lý kết nối đến cơ sở dữ liệu Aiven MySQL
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    private String url;
    private String username;
    private String password;
    
    // Thông tin kết nối mặc định
    private static final String DEFAULT_URL = "jdbc:mysql://mysql-134e11f2-app-phenikaa.j.aivencloud.com:28575/defaultdb";
    private static final String DEFAULT_USERNAME = "avnadmin";
    private static final String DEFAULT_PASSWORD = "AVNS_36Lxj4Cy2KHfvBdsLN6";
    
    /**
     * Constructor private để triển khai Singleton pattern
     */
    private DatabaseConnection() {
        try {
            // Sửa thành MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Khởi tạo thông tin kết nối
            this.url = DEFAULT_URL;
            this.username = DEFAULT_USERNAME;
            this.password = DEFAULT_PASSWORD;
            
            System.out.println("MySQL JDBC Driver loaded successfully!");
            
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver không tìm thấy!");
            System.err.println("Hãy thêm dependency MySQL Connector/J vào project!");
            e.printStackTrace();
        }
    }
    
    /**
     * Lấy instance của DatabaseConnection (Singleton pattern)
     */
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
    
    /**
     * Thiết lập thông tin kết nối
     */
    public void setConnectionInfo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        closeConnection(); // Đóng kết nối cũ nếu có
    }
    
    /**
     * Mở kết nối đến cơ sở dữ liệu
     */
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Thêm các tham số kết nối cho MySQL Aiven
                String connectionUrl = url + "?useSSL=true&requireSSL=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
                
                System.out.println("Connecting to: " + url);
                connection = DriverManager.getConnection(connectionUrl, username, password);
                System.out.println("Kết nối đến Aiven MySQL database thành công!");
                
            } catch (SQLException e) {
                System.err.println("Lỗi kết nối đến Aiven database: " + e.getMessage());
                System.err.println("URL: " + url);
                throw e;
            }
        }
        return connection;
    }
    
    /**
     * Đóng kết nối
     */
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Đóng kết nối database thành công!");
            } catch (SQLException e) {
                System.err.println("Lỗi khi đóng kết nối: " + e.getMessage());
            }
        }
    }
    
    /**
     * Kiểm tra kết nối
     */
    public boolean testConnection() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT 1")) {
            
            return rs.next() && rs.getInt(1) == 1;
            
        } catch (SQLException e) {
            System.err.println("Kiểm tra kết nối thất bại: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Thực thi query và trả về ResultSet
     */
    public ResultSet executeQuery(String sql) throws SQLException {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
    }
    
    /**
     * Thực thi update/insert/delete
     */
    public int executeUpdate(String sql) throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            return stmt.executeUpdate(sql);
        }
    }
    
    /**
     * Tạo PreparedStatement
     */
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        Connection conn = getConnection();
        return conn.prepareStatement(sql);
    }
    
    /**
     * Bắt đầu transaction
     */
    public void beginTransaction() throws SQLException {
        Connection conn = getConnection();
        conn.setAutoCommit(false);
    }
    
    /**
     * Commit transaction
     */
    public void commitTransaction() throws SQLException {
        Connection conn = getConnection();
        conn.commit();
        conn.setAutoCommit(true);
    }
    
    /**
     * Rollback transaction
     */
    public void rollbackTransaction() throws SQLException {
        Connection conn = getConnection();
        conn.rollback();
        conn.setAutoCommit(true);
    }
}
