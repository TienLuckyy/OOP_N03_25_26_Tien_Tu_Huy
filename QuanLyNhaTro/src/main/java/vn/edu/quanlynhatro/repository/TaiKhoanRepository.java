package vn.edu.quanlynhatro.repository;

import vn.edu.quanlynhatro.model.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Long> {
    Optional<TaiKhoan> findByUsername(String username);
}
