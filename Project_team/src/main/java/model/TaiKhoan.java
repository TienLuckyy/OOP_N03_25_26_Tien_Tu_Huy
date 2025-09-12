package vn.edu.quanlynhatro.model;

import java.io.Serializable;

/**
 * Lớp TaiKhoan đại diện cho tài khoản đăng nhập của hệ thống
 */
public class TaiKhoan implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;  // Tên đăng nhập
    private String password;  // Mật khẩu
    private String role;      // Vai trò (SinhVien, QuanLy, Admin...)

    // --- Constructors ---
    public TaiKhoan() {
    }

    public TaiKhoan(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "SinhVien"; // mặc định nếu không truyền vào
    }

    public TaiKhoan(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // --- Getters & Setters ---
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // --- Methods ---
    /**
     * Kiểm tra đăng nhập
     */
    public boolean checkLogin(String user, String pass) {
        return this.username.equals(user) && this.password.equals(pass);
    }

    @Override
    public String toString() {
        return "TaiKhoan{ " +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                " }";
    }
}
