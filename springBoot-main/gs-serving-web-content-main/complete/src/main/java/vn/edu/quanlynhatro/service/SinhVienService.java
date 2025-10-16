package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.SinhVien;
import vn.edu.quanlynhatro.repository.SinhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SinhVienService {

    // Dependency Injection: Sử dụng Repository để thao tác với CSDL
    @Autowired
    private SinhVienRepository sinhVienRepository;

    // ---------------------- READ ----------------------

    // Lấy tất cả sinh viên từ CSDL
    public List<SinhVien> getAllSinhVien() {
        // JPA: Gọi phương thức findAll() đã có sẵn
        return sinhVienRepository.findAll(); 
    }

    // Tìm kiếm theo ID (Khóa chính)
    public Optional<SinhVien> timKiemTheoId(Long id) {
        // JPA: findById() trả về Optional
        return sinhVienRepository.findById(id); 
    }

    // Tìm kiếm theo MSSV (Cần thêm hàm tùy chỉnh vào Repository)
    public SinhVien timKiemTheoMssv(String mssv) {
        // Cần định nghĩa 'findByMssv' trong SinhVienRepository
        // Ví dụ: return sinhVienRepository.findByMssv(mssv).orElse(null); 
        return null; // Giữ tạm thời, cần bổ sung logic tìm kiếm
    }

    // ---------------------- CREATE / UPDATE ----------------------

    public SinhVien luuHoacSuaSinhVien(SinhVien sv) {
        // JPA: Phương thức save() dùng cho cả tạo mới (id=null) và cập nhật (id có sẵn)
        return sinhVienRepository.save(sv);
    }

    // ---------------------- DELETE ----------------------

    public boolean xoaSinhVien(Long id) {
        if (sinhVienRepository.existsById(id)) {
            sinhVienRepository.deleteById(id); // Xóa theo ID
            return true;
        }
        return false;
    }
}