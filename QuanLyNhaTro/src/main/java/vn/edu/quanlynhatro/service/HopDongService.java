package service;

import util.FileUtil;
import vn.edu.model.HopDong;
import vn.edu.model.Phong;
import vn.edu.model.SinhVien;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Lớp HopDongService quản lý các nghiệp vụ liên quan đến hợp đồng thuê phòng.
 * Đây là lớp trung tâm, tương tác với cả SinhVienService và PhongService.
 */
public class HopDongService {

    private List<HopDong> danhSachHopDong;
    private static final String FILE_NAME = "data/hopdong.dat";

    // Các service phụ thuộc để đảm bảo tính toàn vẹn dữ liệu
    private final PhongService phongService;
    private final SinhVienService sinhVienService;

    /**
     * Hàm khởi tạo cho HopDongService.
     * Tải dữ liệu hợp đồng và khởi tạo các service cần thiết khác.
     */
    @SuppressWarnings("unchecked")
    public HopDongService() {
        // Khởi tạo các service phụ thuộc
        this.phongService = new PhongService();
        this.sinhVienService = new SinhVienService();

        // Tải danh sách hợp đồng từ file
        this.danhSachHopDong = (List<HopDong>) FileUtil.docFile(FILE_NAME);
        if (this.danhSachHopDong == null) {
            this.danhSachHopDong = new ArrayList<>();
        }
    }

    /**
     * Trả về toàn bộ danh sách hợp đồng.
     * @return List<HopDong>
     */
    public List<HopDong> getAllHopDong() {
        return this.danhSachHopDong;
    }

    /**
     * Tạo một hợp đồng mới với các bước kiểm tra logic chặt chẽ.
     * @param hopDong Đối tượng hợp đồng mới cần tạo.
     * @return true nếu tạo thành công, false nếu thất bại.
     */
    public boolean taoHopDong(HopDong hopDong) {
        // 1. Kiểm tra mã hợp đồng đã tồn tại chưa
        if (danhSachHopDong.stream().anyMatch(hd -> hd.getMaHopDong().equalsIgnoreCase(hopDong.getMaHopDong()))) {
            System.out.println("Lỗi: Mã hợp đồng '" + hopDong.getMaHopDong() + "' đã tồn tại.");
            return false;
        }

        // 2. Kiểm tra sinh viên có tồn tại không
        SinhVien sv = sinhVienService.timKiemTheoMssv(hopDong.getMaSV());
        if (sv == null) {
            System.out.println("Lỗi: Không tìm thấy sinh viên với MSSV '" + hopDong.getMaSV() + "'.");
            return false;
        }

        // 3. Kiểm tra phòng có tồn tại và còn trống không
        Phong phong = phongService.timKiemTheoSoPhong(hopDong.getMaPhong());
        if (phong == null) {
            System.out.println("Lỗi: Không tìm thấy phòng với số phòng '" + hopDong.getMaPhong() + "'.");
            return false;
        }
        if (phong.isTrangThai()) { // true = đang sử dụng
            System.out.println("Lỗi: Phòng '" + hopDong.getMaPhong() + "' đã có người thuê.");
            return false;
        }

        // --- Nếu tất cả kiểm tra đều hợp lệ ---
        // Thêm hợp đồng vào danh sách
        danhSachHopDong.add(hopDong);
        
        // Cập nhật trạng thái phòng thành "Đang sử dụng"
        phong.setTrangThai(true);
        phongService.suaPhong(phong); // Gọi service của phòng để cập nhật và lưu
        
        // Lưu lại danh sách hợp đồng
        luuFile();
        System.out.println("Tạo hợp đồng thành công cho sinh viên " + sv.getTen() + " tại phòng " + phong.getSoPhong());
        return true;
    }

    /**
     * Hủy một hợp đồng và giải phóng phòng.
     * @param maHopDong Mã của hợp đồng cần hủy.
     * @return true nếu hủy thành công, false nếu không tìm thấy hợp đồng.
     */
    public boolean huyHopDong(String maHopDong) {
        HopDong hopDongCanHuy = timKiemTheoMaHD(maHopDong);

        if (hopDongCanHuy == null) {
            System.out.println("Lỗi: Không tìm thấy hợp đồng có mã '" + maHopDong + "'.");
            return false;
        }

        // Cập nhật trạng thái hợp đồng
        hopDongCanHuy.setTrangThai("Đã hủy");

        // Tìm phòng liên quan và cập nhật trạng thái về "Trống"
        Phong phong = phongService.timKiemTheoSoPhong(hopDongCanHuy.getMaPhong());
        if (phong != null) {
            phong.setTrangThai(false); // false = trống
            phongService.suaPhong(phong);
        }
        
        luuFile();
        System.out.println("Hủy hợp đồng '" + maHopDong + "' thành công.");
        return true;
    }

    /**
     * Tìm một hợp đồng theo mã.
     * @param maHopDong Mã hợp đồng cần tìm.
     * @return Đối tượng HopDong hoặc null nếu không tìm thấy.
     */
    public HopDong timKiemTheoMaHD(String maHopDong) {
        return danhSachHopDong.stream()
                .filter(hd -> hd.getMaHopDong().equalsIgnoreCase(maHopDong))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Tìm tất cả hợp đồng của một sinh viên.
     * @param maSV MSSV của sinh viên.
     * @return Danh sách các hợp đồng tìm được.
     */
    public List<HopDong> timKiemTheoMaSV(String maSV) {
        return danhSachHopDong.stream()
                .filter(hd -> hd.getMaSV().equalsIgnoreCase(maSV))
                .collect(Collectors.toList());
    }

    /**
     * Tìm các hợp đồng còn hiệu lực và sắp hết hạn trong vòng 30 ngày tới.
     * @return Danh sách các hợp đồng sắp hết hạn.
     */
    public List<HopDong> kiemTraHopDongSapHetHan() {
        Date today = new Date();
        return danhSachHopDong.stream()
                .filter(hd -> hd.getTrangThai().equalsIgnoreCase("Đang hiệu lực"))
                .filter(hd -> {
                    long diffInMillis = hd.getNgayKetThuc().getTime() - today.getTime();
                    long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
                    return diffInDays >= 0 && diffInDays <= 30;
                })
                .collect(Collectors.toList());
    }

    /**
     * Ghi danh sách hợp đồng hiện tại vào file.
     */
    private void luuFile() {
        FileUtil.ghiFile(FILE_NAME, this.danhSachHopDong);
    }
}