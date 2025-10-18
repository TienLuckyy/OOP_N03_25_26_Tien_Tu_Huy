package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.controller.WriteToFile;
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
    private WriteToFile writeToFile;

    @Autowired
    private SinhVienRepository sinhVienRepository;

    // 🏠 Lấy tất cả phòng
    public List<Phong> getAllPhong() {
        return phongRepository.findAll();
    }

    // 🔍 Tìm phòng theo (soPhong + toa)
    public Optional<Phong> timKiemTheoSoPhongVaToa(String soPhong, String toa) {
        return phongRepository.findById(new PhongId(soPhong, toa));
    }

    // 🔍 Lấy phòng theo khóa kép
    public Phong layPhongTheoSoPhongVaToa(String soPhong, String toa) {
        return phongRepository.findBySoPhongAndToa(soPhong, toa).orElse(null);
    }

    // 🟩 Lấy phòng còn trống (soNguoiHienTai < soNguoiToiDa)
    public List<Phong> timKiemPhongTrong() {
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

    // ✅ Kiểm tra trùng phòng (soPhong + toa)
    public boolean kiemTraTrungPhong(String soPhong, String toa) {
        return phongRepository.existsBySoPhongAndToa(soPhong, toa);
    }

    // ➕ Thêm phòng (không ném exception)
    public boolean themPhong(Phong phong) {
        if (kiemTraTrungPhong(phong.getSoPhong(), phong.getToa())) {
            return false; // báo là trùng
        }
        phongRepository.save(phong);
        writeToFile.exportPhongData(); // ghi ra file
        return true;
    }

    // ✏️ Sửa phòng
    public void suaPhong(Phong phong) {
        phongRepository.save(phong);
        writeToFile.exportPhongData();
    }

    // ❌ Xóa phòng
    public void xoaPhong(String soPhong, String toa) {
        phongRepository.deleteById(new PhongId(soPhong, toa));
        writeToFile.exportPhongData();
    }

    // 🔁 Cập nhật số người hiện tại
    public void capNhatSoNguoiHienTai(String soPhong, String toa, int soNguoiMoi) {
        phongRepository.capNhatSoNguoiHienTai(soPhong, toa, soNguoiMoi);
    }

    // 🔁 Cập nhật tiền nhà
    public void capNhatTienNha(String soPhong, String toa, double tienNha) {
        phongRepository.capNhatTienNha(soPhong, toa, tienNha);
    }

    // 🔁 Cập nhật trạng thái phòng
    public void capNhatTrangThai(String soPhong, String toa, boolean trangThai) {
        phongRepository.capNhatTrangThai(soPhong, toa, trangThai);
    }

    // 📊 Thống kê
    public long demPhongTheoTrangThai(boolean trangThai) {
        return phongRepository.countByTrangThai(trangThai);
    }

    public long demPhongTheoToa(String toa) {
        return phongRepository.countByToa(toa);
    }
    

    public boolean ganSinhVienVaoPhong(Long sinhVienId, String soPhong, String toa) {
        Phong phong = layPhongTheoSoPhongVaToa(soPhong, toa);
        if (phong == null) return false;

        // Kiểm tra phòng đã đầy chưa
        if (phong.getSoNguoiHienTai() >= phong.getSoNguoiToiDa()) {
            return false; // Phòng đã đầy
        }

        Optional<SinhVien> sinhVienOpt = sinhVienRepository.findById(sinhVienId);
        if (sinhVienOpt.isEmpty()) return false;

        SinhVien sv = sinhVienOpt.get();
        
        // Kiểm tra sinh viên đã có phòng chưa
        if (sv.getPhong() != null) {
            return false; // Sinh viên đã có phòng
        }

        sv.setPhong(phong); // gán sinh viên vào phòng
        sinhVienRepository.save(sv);

        // cập nhật số người hiện tại và trạng thái phòng
        int soNguoiMoi = phong.getSoNguoiHienTai() + 1;
        capNhatSoNguoiHienTai(soPhong, toa, soNguoiMoi);
        capNhatTrangThai(soPhong, toa, soNguoiMoi > 0);

        return true;
    }
}
