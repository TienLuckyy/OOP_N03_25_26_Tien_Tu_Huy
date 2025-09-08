package service;

import util.FileUtil;
import vn.edu.model.BanQuanLy;

import java.util.ArrayList;
import java.util.List;

/**
 * Lớp BQLService chịu trách nhiệm xử lý các logic nghiệp vụ
 * liên quan đến đối tượng BanQuanLy.
 * Bao gồm các chức năng CRUD (Create, Read, Update, Delete) và lưu trữ dữ liệu.
 */
public class BQLService {

    // Danh sách thành viên BQL được quản lý trong bộ nhớ
    private List<BanQuanLy> danhSachBQL;
    // Tên file để lưu trữ dữ liệu
    private static final String FILE_NAME = "data/banquanly.dat";

    /**
     * Hàm khởi tạo cho BQLService.
     * Tự động đọc dữ liệu từ file khi service được khởi tạo.
     * Nếu file không tồn tại, một danh sách rỗng sẽ được tạo mới.
     */
    @SuppressWarnings("unchecked")
    public BQLService() {
        this.danhSachBQL = (List<BanQuanLy>) FileUtil.docFile(FILE_NAME);
        if (this.danhSachBQL == null) {
            this.danhSachBQL = new ArrayList<>();
        }
    }

    /**
     * Trả về toàn bộ danh sách thành viên Ban Quản lý.
     * @return List<BanQuanLy> danh sách BQL.
     */
    public List<BanQuanLy> getAllBQL() {
        return this.danhSachBQL;
    }

    /**
     * Thêm một thành viên BQL mới vào danh sách.
     * Phương thức sẽ kiểm tra CCCD để tránh trùng lặp.
     * @param bql Đối tượng BanQuanLy cần thêm.
     */
    public void themBQL(BanQuanLy bql) {
        // Kiểm tra xem CCCD đã tồn tại trong danh sách chưa
        boolean isExist = danhSachBQL.stream().anyMatch(member -> member.getCccd().equals(bql.getCccd()));
        if (isExist) {
            System.out.println("Lỗi: CCCD " + bql.getCccd() + " đã tồn tại!");
            return;
        }
        // Thêm thành viên mới và lưu lại file
        danhSachBQL.add(bql);
        luuFile();
        System.out.println("Thêm thành viên BQL thành công.");
    }

    /**
     * Sửa thông tin của một thành viên BQL trong danh sách.
     * @param bqlDaSua Đối tượng BanQuanLy chứa thông tin đã được cập nhật.
     * @return true nếu sửa thành công, false nếu không tìm thấy.
     */
    public boolean suaBQL(BanQuanLy bqlDaSua) {
        for (int i = 0; i < danhSachBQL.size(); i++) {
            if (danhSachBQL.get(i).getCccd().equals(bqlDaSua.getCccd())) {
                danhSachBQL.set(i, bqlDaSua); // Thay thế đối tượng cũ bằng đối tượng mới
                luuFile();
                System.out.println("Cập nhật thông tin thành viên BQL thành công.");
                return true;
            }
        }
        System.out.println("Lỗi: Không tìm thấy thành viên BQL có CCCD " + bqlDaSua.getCccd());
        return false;
    }

    /**
     * Xóa một thành viên BQL khỏi danh sách dựa vào CCCD.
     * @param cccd Số CCCD của thành viên cần xóa.
     * @return true nếu xóa thành công, false nếu không tìm thấy.
     */
    public boolean xoaBQL(String cccd) {
        boolean isRemoved = danhSachBQL.removeIf(bql -> bql.getCccd().equals(cccd));
        if (isRemoved) {
            luuFile();
            System.out.println("Xóa thành viên BQL thành công.");
            return true;
        } else {
            System.out.println("Lỗi: Không tìm thấy thành viên BQL có CCCD " + cccd);
            return false;
        }
    }

    /**
     * Tìm kiếm một thành viên BQL trong danh sách dựa vào CCCD.
     * @param cccd Số CCCD của thành viên cần tìm.
     * @return Đối tượng BanQuanLy nếu tìm thấy, ngược lại trả về null.
     */
    public BanQuanLy timKiemTheoCccd(String cccd) {
        return danhSachBQL.stream()
                .filter(bql -> bql.getCccd().equals(cccd))
                .findFirst()
                .orElse(null);
    }

    /**
     * Phương thức private để ghi danh sách BQL hiện tại vào file.
     * Được gọi sau mỗi lần thêm, sửa, hoặc xóa.
     */
    private void luuFile() {
        FileUtil.ghiFile(FILE_NAME, this.danhSachBQL);
    }
}