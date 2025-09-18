package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.Role;
import vn.edu.quanlynhatro.model.TaiKhoan;

public class TaiKhoanTest {
    public static void main(String[] args) {
        System.out.println("=== KIỂM THỬ LỚP TAIKHOAN ===");

        // Tạo tài khoản với role enum
        TaiKhoan tk1 = new TaiKhoan("sinhvien01", "password123", Role.STUDENT);
        TaiKhoan tk2 = new TaiKhoan("admin", "admin123", Role.ADMIN);

        System.out.println("Tài khoản 1: " + tk1);
        System.out.println("Tài khoản 2: " + tk2);

        // Kiểm thử đổi mật khẩu
        System.out.println("\n=== Kiểm thử đổi mật khẩu ===");
        System.out.println("Mật khẩu cũ tk1: " + tk1.getPassword());
        tk1.setPassword("newpass123");
        System.out.println("Mật khẩu mới tk1: " + tk1.getPassword());
    }
}
