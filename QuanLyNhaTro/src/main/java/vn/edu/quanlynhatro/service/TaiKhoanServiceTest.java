package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.TaiKhoan;

public class TaiKhoanServiceTest {
    public static void main(String[] args) {
        TaiKhoanService service = new TaiKhoanService();

        System.out.println("=== TEST TAIKHOAN SERVICE ===");

        // Đăng ký tài khoản mới
        TaiKhoan sv1 = new TaiKhoan("sv001", "123456", "SinhVien");
        boolean dk1 = service.dangKy(sv1);
        System.out.println("Đăng ký sv001: " + (dk1 ? "Thành công" : "Thất bại"));

        // Thử đăng ký trùng username
        TaiKhoan sv2 = new TaiKhoan("sv001", "abcdef", "SinhVien");
        boolean dk2 = service.dangKy(sv2);
        System.out.println("Đăng ký trùng sv001: " + (dk2 ? "Thành công" : "Thất bại"));

        // Đăng nhập đúng
        TaiKhoan login1 = service.dangNhap("sv001", "123456");
        System.out.println("Đăng nhập sv001/123456: " + (login1 != null ? "Thành công" : "Thất bại"));

        // Đăng nhập sai
        TaiKhoan login2 = service.dangNhap("sv001", "sai_pass");
        System.out.println("Đăng nhập sv001/sai_pass: " + (login2 != null ? "Thành công" : "Thất bại"));

        // Đổi mật khẩu
        boolean doiPass = service.doiMatKhau("sv001", "123456", "newpass");
        System.out.println("Đổi mật khẩu sv001: " + (doiPass ? "Thành công" : "Thất bại"));

        // Đăng nhập bằng mật khẩu mới
        TaiKhoan login3 = service.dangNhap("sv001", "newpass");
        System.out.println("Đăng nhập sv001/newpass: " + (login3 != null ? "Thành công" : "Thất bại"));
    }
}
