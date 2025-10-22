package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.BanQuanLy;
import vn.edu.quanlynhatro.model.Phong;
import vn.edu.quanlynhatro.model.SinhVien;
import vn.edu.quanlynhatro.repository.BanQuanLyRepository;
import vn.edu.quanlynhatro.repository.PhongRepository;
import vn.edu.quanlynhatro.repository.SinhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class BanQuanLyService {
    
    @Autowired
    private BanQuanLyRepository banQuanLyRepository;
    
    @Autowired
    private PhongRepository phongRepository;
    
    @Autowired
    private SinhVienRepository sinhVienRepository;
    
    // ✅ CREATE - Thêm ban quản lý mới
    public BanQuanLy createBanQuanLy(BanQuanLy banQuanLy) {
        // Kiểm tra mã nhân viên trùng
        if (banQuanLyRepository.findByMaNhanVien(banQuanLy.getMaNhanVien()).isPresent()) {
            throw new RuntimeException("Mã nhân viên đã tồn tại: " + banQuanLy.getMaNhanVien());
        }

        // Kiểm tra CCCD trùng
        if (banQuanLyRepository.findByCccd(banQuanLy.getCccd()).isPresent()) {
            throw new RuntimeException("CCCD đã tồn tại: " + banQuanLy.getCccd());
        }

        // Kiểm tra số điện thoại trùng
        if (banQuanLyRepository.findBySoDienThoai(banQuanLy.getSoDienThoai()).isPresent()) {
            throw new RuntimeException("Số điện thoại đã tồn tại: " + banQuanLy.getSoDienThoai());
        }

        return banQuanLyRepository.save(banQuanLy);
    }
    
    // ✅ READ - Lấy tất cả ban quản lý
    public List<BanQuanLy> getAllBanQuanLy() {
        return banQuanLyRepository.findAll();
    }
    
    // ✅ READ - Tìm theo ID
    public Optional<BanQuanLy> getBanQuanLyById(Long id) {
        return banQuanLyRepository.findById(id);
    }
    
    // ✅ READ - Tìm theo mã nhân viên
    public Optional<BanQuanLy> getBanQuanLyByMaNhanVien(String maNhanVien) {
        return banQuanLyRepository.findByMaNhanVien(maNhanVien);
    }
    
    // ✅ UPDATE - Cập nhật ban quản lý
    // ✅ UPDATE - Cập nhật ban quản lý
public BanQuanLy updateBanQuanLy(Long id, BanQuanLy banQuanLyUpdate) {
    BanQuanLy existing = banQuanLyRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Không tìm thấy ban quản lý với ID: " + id));

    // Kiểm tra mã nhân viên trùng (trừ bản ghi hiện tại)
    if (banQuanLyRepository.existsByMaNhanVienAndIdNot(banQuanLyUpdate.getMaNhanVien(), id)) {
        throw new RuntimeException("Mã nhân viên đã tồn tại: " + banQuanLyUpdate.getMaNhanVien());
    }

    // Kiểm tra CCCD trùng (trừ bản ghi hiện tại)
    if (banQuanLyRepository.existsByCccdAndIdNot(banQuanLyUpdate.getCccd(), id)) {
        throw new RuntimeException("CCCD đã tồn tại: " + banQuanLyUpdate.getCccd());
    }

    // Kiểm tra số điện thoại trùng (trừ bản ghi hiện tại)
    if (banQuanLyRepository.existsBySoDienThoaiAndIdNot(banQuanLyUpdate.getSoDienThoai(), id)) {
        throw new RuntimeException("Số điện thoại đã tồn tại: " + banQuanLyUpdate.getSoDienThoai());
    }

    // Cập nhật thông tin
    existing.setHoTen(banQuanLyUpdate.getHoTen());
    existing.setGioiTinh(banQuanLyUpdate.getGioiTinh());
    existing.setCccd(banQuanLyUpdate.getCccd());
    existing.setSoDienThoai(banQuanLyUpdate.getSoDienThoai());
    existing.setNgaySinh(banQuanLyUpdate.getNgaySinh());
    existing.setDiaChi(banQuanLyUpdate.getDiaChi());
    existing.setToaPhuTrach(banQuanLyUpdate.getToaPhuTrach());
    existing.setMaNhanVien(banQuanLyUpdate.getMaNhanVien());

    return banQuanLyRepository.save(existing);
}

    
    // ✅ DELETE - Xóa ban quản lý
// ✅ DELETE - Xóa ban quản lý (giữ lại biến cho thông báo)
public void deleteBanQuanLy(Long id) {
    BanQuanLy nhanVien = banQuanLyRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Không tìm thấy ban quản lý với ID: " + id));
    
    // Kiểm tra xem nhân viên có đang quản lý phòng có sinh viên không
    List<Phong> phongQuanLy = getPhongQuanLyByNhanVien(id);
    boolean coSinhVien = phongQuanLy.stream()
        .anyMatch(phong -> !phong.getSinhViens().isEmpty());
        
    if (coSinhVien) {
        long tongSinhVien = phongQuanLy.stream()
            .mapToInt(phong -> phong.getSinhViens().size())
            .sum();
        // ✅ SỬ DỤNG biến nhanVien trong thông báo lỗi
        throw new RuntimeException("Không thể xóa nhân viên " + nhanVien.getHoTen() + 
            " đang quản lý " + tongSinhVien + " sinh viên. Hãy chuyển sinh viên ra khỏi phòng trước.");
    }
    
    banQuanLyRepository.deleteById(id);
}
    
    // 🎯 Lấy danh sách phòng mà nhân viên quản lý (THEO TOA)
    public List<Phong> getPhongQuanLyByNhanVien(Long nhanVienId) {
        BanQuanLy nhanVien = banQuanLyRepository.findById(nhanVienId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));
        
        if ("Tất cả".equals(nhanVien.getToaPhuTrach())) {
            return phongRepository.findAll();
        }
        return phongRepository.findByToa(nhanVien.getToaPhuTrach());
    }
    
    // 🎯 Lấy danh sách sinh viên mà nhân viên quản lý
    public List<SinhVien> getSinhVienQuanLyByNhanVien(Long nhanVienId) {
        return getPhongQuanLyByNhanVien(nhanVienId).stream()
            .flatMap(phong -> phong.getSinhViens().stream())
            .toList();
    }
    
    // 🎯 Thống kê cho nhân viên - SỬA LẠI CHO PHÙ HỢP
    public Map<String, Object> getThongKeNhanVien(Long nhanVienId) {
        List<Phong> phongQuanLy = getPhongQuanLyByNhanVien(nhanVienId);
        List<SinhVien> sinhVienQuanLy = getSinhVienQuanLyByNhanVien(nhanVienId);
        
        Map<String, Object> thongKe = new HashMap<>();
        thongKe.put("tongPhong", phongQuanLy.size());
        thongKe.put("tongSinhVien", sinhVienQuanLy.size());
        
        // ✅ SỬA: Kiểm tra phòng trống bằng logic thay vì method
        long phongTrong = phongQuanLy.stream()
            .filter(phong -> phong.getSoNguoiHienTai() < phong.getSoNguoiToiDa())
            .count();
        long phongDay = phongQuanLy.stream()
            .filter(phong -> phong.getSoNguoiHienTai() >= phong.getSoNguoiToiDa())
            .count();
            
        thongKe.put("phongTrong", phongTrong);
        thongKe.put("phongDay", phongDay);
        
        // Tính tỷ lệ sử dụng
        int tongSoNguoiToiDa = phongQuanLy.stream().mapToInt(Phong::getSoNguoiToiDa).sum();
        double tyLeSuDung = tongSoNguoiToiDa > 0 ? 
            (double) sinhVienQuanLy.size() / tongSoNguoiToiDa * 100 : 0;
        thongKe.put("dienTichSuDung", String.format("%.1f%%", tyLeSuDung));
        
        return thongKe;
    }
    
    // 🎯 Tìm kiếm ban quản lý
    public List<BanQuanLy> searchBanQuanLy(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllBanQuanLy();
        }
        return banQuanLyRepository.findByHoTenContainingIgnoreCase(keyword);
    }
    
    // 🎯 Lấy nhân viên theo tòa
    public List<BanQuanLy> getNhanVienTheoToa(String toa) {
        return banQuanLyRepository.findByToaPhuTrachOrAll(toa);
    }
    
    // 🎯 Thống kê tổng quan toàn hệ thống - SỬA LẠI
    public Map<String, Object> getThongKeTongQuan() {
        Map<String, Object> thongKe = new HashMap<>();
        
        // Thống kê nhân viên
        thongKe.put("tongNhanVien", banQuanLyRepository.count());
        thongKe.put("phanBoNhanVien", banQuanLyRepository.countByToaPhuTrach());
        
        // Thống kê phòng
        thongKe.put("tongPhong", phongRepository.count());
        
        // Thống kê sinh viên
        thongKe.put("tongSinhVien", sinhVienRepository.count());
        
        return thongKe;
    }
    
    // 🎯 Kiểm tra nhân viên có thể quản lý phòng
    public boolean kiemTraQuyenQuanLy(Long nhanVienId, String soPhong, String toa) {
        BanQuanLy nhanVien = banQuanLyRepository.findById(nhanVienId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));
        
        Phong phong = phongRepository.findBySoPhongAndToa(soPhong, toa)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng"));
        
        return nhanVien.coTheQuanLyPhong(phong);
    }
    
    // 🎯 Lấy thống kê đơn giản để hiển thị
    public String getThongKeTongQuanText() {
        long tongNhanVien = banQuanLyRepository.count();
        long tongPhong = phongRepository.count();
        long tongSinhVien = sinhVienRepository.count();
        
        return String.format("Tổng số: %d nhân viên | %d phòng | %d sinh viên", 
            tongNhanVien, tongPhong, tongSinhVien);
    }
    
    // 🎯 Kiểm tra phòng còn trống (phương thức tiện ích)
    public boolean isPhongConTrong(Phong phong) {
        return phong.getSoNguoiHienTai() < phong.getSoNguoiToiDa();
    }
    
    // 🎯 Lấy số chỗ trống của phòng
    public int getSoChoTrong(Phong phong) {
        return phong.getSoNguoiToiDa() - phong.getSoNguoiHienTai();
    }
    // 🎯 Đếm tổng số phòng
    public long countAllPhong() {
    return phongRepository.count(); // đảm bảo repo dùng đúng entity Phong
}
}