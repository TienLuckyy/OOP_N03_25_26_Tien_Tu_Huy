package vn.edu.quanlynhatro.repository; 

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.quanlynhatro.model.Phong;
import vn.edu.quanlynhatro.model.SinhVien;
import java.util.List;
import java.util.Optional;

public interface SinhVienRepository extends JpaRepository<SinhVien, Long> {
    
    // Hàm tìm kiếm tùy chỉnh theo MSSV
    Optional<SinhVien> findByMssv(String mssv);
    List<SinhVien> findByPhong(Phong phong);
    
    // Tìm sinh viên chưa có phòng (phòng là null)
    List<SinhVien> findByPhongIsNull();
}