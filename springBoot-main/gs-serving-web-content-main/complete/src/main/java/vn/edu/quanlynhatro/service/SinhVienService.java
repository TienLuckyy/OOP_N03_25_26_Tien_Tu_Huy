package vn.edu.quanlynhatro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.quanlynhatro.controller.WriteToFile;
import vn.edu.quanlynhatro.model.Phong;
import vn.edu.quanlynhatro.model.SinhVien;
import vn.edu.quanlynhatro.repository.PhongRepository;
import vn.edu.quanlynhatro.repository.SinhVienRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SinhVienService {

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Autowired
    private PhongRepository phongRepository;

    @Autowired
    private WriteToFile writeToFile; // thêm WriteToFile

    public List<SinhVien> getAll() {
        return sinhVienRepository.findAll();
    }

    public Optional<SinhVien> findById(Long id) {
        return sinhVienRepository.findById(id);
    }

    public SinhVien save(SinhVien sv) {
        SinhVien saved = sinhVienRepository.save(sv);
        writeToFile.exportSinhVienData(); // xuất ra file sau khi lưu
        return saved;
    }

    public Optional<SinhVien> findByMssv(String mssv) {
        return sinhVienRepository.findByMssv(mssv);
    }

    public Optional<SinhVien> findByCccd(String cccd) {
        return sinhVienRepository.findByCccd(cccd);
    }

    public Optional<SinhVien> findBySoDienThoai(String soDienThoai) {
        return sinhVienRepository.findBySoDienThoai(soDienThoai);
    }

    public List<SinhVien> searchByName(String hoTen) {
        return sinhVienRepository.findByHoTenContainingIgnoreCase(hoTen);
    }

    public List<SinhVien> findByPhong(Phong phong) {
        return sinhVienRepository.findByPhong(phong);
    }

    public List<SinhVien> findByPhongIsNull() {
        return sinhVienRepository.findByPhongIsNull();
    }

    public List<SinhVien> findByPhongIn(List<Phong> phongs) {
        return sinhVienRepository.findByPhongIn(phongs);
    }

    public void delete(Long id) {
        Optional<SinhVien> svOptional = sinhVienRepository.findById(id);
        if (svOptional.isPresent()) {
            SinhVien sv = svOptional.get();

            // Xóa liên kết với phòng
            if (sv.getPhong() != null) {
                Phong phong = sv.getPhong();
                phong.getSinhViens().remove(sv);
                phongRepository.save(phong);
            }

            sinhVienRepository.delete(sv);
            writeToFile.exportSinhVienData(); // xuất ra file sau khi xóa
        }
    }

}
