package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.util.FileUtil;
import vn.edu.quanlynhatro.util.PasswordEncoder; // Giả định bạn đã có lớp này để mã hóa mật khẩu
import vn.edu.quanlynhatro.model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

/**
 * Lớp TaiKhoanService quản lý các nghiệp vụ liên quan đến tài khoản
 * như đăng nhập, đăng ký, và đổi mật khẩu.
 */
public class TaiKhoanService {

    private List<TaiKhoan> danhSachTaiKhoan;
    private static final String FILE_NAME = "data/taikhoan.dat";

    /**
     * Hàm khởi tạo cho TaiKhoanService.
     * Tải danh sách tài khoản từ file. Nếu file không tồn tại,
     * sẽ tạo một tài khoản admin mặc định.
     */
    @SuppressWarnings("unchecked")
    public TaiKhoanService() {
        this.danhSachTaiKhoan = (List<TaiKhoan>) FileUtil.docFile(FILE_NAME);
        if (this.danhSachTaiKhoan == null) {
            this.danhSachTaiKhoan = new ArrayList<>();
            // Trong lần chạy đầu tiên, tạo tài khoản admin mặc định
            // Mật khẩu "admin" được mã hóa trước khi lưu
            TaiKhoan admin = new TaiKhoan("admin", PasswordEncoder.maHoaMD5("admin"), "Admin");
            this.danhSachTaiKhoan.add(admin);
            luuFile();
        }
    }

    /**
     * Lấy toàn bộ danh sách tài khoản.
     * @return List<TaiKhoan>
     */
    public List<TaiKhoan> getAllTaiKhoan() {
        return this.danhSachTaiKhoan;
    }

    /**
     * Xử lý logic đăng nhập an toàn.
     * @param username Tên đăng nhập người dùng nhập.
     * @param password Mật khẩu người dùng nhập (chưa mã hóa).
     * @return Đối tượng TaiKhoan nếu đăng nhập thành công, ngược lại trả về null.
     */
    public TaiKhoan dangNhap(String username, String password) {
        // Tìm tài khoản theo username trước
        TaiKhoan taiKhoanTimThay = timKiemTheoUsername(username);

        if (taiKhoanTimThay != null) {
            // Mã hóa mật khẩu người dùng nhập vào
            String matKhauDaMaHoa = PasswordEncoder.maHoaMD5(password);
            // So sánh mật khẩu đã mã hóa với mật khẩu được lưu trong file
            if (taiKhoanTimThay.getPassword().equals(matKhauDaMaHoa)) {
                return taiKhoanTimThay; // Đăng nhập thành công
            }
        }
        return null; // Sai username hoặc password
    }

    /**
     * Đăng ký một tài khoản mới.
     * @param tk Đối tượng TaiKhoan mới (với mật khẩu chưa mã hóa).
     * @return true nếu đăng ký thành công, false nếu username đã tồn tại.
     */
    public boolean dangKy(TaiKhoan tk) {
        if (timKiemTheoUsername(tk.getUsername()) != null) {
            System.out.println("Lỗi: Tên đăng nhập '" + tk.getUsername() + "' đã tồn tại.");
            return false;
        }
        // Mã hóa mật khẩu trước khi lưu
        String matKhauMaHoa = PasswordEncoder.maHoaMD5(tk.getPassword());
        tk.setPassword(matKhauMaHoa);

        danhSachTaiKhoan.add(tk);
        luuFile();
        System.out.println("Đăng ký tài khoản thành công.");
        return true;
    }

    /**
     * Đổi mật khẩu cho một tài khoản.
     * @param username Tên đăng nhập.
     * @param oldPass Mật khẩu cũ.
     * @param newPass Mật khẩu mới.
     * @return true nếu đổi thành công, false nếu thông tin không chính xác.
     */
    public boolean doiMatKhau(String username, String oldPass, String newPass) {
        // Tái sử dụng hàm dangNhap để xác thực người dùng
        TaiKhoan tk = dangNhap(username, oldPass);

        if (tk != null) {
            // Cập nhật mật khẩu mới đã được mã hóa
            tk.setPassword(PasswordEncoder.maHoaMD5(newPass));
            luuFile();
            System.out.println("Đổi mật khẩu thành công.");
            return true;
        } else {
            System.out.println("Lỗi: Tên đăng nhập hoặc mật khẩu cũ không đúng.");
            return false;
        }
    }

    /**
     * Tìm kiếm một tài khoản theo username (không phân biệt hoa thường).
     * @param username Tên đăng nhập cần tìm.
     * @return Đối tượng TaiKhoan nếu tìm thấy, ngược lại là null.
     */
    public TaiKhoan timKiemTheoUsername(String username) {
        return danhSachTaiKhoan.stream()
                .filter(tk -> tk.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }

    /**
     * Ghi danh sách tài khoản hiện tại vào file.
     */
    private void luuFile() {
        FileUtil.ghiFile(FILE_NAME, this.danhSachTaiKhoan);
    }
}
