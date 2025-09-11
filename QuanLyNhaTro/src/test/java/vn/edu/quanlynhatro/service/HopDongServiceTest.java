package vn.edu.quanlynhatro.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import vn.edu.quanlynhatro.service.HopDongService;
import vn.edu.quanlynhatro.service.PhongService;
import vn.edu.quanlynhatro.service.SinhVienService;
import vn.edu.quanlynhatro.model.HopDong;
import vn.edu.quanlynhatro.model.Phong;
import vn.edu.quanlynhatro.model.SinhVien;

/**
 * Lớp này dùng để kiểm thử (test) các logic nghiệp vụ của HopDongService.
 * Mỗi test case sẽ được chạy trong một môi trường độc lập.
 */
class HopDongServiceTest {

    private HopDongService hopDongService;
    private PhongService phongService;
    private SinhVienService sinhVienService;

    /**
     * Phương thức này chạy TRƯỚC MỖI test case.
     * Dùng để khởi tạo một môi trường "sạch" và dữ liệu giả (mock data)
     * để đảm bảo các test không ảnh hưởng lẫn nhau.
     */
    @BeforeEach
    void setUp() {
        // Xóa dữ liệu cũ trước khi chạy test để đảm bảo môi trường sạch
        tearDown();

        // Khởi tạo các service
        phongService = new PhongService();
        sinhVienService = new SinhVienService();
        hopDongService = new HopDongService();

        // --- Tạo dữ liệu giả cho việc test ---
        // 1. Tạo một sinh viên
        sinhVienService.themSinhVien(new SinhVien("SV01", "Nguyễn Văn An", "Hà Nội", new Date(), "Nam", "0911111111", "111", "CNTT1", "KTPM"));

        // 2. Tạo một phòng còn trống
        phongService.themPhong(new Phong("P101", false, 0)); // false = trống

        // 3. Tạo một phòng đã được thuê
        phongService.themPhong(new Phong("P102", true, 0)); // true = đã thuê
    }

    /**
     * Phương thức này chạy SAU MỖI test case.
     * Dùng để dọn dẹp tài nguyên, xóa các file dữ liệu đã tạo ra trong quá trình test.
     */
    @AfterEach
    void tearDown() {
        new File("data/phong.dat").delete();
        new File("data/sinhvien.dat").delete();
        new File("data/hopdong.dat").delete();
    }

    @Test
    void testTaoHopDong_ThanhCong() {
        // Arrange: Chuẩn bị dữ liệu đầu vào cho một kịch bản thành công
        HopDong hopDongMoi = new HopDong("HD01", "SV01", "P101", new Date(), new Date(), 2000000, "Đang hiệu lực");

        // Act: Thực thi phương thức cần test
        boolean ketQua = hopDongService.taoHopDong(hopDongMoi);

        // Assert: Kiểm tra kết quả có đúng như mong đợi không
        assertTrue(ketQua, "Tạo hợp đồng phải thành công khi phòng trống và sinh viên tồn tại.");
        
        // Kiểm tra tác dụng phụ (side-effect): Trạng thái phòng phải được cập nhật
        Phong phongSauKhiThue = phongService.timKiemTheoSoPhong("P101");
        assertTrue(phongSauKhiThue.isTrangThai(), "Trạng thái phòng phải là 'true' (đang sử dụng) sau khi tạo hợp đồng.");
    }

    @Test
    void testTaoHopDong_ThatBai_KhiPhongDaThue() {
        // Arrange: Cố gắng tạo hợp đồng cho phòng P102 (đã được thuê sẵn)
        HopDong hopDongMoi = new HopDong("HD02", "SV01", "P102", new Date(), new Date(), 2500000, "Đang hiệu lực");

        // Act
        boolean ketQua = hopDongService.taoHopDong(hopDongMoi);

        // Assert
        assertFalse(ketQua, "Tạo hợp đồng phải thất bại khi phòng đã có người thuê.");
        assertTrue(hopDongService.getAllHopDong().isEmpty(), "Không được phép tạo hợp đồng mới vào danh sách.");
    }

    @Test
    void testTaoHopDong_ThatBai_KhiSinhVienKhongTonTai() {
        // Arrange: Cố gắng tạo hợp đồng cho sinh viên không tồn tại (SV999)
        HopDong hopDongMoi = new HopDong("HD03", "SV999", "P101", new Date(), new Date(), 2000000, "Đang hiệu lực");

        // Act
        boolean ketQua = hopDongService.taoHopDong(hopDongMoi);

        // Assert
        assertFalse(ketQua, "Tạo hợp đồng phải thất bại khi sinh viên không tồn tại.");
    }

    @Test
    void testHuyHopDong_ThanhCong() {
        // Arrange: Đầu tiên phải tạo thành công một hợp đồng
        HopDong hopDong = new HopDong("HD01", "SV01", "P101", new Date(), new Date(), 2000000, "Đang hiệu lực");
        hopDongService.taoHopDong(hopDong);

        // Act: Thực hiện hủy hợp đồng vừa tạo
        boolean ketQuaHuy = hopDongService.huyHopDong("HD01");

        // Assert:
        assertTrue(ketQuaHuy, "Hủy hợp đồng phải thành công khi mã hợp đồng tồn tại.");
        
        // Kiểm tra tác dụng phụ: Trạng thái phòng phải được trả về "trống"
        Phong phongSauKhiHuy = phongService.timKiemTheoSoPhong("P101");
        assertFalse(phongSauKhiHuy.isTrangThai(), "Phòng phải trở về trạng thái 'false' (trống) sau khi hủy hợp đồng.");
        
        // Kiểm tra trạng thái hợp đồng đã được cập nhật
        assertEquals("Đã hủy", hopDongService.timKiemTheoMaHD("HD01").getTrangThai());
    }
    
    @Test
    void testKiemTraHopDongSapHetHan_ChinhXac() {
        // Arrange: Tạo 2 hợp đồng, 1 sắp hết hạn, 1 còn dài hạn
        Calendar cal = Calendar.getInstance();
        
        // Hợp đồng hết hạn trong 15 ngày tới
        cal.add(Calendar.DAY_OF_MONTH, 15);
        Date ngayHetHanGan = cal.getTime();
        HopDong hopDongGan = new HopDong("HD_GAN", "SV01", "P101", new Date(), ngayHetHanGan, 1, "Đang hiệu lực");
        hopDongService.taoHopDong(hopDongGan);

        // Hợp đồng hết hạn trong 45 ngày tới (phải thuê phòng khác)
        phongService.themPhong(new Phong("P103", false, 0));
        cal.add(Calendar.DAY_OF_MONTH, 30); // Thêm 30 ngày nữa, tổng là 45
        Date ngayHetHanXa = cal.getTime();
        HopDong hopDongXa = new HopDong("HD_XA", "SV01", "P103", new Date(), ngayHetHanXa, 1, "Đang hiệu lực");
        hopDongService.taoHopDong(hopDongXa);

        // Act: Gọi phương thức kiểm tra
        List<HopDong> danhSachSapHetHan = hopDongService.kiemTraHopDongSapHetHan();
        
        // Assert:
        assertEquals(1, danhSachSapHetHan.size(), "Chỉ tìm thấy 1 hợp đồng sắp hết hạn (trong 30 ngày).");
        assertEquals("HD_GAN", danhSachSapHetHan.get(0).getMaHopDong(), "Hợp đồng tìm thấy phải là hợp đồng sắp hết hạn.");
    }
}