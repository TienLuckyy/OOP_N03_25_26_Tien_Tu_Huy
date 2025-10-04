package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.util.FileUtil;
import vn.edu.quanlynhatro.util.PasswordEncoder;
import vn.edu.quanlynhatro.model.TaiKhoan;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Lớp TaiKhoanService quản lý các nghiệp vụ liên quan đến tài khoản
 * như đăng nhập, đăng ký, và đổi mật khẩu.
 */
@Service
public class TaiKhoanService {

    private List<TaiKhoan> danhSachTaiKhoan;
    private static final String FILE_NAME = "data/taikhoan.dat";

    @SuppressWarnings("unchecked")
    public TaiKhoanService() {
        this.danhSachTaiKhoan = (List<TaiKhoan>) FileUtil.docFile(FILE_NAME);
        if (this.danhSachTaiKhoan == null) {
            this.danhSachTaiKhoan = new ArrayList<>();
            // Tạo tài khoản admin mặc định
            TaiKhoan admin = new TaiKhoan("admin", PasswordEncoder.maHoaMD5("admin"), "Admin");
            this.danhSachTaiKhoan.add(admin);
            luuFile();
        }
    }

    public List<TaiKhoan> getAllTaiKhoan() {
        return this.danhSachTaiKhoan;
    }

   public TaiKhoan dangNhap(String username, String password) {
        TaiKhoan tk = timKiemTheoUsername(username);
        System.out.println("Tìm thấy tài khoản: " + (tk != null));
        
        if (tk != null) {
            String inputHash = PasswordEncoder.maHoaMD5(password);
            String storedHash = tk.getPassword();
            System.out.println("Input password hash: " + inputHash);
            System.out.println("Stored password hash: " + storedHash);
            System.out.println("Hashes match: " + inputHash.equals(storedHash));
            
            if (inputHash.equals(storedHash)) {
                return tk;
            }
        }
        return null;
    }
    public boolean dangKy(TaiKhoan tk) {
        if (timKiemTheoUsername(tk.getUsername()) != null) {
            System.out.println("Lỗi: Tên đăng nhập '" + tk.getUsername() + "' đã tồn tại.");
            return false;
        }
        tk.setPassword(PasswordEncoder.maHoaMD5(tk.getPassword()));
        danhSachTaiKhoan.add(tk);
        luuFile();
        System.out.println("Đăng ký tài khoản thành công.");
        return true;
    }

    public boolean doiMatKhau(String username, String oldPass, String newPass) {
        TaiKhoan tk = dangNhap(username, oldPass);
        if (tk != null) {
            tk.setPassword(PasswordEncoder.maHoaMD5(newPass));
            luuFile();
            System.out.println("Đổi mật khẩu thành công.");
            return true;
        }
        System.out.println("Lỗi: Tên đăng nhập hoặc mật khẩu cũ không đúng.");
        return false;
    }

    public TaiKhoan timKiemTheoUsername(String username) {
        return danhSachTaiKhoan.stream()
                .filter(tk -> tk.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }

    public void deleteByUsername(String username) {
        TaiKhoan tk = timKiemTheoUsername(username);
        if (tk != null) {
            danhSachTaiKhoan.remove(tk);
            luuFile();
            System.out.println("Xóa tài khoản thành công: " + username);
        } else {
            System.out.println("Không tìm thấy tài khoản: " + username);
        }
    }

    public boolean updateUser(String username, String newPassword, String newRole) {
        TaiKhoan tk = timKiemTheoUsername(username);
        if (tk != null) {
            // Chỉ cập nhật mật khẩu nếu có giá trị mới và không rỗng
            if (newPassword != null && !newPassword.trim().isEmpty()) {
                tk.setPassword(PasswordEncoder.maHoaMD5(newPassword));
                System.out.println("Đã cập nhật mật khẩu cho: " + username);
            } else {
                System.out.println("Giữ nguyên mật khẩu cho: " + username);
            }
            tk.setRole(newRole);
            luuFile();
            System.out.println("Cập nhật tài khoản thành công: " + username);
            return true;
        }
        System.out.println("Không tìm thấy tài khoản cần cập nhật: " + username);
        return false;
    }

    /**
     * Ghi danh sách tài khoản hiện tại vào file.
     * Nếu thư mục chưa tồn tại, sẽ tạo tự động.
     */
    private void luuFile() {
        File file = new File(FILE_NAME);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs(); // tạo thư mục cha nếu chưa tồn tại
        }
        FileUtil.ghiFile(FILE_NAME, danhSachTaiKhoan);
    }
    // Thêm method này vào TaiKhoanService
    public void taoTaiKhoanMau() {
        // Tạo tài khoản mẫu nếu chưa có
        if (timKiemTheoUsername("admin") == null) {
            TaiKhoan admin = new TaiKhoan("admin", "admin", "Admin");
            dangKy(admin);
        }
        if (timKiemTheoUsername("manager") == null) {
            TaiKhoan manager = new TaiKhoan("manager", "manager", "QuanLy");
            dangKy(manager);
        }
        if (timKiemTheoUsername("student") == null) {
            TaiKhoan student = new TaiKhoan("student", "student", "SinhVien");
            dangKy(student);
        }
    }
}
