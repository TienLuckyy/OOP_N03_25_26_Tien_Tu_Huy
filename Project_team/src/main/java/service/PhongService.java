package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.util.FileUtil;
import vn.edu.quanlynhatro.model.Phong;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Lớp PhongService chịu trách nhiệm xử lý các logic nghiệp vụ
 * liên quan đến đối tượng Phong.
 */
public class PhongService {

    private List<Phong> danhSachPhong;
    private static final String FILE_NAME = "data/phong.dat";

    /**
     * Hàm khởi tạo cho PhongService.
     * Tự động đọc dữ liệu từ file khi service được khởi tạo.
     */
    @SuppressWarnings("unchecked")
    public PhongService() {
        this.danhSachPhong = (List<Phong>) FileUtil.docFile(FILE_NAME);
        if (this.danhSachPhong == null) {
            this.danhSachPhong = new ArrayList<>();
        }
    }

    /**
     * Trả về toàn bộ danh sách phòng.
     * @return List<Phong> danh sách phòng.
     */
    public List<Phong> getAllPhong() {
        return this.danhSachPhong;
    }

    /**
     * Thêm một phòng mới vào danh sách.
     * Kiểm tra xem số phòng đã tồn tại chưa trước khi thêm.
     * @param phong Đối tượng Phong cần thêm.
     */
    public void themPhong(Phong phong) {
        boolean isExist = danhSachPhong.stream()
                .anyMatch(p -> p.getSoPhong().equalsIgnoreCase(phong.getSoPhong()));

        if (isExist) {
            System.out.println("Lỗi: Số phòng " + phong.getSoPhong() + " đã tồn tại!");
            return;
        }
        
        danhSachPhong.add(phong);
        luuFile();
        System.out.println("Thêm phòng thành công.");
    }

    /**
     * Sửa thông tin của một phòng đã có.
     * @param phongDaSua Đối tượng Phong chứa thông tin đã cập nhật.
     * @return true nếu sửa thành công, false nếu không tìm thấy phòng.
     */
    public boolean suaPhong(Phong phongDaSua) {
        for (int i = 0; i < danhSachPhong.size(); i++) {
            if (danhSachPhong.get(i).getSoPhong().equalsIgnoreCase(phongDaSua.getSoPhong())) {
                danhSachPhong.set(i, phongDaSua);
                luuFile();
                System.out.println("Cập nhật thông tin phòng thành công.");
                return true;
            }
        }
        System.out.println("Lỗi: Không tìm thấy phòng có số " + phongDaSua.getSoPhong());
        return false;
    }

    /**
     * Xóa một phòng khỏi danh sách dựa vào số phòng.
     * @param soPhong Số của phòng cần xóa.
     * @return true nếu xóa thành công, false nếu không tìm thấy.
     */
    public boolean xoaPhong(String soPhong) {
        boolean isRemoved = danhSachPhong.removeIf(p -> p.getSoPhong().equalsIgnoreCase(soPhong));
        
        if (isRemoved) {
            luuFile();
            System.out.println("Xóa phòng thành công.");
            return true;
        } else {
            System.out.println("Lỗi: Không tìm thấy phòng có số " + soPhong);
            return false;
        }
    }

    /**
     * Tìm kiếm một phòng trong danh sách dựa vào số phòng.
     * @param soPhong Số phòng cần tìm.
     * @return Đối tượng Phong nếu tìm thấy, ngược lại trả về null.
     */
    public Phong timKiemTheoSoPhong(String soPhong) {
        return danhSachPhong.stream()
                .filter(p -> p.getSoPhong().equalsIgnoreCase(soPhong))
                .findFirst()
                .orElse(null);
    }

    /**
     * Lấy danh sách các phòng còn trống (trangThai = false).
     * @return List<Phong> danh sách các phòng trống.
     */
    public List<Phong> timKiemPhongTrong() {
        return danhSachPhong.stream()
                .filter(phong -> !phong.isTrangThai()) // !phong.isTrangThai() tương đương trangThai == false
                .collect(Collectors.toList());
    }

    /**
     * Phương thức private để ghi danh sách phòng hiện tại vào file.
     */
    private void luuFile() {
        FileUtil.ghiFile(FILE_NAME, this.danhSachPhong);
    }
}
