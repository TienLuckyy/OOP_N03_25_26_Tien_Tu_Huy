package vn.edu.quanlynhatro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.quanlynhatro.model.BanQuanLy;

import java.util.List;

@Repository
public interface BanQuanLyRepository extends JpaRepository<BanQuanLy, Long> {

    // Tìm theo họ tên
    List<BanQuanLy> findByHoTenContainingIgnoreCase(String hoTen);

    // Tìm theo tòa phụ trách
    List<BanQuanLy> findByToaPhuTrach(String toaPhuTrach);
}
