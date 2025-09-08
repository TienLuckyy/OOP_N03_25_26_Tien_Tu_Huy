package vn.edu.quanlynhatro.controller;

import service.TaiKhoanService;
import vn.edu.model.TaiKhoan;

import java.util.Scanner;

public class TaiKhoanController {
    private final TaiKhoanService taiKhoanService = new TaiKhoanService();
    private final Scanner scanner = new Scanner(System.in);

    public void menuTaiKhoan() {
        while (true) {
            System.out.println("\n---[ MENU QUẢN LÝ TÀI KHOẢN ]---");
            System.out.println("1. Đăng ký tài khoản mới (Sinh Viên)");
            System.out.println("2. Đăng nhập");
            System.out.println("3. Đổi mật khẩu");
            System.out.println("0. Quay lại menu chính");
            System.out.print(">> Vui lòng chọn chức năng: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số.");
                continue;
            }

            switch (choice) {
                case 1:
                    hienThiDangKyUI();
                    break;
                case 2:
                    hienThiDangNhapUI();
                    break;
                case 3:
                    hienThiDoiMatKhauUI();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
            }
        }
    }

    private void hienThiDangKyUI() {
        System.out.println("\n--- Đăng Ký Tài Khoản Sinh Viên ---");
        System.out.print("Nhập tên đăng nhập (username): ");
        String username = scanner.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();

        // Mặc định đăng ký từ menu này là vai trò "SinhVien"
        TaiKhoan tkMoi = new TaiKhoan(username, password, "SinhVien");
        taiKhoanService.dangKy(tkMoi);
    }

    private void hienThiDangNhapUI() {
        System.out.println("\n--- Đăng Nhập Hệ Thống ---");
        System.out.print("Tên đăng nhập: ");
        String username = scanner.nextLine();
        System.out.print("Mật khẩu: ");
        String password = scanner.nextLine();

        TaiKhoan tk = taiKhoanService.dangNhap(username, password);
        if (tk != null) {
            System.out.println("Đăng nhập thành công! Xin chào " + tk.getUsername() + ", vai trò của bạn là: " + tk.getRole());
            // Tại đây có thể điều hướng đến menu con tương ứng với vai trò
        } else {
            System.out.println("Đăng nhập thất bại. Vui lòng kiểm tra lại tên đăng nhập hoặc mật khẩu.");
        }
    }

    private void hienThiDoiMatKhauUI() {
        System.out.println("\n--- Đổi Mật Khẩu ---");
        System.out.print("Nhập tên đăng nhập của bạn: ");
        String username = scanner.nextLine();
        System.out.print("Nhập mật khẩu CŨ: ");
        String oldPass = scanner.nextLine();
        System.out.print("Nhập mật khẩu MỚI: ");
        String newPass = scanner.nextLine();

        taiKhoanService.doiMatKhau(username, oldPass, newPass);
    }
}