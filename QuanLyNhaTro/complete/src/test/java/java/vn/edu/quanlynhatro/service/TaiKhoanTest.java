package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.TaiKhoan;

public class TaiKhoanTest {
    public static void main(String[] args) {
        System.out.println("=== KIỂM THỬ LỚP TAIKHOAN ===");

        // Tạo tài khoản mặc định
        TaiKhoan tk1 = new TaiKhoan("sv001", "123456");
        System.out.println("Tài khoản 1: " + tk1);

        // Tạo tài khoản với role
        TaiKhoan tk2 = new TaiKhoan("admin", "admin123", "Admin");
        System.out.println("Tài khoản 2: " + tk2);

        // Kiểm thử đăng nhập đúng
        boolean login1 = tk1.checkLogin("sv001", "123456");
        System.out.println("Đăng nhập tk1 với đúng mật khẩu: " + (login1 ? "Thành công" : "Thất bại"));

        // Kiểm thử đăng nhập sai
        boolean login2 = tk1.checkLogin("sv001", "wrongpass");
        System.out.println("Đăng nhập tk1 với mật khẩu sai: " + (login2 ? "Thành công" : "Thất bại"));

        // Kiểm thử đổi mật khẩu
        tk1.setPassword("newpass");
        boolean login3 = tk1.checkLogin("sv001", "newpass");
        System.out.println("Sau khi đổi mật khẩu, đăng nhập tk1: " + (login3 ? "Thành công" : "Thất bại"));
    }
}
