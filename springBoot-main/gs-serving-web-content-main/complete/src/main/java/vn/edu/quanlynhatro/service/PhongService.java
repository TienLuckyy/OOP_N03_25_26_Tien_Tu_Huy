package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.Phong;
import vn.edu.quanlynhatro.model.PhongId;
import vn.edu.quanlynhatro.model.SinhVien;
import vn.edu.quanlynhatro.repository.PhongRepository;
import vn.edu.quanlynhatro.repository.SinhVienRepository;
import vn.edu.quanlynhatro.exception.ResourceNotFoundException;
import vn.edu.quanlynhatro.exception.ResourceInUseException;
import vn.edu.quanlynhatro.controller.WriteToFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private WriteToFile writeToFile;

    // =============================
    // 🔹 LẤY DANH SÁCH PHÒNG
    // =============================
    public List<Phong> getAllPhong() {
        return phongRepository.findAll();
    }

    // =============================
    // 🔹 TÌM PHÒNG THEO ID
    // =============================
    public Optional<Phong> getPhongById(String soPhong, String toa) {
        return phongRepository.findBySoPhongAndToa(soPhong, toa);
    }

    // =============================
    // 🔹 TẠO PHÒNG MỚI
    // =============================
    public boolean createPhong(Phong phong) {
        if (phongRepository.existsBySoPhongAndToa(phong.getSoPhong(), phong.getToa())) {
            return false;
        }

        if (phong.getSoNguoiToiDa() == null) phong.setSoNguoiToiDa(4);
        if (phong.getTienNha() == null) phong.setTienNha(0.0);
        phong.setTrangThai(phong.getSoNguoiHienTai() > 0);

        phongRepository.save(phong);
        writeToFile.exportPhongData();
        return true;
    }

    // =============================
    // 🔹 CẬP NHẬT THÔNG TIN PHÒNG
    // =============================
    public boolean updatePhong(Phong phong) {
        PhongId id = new PhongId(phong.getSoPhong(), phong.getToa());
        Optional<Phong> phongDbOpt = phongRepository.findById(id);
        if (phongDbOpt.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy phòng: " + phong.getSoPhong() + " - " + phong.getToa());
        }

        Phong phongDb = phongDbOpt.get();
        if (phong.getSoNguoiToiDa() != null)
            phongDb.setSoNguoiToiDa(phong.getSoNguoiToiDa());
        if (phong.getTienNha() != null)
            phongDb.setTienNha(phong.getTienNha());

        phongRepository.save(phongDb);
        writeToFile.exportPhongData();
        return true;
    }

    // =============================
    // 🔹 XÓA PHÒNG (có kiểm tra sinh viên)
    // =============================
    public void deletePhong(String soPhong, String toa) {
        PhongId id = new PhongId(soPhong, toa);
        Optional<Phong> phongOpt = phongRepository.findById(id);

        if (phongOpt.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy phòng: " + soPhong + " - " + toa);
        }

        Phong phong = phongOpt.get();

        // ❌ Nếu phòng vẫn còn sinh viên
        if (phong.getSinhViens() != null && !phong.getSinhViens().isEmpty()) {
            throw new ResourceInUseException("Phòng " + soPhong + " - " + toa + " vẫn còn sinh viên, không thể xóa.");
        }

        phongRepository.delete(phong);
        writeToFile.exportPhongData();
    }

    // =============================
    // 🔹 GÁN SINH VIÊN VÀO PHÒNG
    // =============================
    public boolean assignStudent(Long sinhVienId, String soPhong, String toa) {
        Optional<Phong> phongOpt = phongRepository.findBySoPhongAndToa(soPhong, toa);
        Optional<SinhVien> svOpt = sinhVienRepository.findById(sinhVienId);

        if (phongOpt.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy phòng: " + soPhong + " - " + toa);
        }
        if (svOpt.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy sinh viên ID: " + sinhVienId);
        }

        Phong phong = phongOpt.get();
        SinhVien sv = svOpt.get();

        int soNguoiToiDa = (phong.getSoNguoiToiDa() != null) ? phong.getSoNguoiToiDa() : 4;
        if (phong.getSoNguoiHienTai() >= soNguoiToiDa) {
            throw new ResourceInUseException("Phòng " + soPhong + " - " + toa + " đã đầy chỗ.");
        }

        sv.setPhong(phong);
        sinhVienRepository.save(sv);

        phong.setTrangThai(true);
        phongRepository.save(phong);

        writeToFile.exportPhongData();
        return true;
    }

    // =============================
    // 🔹 XÓA SINH VIÊN KHỎI PHÒNG
    // =============================
    public boolean removeStudent(Long sinhVienId, String soPhong, String toa) {
        Optional<SinhVien> svOpt = sinhVienRepository.findById(sinhVienId);
        Optional<Phong> phongOpt = phongRepository.findBySoPhongAndToa(soPhong, toa);

        if (svOpt.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy sinh viên ID: " + sinhVienId);
        }
        if (phongOpt.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy phòng: " + soPhong + " - " + toa);
        }

        SinhVien sv = svOpt.get();
        Phong phong = phongOpt.get();

        if (sv.getPhong() == null || !sv.getPhong().equals(phong)) {
            throw new ResourceInUseException("Sinh viên không thuộc phòng này, không thể xóa.");
        }

        sv.setPhong(null);
        sinhVienRepository.save(sv);

        phong.setTrangThai(phong.getSoNguoiHienTai() > 0);
        phongRepository.save(phong);

        writeToFile.exportPhongData();
        return true;
    }

    // =============================
    // 🔹 PHÒNG CÒN TRỐNG / THEO TRẠNG THÁI / THEO TÒA
    // =============================
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
