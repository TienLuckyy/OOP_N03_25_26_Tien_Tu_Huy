package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.TaiKhoan;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanService {
    // Dummy data storage
    private List<TaiKhoan> taiKhoanList = new ArrayList<>();

    public TaiKhoanService() {
        // Initialize with some default accounts
        taiKhoanList.add(new TaiKhoan("admin", "admin123", "Admin"));
        taiKhoanList.add(new TaiKhoan("sinhvien01", "password123", "SinhVien"));
    }

    /**
     * Đăng ký tài khoản mới.
     * @param taiKhoan Tài khoản cần đăng ký
     */
    public void dangKy(TaiKhoan taiKhoan) {
        if (taiKhoanList.stream().anyMatch(tk -> tk.getUsername().equals(taiKhoan.getUsername()))) {
            System.out.println("Lỗi: Tên đăng nhập đã tồn tại.");
            return;
        }
        taiKhoanList.add(taiKhoan);
        System.out.println("Đăng ký tài khoản thành công!");
    }

    /**
     * Đăng nhập hệ thống.
     * @param username Tên đăng nhập
     * @param password Mật khẩu
     * @return Tài khoản nếu đăng nhập thành công, null nếu thất bại
     */
    public TaiKhoan dangNhap(String username, String password) {
        return taiKhoanList.stream()
                .filter(tk -> tk.checkLogin(username, password))
                .findFirst()
                .orElse(null);
    }

    /**
     * Đổi mật khẩu của tài khoản.
     * @param username Tên đăng nhập
     * @param oldPassword Mật khẩu cũ
     * @param newPassword Mật khẩu mới
     */
    public void doiMatKhau(String username, String oldPassword, String newPassword) {
        TaiKhoan taiKhoan = taiKhoanList.stream()
                .filter(tk -> tk.getUsername().equals(username) && tk.getPassword().equals(oldPassword))
                .findFirst()
                .orElse(null);

        if (taiKhoan != null) {
            taiKhoan.setPassword(newPassword);
            System.out.println("Đổi mật khẩu thành công!");
        } else {
            System.out.println("Lỗi: Tên đăng nhập hoặc mật khẩu cũ không chính xác.");
        }
    }

    /**
     * Lấy danh sách tất cả tài khoản
     * @return Danh sách tài khoản
     */
    public List<TaiKhoan> getAllTaiKhoan() {
        return taiKhoanList;
    }
}
