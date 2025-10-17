package vn.edu.quanlynhatro.repository;

import vn.edu.quanlynhatro.model.Phong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PhongRepository extends JpaRepository<Phong, String> {
    
    List<Phong> findByTrangThai(boolean trangThai);
    
    List<Phong> findByToa(String toa);
    
    @Query("SELECT p FROM Phong p WHERE p.soNguoiHienTai < p.soNguoiToiDa")
    List<Phong> findPhongConCho();
    
    List<Phong> findBySoNguoiHienTai(int soNguoiHienTai);
    
    List<Phong> findByTienNhaGreaterThan(double tienNha);
    
    List<Phong> findByToaAndTrangThai(String toa, boolean trangThai);
    
    @Modifying
    @Query("UPDATE Phong p SET p.soNguoiHienTai = :soNguoiMoi WHERE p.soPhong = :soPhong")
    int capNhatSoNguoiHienTai(@Param("soPhong") String soPhong, @Param("soNguoiMoi") int soNguoiMoi);
    
    @Modifying
    @Query("UPDATE Phong p SET p.tienNha = :tienNha WHERE p.soPhong = :soPhong")
    int capNhatTienNha(@Param("soPhong") String soPhong, @Param("tienNha") double tienNha);
    
    @Modifying
    @Query("UPDATE Phong p SET p.trangThai = :trangThai WHERE p.soPhong = :soPhong")
    int capNhatTrangThai(@Param("soPhong") String soPhong, @Param("trangThai") boolean trangThai);
    
    long countByTrangThai(boolean trangThai);
    
    long countByToa(String toa);
}
