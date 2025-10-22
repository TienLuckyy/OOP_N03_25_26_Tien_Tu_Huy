package vn.edu.quanlynhatro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.quanlynhatro.model.Phong;
import vn.edu.quanlynhatro.model.SinhVien;

import java.util.List;
import java.util.Optional;
@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien, Long> {

    Optional<SinhVien> findByMssv(String mssv);

    Optional<SinhVien> findByCccd(String cccd);  // 🔹 Thêm method này

    Optional<SinhVien> findBySoDienThoai(String soDienThoai);

    List<SinhVien> findByHoTenContainingIgnoreCase(String hoTen);

    List<SinhVien> findByLop(String lop);

    List<SinhVien> findByNganhHoc(String nganhHoc);

    List<SinhVien> findByPhong(Phong phong);

    List<SinhVien> findByPhongIsNull();

    List<SinhVien> findByPhongIn(List<Phong> phongs);

   
}
