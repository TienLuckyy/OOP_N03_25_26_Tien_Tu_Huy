package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.Phong;
import vn.edu.quanlynhatro.model.PhongId;
import vn.edu.quanlynhatro.model.SinhVien;
import vn.edu.quanlynhatro.repository.PhongRepository;
import vn.edu.quanlynhatro.repository.SinhVienRepository;
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

    // === CRUD CƠ BẢN ===
    
    // 📖 READ - Lấy tất cả phòng
    public List<Phong> getAllPhong() {
        return phongRepository.findAll();
    }

    // 📖 READ - Tìm phòng theo số phòng và tòa
    public Optional<Phong> getPhongById(String soPhong, String toa) {
        return phongRepository.findBySoPhongAndToa(soPhong, toa);
    }

    // ➕ CREATE - Thêm phòng mới (SỬA LỖI NULL)
    public boolean createPhong(Phong phong) {
        if (phongRepository.existsBySoPhongAndToa(phong.getSoPhong(), phong.getToa())) {
            return false; // Phòng đã tồn tại
        }
        
        // Xử lý giá trị null - set mặc định nếu null
        if (phong.getSoNguoiHienTai() == null) {
            phong.setSoNguoiHienTai(0);
        }
        if (phong.getSoNguoiToiDa() == null) {
            phong.setSoNguoiToiDa(4); // giá trị mặc định
        }
        if (phong.getTienNha() == null) {
            phong.setTienNha(0.0);
        }
        
        phong.setTrangThai(phong.getSoNguoiHienTai() > 0);
        phongRepository.save(phong);
        return true;
    }

    // ✏️ UPDATE - Cập nhật phòng (SỬA LỖI NULL)
    public boolean updatePhong(Phong phong) {
        if (!phongRepository.existsById(new PhongId(phong.getSoPhong(), phong.getToa()))) {
            return false; // Phòng không tồn tại
        }
        
        // Xử lý giá trị null
        if (phong.getSoNguoiHienTai() == null) {
            phong.setSoNguoiHienTai(0);
        }
        if (phong.getSoNguoiToiDa() == null) {
            phong.setSoNguoiToiDa(4);
        }
        if (phong.getTienNha() == null) {
            phong.setTienNha(0.0);
        }
        
        phong.setTrangThai(phong.getSoNguoiHienTai() > 0);
        phongRepository.save(phong);
        return true;
    }

    // ❌ DELETE - Xóa phòng
    public boolean deletePhong(String soPhong, String toa) {
        Optional<Phong> phong = phongRepository.findBySoPhongAndToa(soPhong, toa);
        if (phong.isEmpty() || !phong.get().getSinhViens().isEmpty()) {
            return false; // Không tồn tại hoặc đang có sinh viên
        }
        phongRepository.deleteById(new PhongId(soPhong, toa));
        return true;
    }

    // === BUSINESS METHODS ===

    // 🟩 Lấy phòng còn trống
    public List<Phong> getEmptyRooms() {
        return phongRepository.findPhongConCho();
    }

    // 🟥 Lấy phòng theo trạng thái
    public List<Phong> getPhongTheoTrangThai(boolean trangThai) {
        return phongRepository.findByTrangThai(trangThai);
    }

    // 🏢 Lấy phòng theo tòa
    public List<Phong> getPhongTheoToa(String toa) {
        return phongRepository.findByToa(toa);
    }

    // 👥 Gán sinh viên vào phòng (SỬA LỖI NULL)
    public boolean assignStudent(Long sinhVienId, String soPhong, String toa) {
        Optional<Phong> phongOpt = phongRepository.findBySoPhongAndToa(soPhong, toa);
        Optional<SinhVien> sinhVienOpt = sinhVienRepository.findById(sinhVienId);
        
        if (phongOpt.isEmpty() || sinhVienOpt.isEmpty()) {
            return false;
        }

        Phong phong = phongOpt.get();
        SinhVien sv = sinhVienOpt.get();

        // Kiểm tra điều kiện (xử lý null)
        int soNguoiHienTai = phong.getSoNguoiHienTai() != null ? phong.getSoNguoiHienTai() : 0;
        int soNguoiToiDa = phong.getSoNguoiToiDa() != null ? phong.getSoNguoiToiDa() : 4;
        
        if (soNguoiHienTai >= soNguoiToiDa || sv.getPhong() != null) {
            return false;
        }

        // Thực hiện gán
        sv.setPhong(phong);
        sinhVienRepository.save(sv);

        // Cập nhật số người
        phong.setSoNguoiHienTai(soNguoiHienTai + 1);
        phong.setTrangThai(true);
        phongRepository.save(phong);

        return true;
    }

    // 🗑️ Xóa sinh viên khỏi phòng (SỬA LỖI NULL)
    public boolean removeStudent(Long sinhVienId, String soPhong, String toa) {
        Optional<SinhVien> sinhVienOpt = sinhVienRepository.findById(sinhVienId);
        Optional<Phong> phongOpt = phongRepository.findBySoPhongAndToa(soPhong, toa);
        
        if (sinhVienOpt.isEmpty() || phongOpt.isEmpty()) {
            return false;
        }

        SinhVien sv = sinhVienOpt.get();
        Phong phong = phongOpt.get();

        // Kiểm tra sinh viên có trong phòng này không
        if (sv.getPhong() == null || !sv.getPhong().equals(phong)) {
            return false;
        }

        // Xóa khỏi phòng
        sv.setPhong(null);
        sinhVienRepository.save(sv);

        // Cập nhật số người (xử lý null)
        int soNguoiHienTai = phong.getSoNguoiHienTai() != null ? phong.getSoNguoiHienTai() : 0;
        phong.setSoNguoiHienTai(Math.max(0, soNguoiHienTai - 1));
        phong.setTrangThai(phong.getSoNguoiHienTai() > 0);
        phongRepository.save(phong);

        return true;
    }

    // === COMPATIBILITY METHODS ===
    
    public Optional<Phong> timKiemTheoSoPhongVaToa(String soPhong, String toa) {
        return getPhongById(soPhong, toa);
    }
    
    public Phong layPhongTheoSoPhongVaToa(String soPhong, String toa) {
        return getPhongById(soPhong, toa).orElse(null);
    }
    
    public List<Phong> timKiemPhongTrong() {
        return getEmptyRooms();
    }
    
    public boolean themPhong(Phong phong) {
        return createPhong(phong);
    }
    
    public void suaPhong(Phong phong) {
        updatePhong(phong);
    }
    
    public void xoaPhong(String soPhong, String toa) {
        deletePhong(soPhong, toa);
    }
    
    public boolean ganSinhVienVaoPhong(Long sinhVienId, String soPhong, String toa) {
        return assignStudent(sinhVienId, soPhong, toa);
    }
}