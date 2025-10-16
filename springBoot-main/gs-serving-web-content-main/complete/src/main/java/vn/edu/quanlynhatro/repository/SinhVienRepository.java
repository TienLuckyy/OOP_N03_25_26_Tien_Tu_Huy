package vn.edu.quanlynhatro.repository; 

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.quanlynhatro.model.SinhVien;

import java.util.List;
import java.util.Optional;

public interface SinhVienRepository extends JpaRepository<SinhVien, Long> {
    
    // Tên hàm theo quy tắc đặt tên của Spring Data JPA
    // Spring sẽ tự động tạo truy vấn SQL: SELECT * FROM sinh_vien WHERE mssv = ?
    Optional<SinhVien> findByMssv(String mssv);
    
    // Tìm kiếm theo tên (ví dụ)
    List<SinhVien> findByHoTenContainingIgnoreCase(String hoTen);
}