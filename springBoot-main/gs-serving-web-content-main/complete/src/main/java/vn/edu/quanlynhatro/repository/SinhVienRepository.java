package vn.edu.quanlynhatro.repository; 

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.quanlynhatro.model.SinhVien;

import java.util.Optional;

public interface SinhVienRepository extends JpaRepository<SinhVien, Long> {
    
    // Hàm tìm kiếm tùy chỉnh theo MSSV
    Optional<SinhVien> findByMssv(String mssv);
}