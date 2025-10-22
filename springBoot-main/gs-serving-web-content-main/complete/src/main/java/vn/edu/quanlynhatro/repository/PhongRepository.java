package vn.edu.quanlynhatro.repository;

import vn.edu.quanlynhatro.model.Phong;
import vn.edu.quanlynhatro.model.PhongId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhongRepository extends JpaRepository<Phong, PhongId> {

    // 🔹 Lấy phòng theo trạng thái (true = đang sử dụng, false = còn trống)
    List<Phong> findByTrangThai(boolean trangThai);

    // 🔹 Lấy phòng theo tòa
    List<Phong> findByToa(String toa);

    // 🔹 Lấy các phòng còn chỗ trống (soNguoiHienTai < soNguoiToiDa)
    // SỬA LỖI 1: Thay thế p.soNguoiHienTai bằng SIZE(p.sinhViens)
    @Query("SELECT p FROM Phong p WHERE SIZE(p.sinhViens) < p.soNguoiToiDa")
    List<Phong> findPhongConCho();

    // 🔹 Lấy phòng theo số người hiện tại
    // SỬA LỖI 2: Phương thức 'findBy' tự động sẽ lỗi vì không có trường soNguoiHienTai.
    // Phải viết lại bằng @Query và dùng SIZE(p.sinhViens).
    @Query("SELECT p FROM Phong p WHERE SIZE(p.sinhViens) = :soNguoiHienTai")
    List<Phong> findBySoNguoiHienTai(@Param("soNguoiHienTai") int soNguoiHienTai);

    // 🔹 Lấy phòng có tiền nhà lớn hơn giá trị
    // List<Phong> findByTienNhaGreaterThan(double tienNha);

    // 🔹 Lấy phòng theo tòa và trạng thái
    List<Phong> findByToaAndTrangThai(String toa, boolean trangThai);

    // 🔹 Kiểm tra tồn tại phòng theo soPhong + toa
    boolean existsBySoPhongAndToa(String soPhong, String toa);

    // 🔹 Tìm phòng theo soPhong + toa
    Optional<Phong> findBySoPhongAndToa(String soPhong, String toa);

    // SỬA LỖI 3: XÓA PHƯƠNG THỨC NÀY
    // Chúng ta không bao giờ cập nhật "số người" thủ công nữa.
    // Dữ liệu này được tự động đếm.
    /*
    @Modifying
    @Query("UPDATE Phong p SET p.soNguoiHienTai = :soNguoiMoi WHERE p.soPhong = :soPhong AND p.toa = :toa")
    int capNhatSoNguoiHienTai(@Param("soPhong") String soPhong,
                              @Param("toa") String toa,
                              @Param("soNguoiMoi") int soNguoiMoi);
    */

    // 🔹 Cập nhật tiền nhà
    @Modifying
    @Query("UPDATE Phong p SET p.tienNha = :tienNha WHERE p.soPhong = :soPhong AND p.toa = :toa")
    int capNhatTienNha(@Param("soPhong") String soPhong,
                       @Param("toa") String toa,
                       @Param("tienNha") double tienNha);

    // 🔹 Cập nhật trạng thái phòng
    @Modifying
    @Query("UPDATE Phong p SET p.trangThai = :trangThai WHERE p.soPhong = :soPhong AND p.toa = :toa")
    int capNhatTrangThai(@Param("soPhong") String soPhong,
                         @Param("toa") String toa,
                         @Param("trangThai") boolean trangThai);

    // 🔹 Thống kê phòng theo trạng thái
    long countByTrangThai(boolean trangThai);

    // 🔹 Thống kê phòng theo tòa
    long countByToa(String toa);
}