package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.Phong;
import vn.edu.quanlynhatro.model.PhongId;
import vn.edu.quanlynhatro.model.SinhVien;
import vn.edu.quanlynhatro.repository.PhongRepository;
import vn.edu.quanlynhatro.repository.SinhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.quanlynhatro.controller.WriteToFile;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PhongService {

    @Autowired
    private PhongRepository phongRepository;

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Autowired
    private WriteToFile writeToFile; // 🔔 Tự động xuất file

    // === CRUD CƠ BẢN ===

    public List<Phong> getAllPhong() {
        return phongRepository.findAll();
    }

    public Optional<Phong> getPhongById(String soPhong, String toa) {
        return phongRepository.findBySoPhongAndToa(soPhong, toa);
    }

    public boolean createPhong(Phong phong) {
        if (phongRepository.existsBySoPhongAndToa(phong.getSoPhong(), phong.getToa())) {
            return false;
        }

        if (phong.getSoNguoiToiDa() == null) phong.setSoNguoiToiDa(4);
        if (phong.getTienNha() == null) phong.setTienNha(0.0);
        phong.setTrangThai(phong.getSoNguoiHienTai() > 0);

        phongRepository.save(phong);
        writeToFile.exportPhongData(); // 🔔 Tự động lưu
        return true;
    }

    public boolean updatePhong(Phong phong) {
        Optional<Phong> phongDbOpt = phongRepository.findById(new PhongId(phong.getSoPhong(), phong.getToa()));
        if (phongDbOpt.isEmpty()) return false;

        Phong phongDb = phongDbOpt.get();
        if (phong.getSoNguoiToiDa() != null) phongDb.setSoNguoiToiDa(phong.getSoNguoiToiDa());
        if (phong.getTienNha() != null) phongDb.setTienNha(phong.getTienNha());

        phongRepository.save(phongDb);
        writeToFile.exportPhongData(); // 🔔 Tự động lưu
        return true;
    }

    public boolean deletePhong(String soPhong, String toa) {
        Optional<Phong> phongOpt = phongRepository.findBySoPhongAndToa(soPhong, toa);
        if (phongOpt.isEmpty() || phongOpt.get().getSoNguoiHienTai() > 0) return false;

        phongRepository.deleteById(new PhongId(soPhong, toa));
        writeToFile.exportPhongData(); // 🔔 Tự động lưu
        return true;
    }

    // === Gán/Xóa sinh viên ===
    public boolean assignStudent(Long sinhVienId, String soPhong, String toa) {
        Optional<Phong> phongOpt = phongRepository.findBySoPhongAndToa(soPhong, toa);
        Optional<SinhVien> svOpt = sinhVienRepository.findById(sinhVienId);
        if (phongOpt.isEmpty() || svOpt.isEmpty()) return false;

        Phong phong = phongOpt.get();
        SinhVien sv = svOpt.get();

        int soNguoiToiDa = phong.getSoNguoiToiDa() != null ? phong.getSoNguoiToiDa() : 4;
        if (phong.getSoNguoiHienTai() >= soNguoiToiDa || sv.getPhong() != null) return false;

        sv.setPhong(phong);
        sinhVienRepository.save(sv);

        phong.setTrangThai(true);
        phongRepository.save(phong);

        writeToFile.exportPhongData(); // 🔔 Tự động lưu
        return true;
    }

    public boolean removeStudent(Long sinhVienId, String soPhong, String toa) {
        Optional<SinhVien> svOpt = sinhVienRepository.findById(sinhVienId);
        Optional<Phong> phongOpt = phongRepository.findBySoPhongAndToa(soPhong, toa);
        if (svOpt.isEmpty() || phongOpt.isEmpty()) return false;

        SinhVien sv = svOpt.get();
        Phong phong = phongOpt.get();
        if (sv.getPhong() == null || !sv.getPhong().equals(phong)) return false;

        sv.setPhong(null);
        sinhVienRepository.save(sv);

        phong.setTrangThai(phong.getSoNguoiHienTai() > 0);
        phongRepository.save(phong);

        writeToFile.exportPhongData(); // 🔔 Tự động lưu
        return true;
    }

    // === Các phương thức khác ===
    public List<Phong> getEmptyRooms() {
        return phongRepository.findPhongConCho();
    }

    public List<Phong> getPhongTheoTrangThai(boolean trangThai) {
        return phongRepository.findByTrangThai(trangThai);
    }

    public List<Phong> getPhongTheoToa(String toa) {
        return phongRepository.findByToa(toa);
    }
}
