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

    List<Phong> findByTrangThai(boolean trangThai);

    List<Phong> findByToa(String toa);

    @Query("SELECT p FROM Phong p WHERE p.soNguoiHienTai < p.soNguoiToiDa")
    List<Phong> findPhongConCho();

    List<Phong> findBySoNguoiHienTai(int soNguoiHienTai);

    List<Phong> findByTienNhaGreaterThan(double tienNha);

    List<Phong> findByToaAndTrangThai(String toa, boolean trangThai);

    // ✅ Kiểm tra trùng (soPhong + toa)
    boolean existsBySoPhongAndToa(String soPhong, String toa);

    // ✅ Tìm phòng theo (soPhong, toa)
    Optional<Phong> findBySoPhongAndToa(String soPhong, String toa);

    // ✅ Cập nhật theo khóa kép
    @Modifying
    @Query("UPDATE Phong p SET p.soNguoiHienTai = :soNguoiMoi WHERE p.soPhong = :soPhong AND p.toa = :toa")
    int capNhatSoNguoiHienTai(@Param("soPhong") String soPhong,
                               @Param("toa") String toa,
                               @Param("soNguoiMoi") int soNguoiMoi);

    @Modifying
    @Query("UPDATE Phong p SET p.tienNha = :tienNha WHERE p.soPhong = :soPhong AND p.toa = :toa")
    int capNhatTienNha(@Param("soPhong") String soPhong,
                       @Param("toa") String toa,
                       @Param("tienNha") double tienNha);

    @Modifying
    @Query("UPDATE Phong p SET p.trangThai = :trangThai WHERE p.soPhong = :soPhong AND p.toa = :toa")
    int capNhatTrangThai(@Param("soPhong") String soPhong,
                         @Param("toa") String toa,
                         @Param("trangThai") boolean trangThai);

    long countByTrangThai(boolean trangThai);
    long countByToa(String toa);
}
