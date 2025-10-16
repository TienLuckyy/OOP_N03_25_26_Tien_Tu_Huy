package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.SinhVien;
import vn.edu.quanlynhatro.repository.SinhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SinhVienService {

    @Autowired
    private SinhVienRepository sinhVienRepository;

    public List<SinhVien> getAllSinhVien() {
        return sinhVienRepository.findAll(); 
    }

    public Optional<SinhVien> timKiemTheoId(Long id) {
        return sinhVienRepository.findById(id); 
    }

    public SinhVien luuHoacSuaSinhVien(SinhVien sv) {
        return sinhVienRepository.save(sv);
    }
    
    public boolean xoaSinhVien(Long id) {
        if (sinhVienRepository.existsById(id)) {
            sinhVienRepository.deleteById(id); 
            return true;
        }
        return false;
    }
    
    // Dùng cho chức năng tìm kiếm
    public SinhVien timKiemTheoMssv(String mssv) {
        // Tìm theo MSSV và trả về SinhVien, nếu không tìm thấy trả về null
        return sinhVienRepository.findByMssv(mssv).orElse(null); 
    }
}