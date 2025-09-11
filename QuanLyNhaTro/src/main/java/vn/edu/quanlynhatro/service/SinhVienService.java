package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.util.FileUtil;
import vn.edu.quanlynhatro.model.SinhVien;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Lớp SinhVienService chịu trách nhiệm xử lý các logic nghiệp vụ
 * liên quan đến đối tượng SinhVien.
 * Bao gồm các chức năng CRUD (Create, Read, Update, Delete) và lưu trữ dữ liệu.
 */
public class SinhVienService {

    // Danh sách sinh viên được quản lý trong bộ nhớ
    private List<SinhVien> danhSachSinhVien;

    // Tên file để lưu trữ dữ liệu sinh viên
    private static final String FILE_NAME = "data/sinhvien.dat";

    /**
     * Hàm khởi tạo cho SinhVienService.
     * Khi một đối tượng service được tạo, nó sẽ cố gắng đọc dữ liệu
     * từ file. Nếu file không tồn tại, một danh sách rỗng sẽ được tạo.
     */
    @SuppressWarnings("unchecked")
    public SinhVienService() {
        // Đọc dữ liệu từ file khi khởi tạo service
        this.danhSachSinhVien = (List<SinhVien>) FileUtil.docFile(FILE_NAME);
        // Nếu không có dữ liệu trong file (lần chạy đầu tiên), khởi tạo danh sách mới
        if (this.danhSachSinhVien == null) {
            this.danhSachSinhVien = new ArrayList<>();
        }
    }

    /**
     * Trả về toàn bộ danh sách sinh viên.
     *
     * @return List<SinhVien> danh sách sinh viên.
     */
    public List<SinhVien> getAllSinhVien() {
        return this.danhSachSinhVien;
    }

    /**
     * Thêm một sinh viên mới vào danh sách.
     * Trước khi thêm, phương thức sẽ kiểm tra xem MSSV đã tồn tại chưa.
     *
     * @param sv Đối tượng SinhVien cần thêm.
     */
    public void themSinhVien(SinhVien sv) {
        if (sv == null || sv.getmssv() == null || sv.getMssv().isEmpty()) {
            System.out.println("Lỗi: MSSV không hợp lệ!");
            return;
        }

        // Kiểm tra xem MSSV đã tồn tại trong danh sách chưa
        boolean isExist = danhSachSinhVien.stream()
                .anyMatch(s -> s.getMssv().equalsIgnoreCase(sv.getMssv()));

        if (isExist) {
            System.out.println("Lỗi: Mã số sinh viên " + sv.getMssv() + " đã tồn tại!");
            return;
        }

        // Nếu chưa tồn tại, thêm sinh viên vào danh sách và lưu lại
        danhSachSinhVien.add(sv);
        luuFile();
        System.out.println("Thêm sinh viên thành công.");
    }

    /**
     * Sửa thông tin của một sinh viên đã có trong danh sách.
     *
     * @param svDaSua Đối tượng SinhVien chứa thông tin đã được cập nhật.
     * @return true nếu sửa thành công, false nếu không tìm thấy sinh viên.
     */
    public boolean suaSinhVien(SinhVien svDaSua) {
        for (int i = 0; i < danhSachSinhVien.size(); i++) {
            if (danhSachSinhVien.get(i).getMssv().equalsIgnoreCase(svDaSua.getMssv())) {
                danhSachSinhVien.set(i, svDaSua); // Thay thế đối tượng cũ bằng đối tượng mới
                luuFile();
                System.out.println("Cập nhật thông tin sinh viên thành công.");
                return true;
            }
        }
        System.out.println("Lỗi: Không tìm thấy sinh viên có MSSV " + svDaSua.getMssv());
        return false;
    }

    /**
     * Xóa một sinh viên khỏi danh sách dựa vào MSSV.
     *
     * @param mssv Mã số của sinh viên cần xóa.
     * @return true nếu xóa thành công, false nếu không tìm thấy sinh viên.
     */
    public boolean xoaSinhVien(String mssv) {
        boolean isRemoved = danhSachSinhVien.removeIf(sv -> sv.getMssv().equalsIgnoreCase(mssv));
        if (isRemoved) {
            luuFile();
            System.out.println("Xóa sinh viên thành công.");
            return true;
        } else {
            System.out.println("Lỗi: Không tìm thấy sinh viên có MSSV " + mssv);
            return false;
        }
    }

    /**
     * Tìm kiếm một sinh viên trong danh sách dựa vào MSSV.
     *
     * @param mssv Mã số của sinh viên cần tìm.
     * @return Đối tượng SinhVien nếu tìm thấy, ngược lại trả về null.
     */
    public SinhVien timKiemTheoMssv(String mssv) {
        return danhSachSinhVien.stream()
                .filter(sv -> sv.getMssv().equalsIgnoreCase(mssv))
                .findFirst()
                .orElse(null);
    }

    /**
     * Trả về Optional<SinhVien> thay vì null để xử lý an toàn hơn.
     */
    public Optional<SinhVien> timKiemTheoMssvOptional(String mssv) {
        return danhSachSinhVien.stream()
                .filter(sv -> sv.getMssv().equalsIgnoreCase(mssv))
                .findFirst();
    }

    /**
     * Tìm kiếm sinh viên theo tên (chứa một phần tên).
     *
     * @param ten Tên hoặc từ khóa tên sinh viên.
     * @return Danh sách sinh viên phù hợp.
     */
    public List<SinhVien> timKiemTheoTen(String ten) {
        return danhSachSinhVien.stream()
                .filter(sv -> sv.getTen() != null && sv.getTen().toLowerCase().contains(ten.toLowerCase()))
                .toList();
    }

    /**
     * Phương thức private để ghi danh sách sinh viên hiện tại vào file.
     * Được gọi sau mỗi lần thêm, sửa, hoặc xóa để đảm bảo dữ liệu luôn được cập nhật.
     */
    private void luuFile() {
        FileUtil.ghiFile(FILE_NAME, this.danhSachSinhVien);
    }
}
