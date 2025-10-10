package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.Phong;
import vn.edu.quanlynhatro.repository.PhongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PhongService {
    
    @Autowired
    private PhongRepository phongRepository;

    public Phong themPhong(Phong phong) { return phongRepository.save(phong); }
    public Phong suaPhong(Phong phong) { return phongRepository.save(phong); } 
    public void xoaPhong(String soPhong) { phongRepository.deleteById(soPhong); }
    public Optional<Phong> timKiemTheoSoPhong(String soPhong) { return phongRepository.findById(soPhong); }
    public List<Phong> timKiemPhongTrong() { return phongRepository.findByTrangThai(false); }
    public List<Phong> getAllPhong() { return phongRepository.findAll(); }
}