package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.DatabaseConnection;

public class TestConnection {
    public static void main(String[] args) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        
        try {
            if (db.testConnection()) {
                System.out.println("✅ Kết nối đến Aiven MySQL thành công!");
                
                // Test thực thi query đơn giản
                String sql = "SHOW TABLES";
                var resultSet = db.executeQuery(sql);
                System.out.println("Các bảng trong database:");
                while (resultSet.next()) {
                    System.out.println("- " + resultSet.getString(1));
                }
                
            } else {
                System.out.println("❌ Kết nối thất bại!");
            }
        } catch (Exception e) {
            System.err.println("Lỗi: " + e.getMessage());
            e.printStackTrace();
        } finally {
            db.closeConnection();
        }
    }
}
