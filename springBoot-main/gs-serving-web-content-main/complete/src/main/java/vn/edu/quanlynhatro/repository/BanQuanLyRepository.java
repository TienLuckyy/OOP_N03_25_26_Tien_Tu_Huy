package vn.edu.quanlynhatro.repository;

import vn.edu.quanlynhatro.model.BanQuanLy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BanQuanLyRepository extends JpaRepository<BanQuanLy, Long> {
    
    // ✅ Tìm theo mã nhân viên
    Optional<BanQuanLy> findByMaNhanVien(String maNhanVien);
    
    // // ✅ Tìm theo tòa phụ trách
    // List<BanQuanLy> findByToaPhuTrach(String toaPhuTrach);
    
    // ✅ Tìm theo tên (tìm kiếm gần đúng)
    List<BanQuanLy> findByHoTenContainingIgnoreCase(String hoTen);
    
    // ✅ Tìm nhân viên quản lý tòa cụ thể hoặc "Tất cả"
    @Query("SELECT b FROM BanQuanLy b WHERE b.toaPhuTrach = :toa OR b.toaPhuTrach = 'Tất cả'")
    List<BanQuanLy> findByToaPhuTrachOrAll(@Param("toa") String toa);
    
    // ✅ Kiểm tra tồn tại mã nhân viên (trừ id hiện tại khi update)
    @Query("SELECT COUNT(b) > 0 FROM BanQuanLy b WHERE b.maNhanVien = :maNhanVien AND b.id != :id")
    boolean existsByMaNhanVienAndIdNot(@Param("maNhanVien") String maNhanVien, @Param("id") Long id);
    
    // ✅ Kiểm tra tồn tại CCCD (trừ id hiện tại khi update)
    @Query("SELECT COUNT(b) > 0 FROM BanQuanLy b WHERE b.cccd = :cccd AND b.id != :id")
    boolean existsByCccdAndIdNot(@Param("cccd") String cccd, @Param("id") Long id);
    
    // ✅ Đếm số nhân viên theo tòa
    @Query("SELECT b.toaPhuTrach, COUNT(b) FROM BanQuanLy b GROUP BY b.toaPhuTrach")
    List<Object[]> countByToaPhuTrach();
    

    
        // // ✅ Tìm nhân viên theo số điện thoại
    Optional<BanQuanLy> findBySoDienThoai(String soDienThoai);
        
    // ✅ Kiểm tra tồn tại SĐT (trừ id hiện tại khi update)
    boolean existsBySoDienThoaiAndIdNot(String soDienThoai, Long id);

    // ✅ Tìm nhân viên theo CCCD
        Optional<BanQuanLy> findByCccd(String cccd);

    
}