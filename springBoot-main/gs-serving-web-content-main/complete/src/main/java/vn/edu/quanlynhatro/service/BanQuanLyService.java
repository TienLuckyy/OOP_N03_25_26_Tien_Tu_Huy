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
import vn.edu.quanlynhatro.controller.WriteToFile;

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

    @Autowired
    private WriteToFile writeToFile;

    // ========================= CREATE =========================
    public BanQuanLy createBanQuanLy(BanQuanLy banQuanLy) {
        if (banQuanLyRepository.findByMaNhanVien(banQuanLy.getMaNhanVien()).isPresent())
            throw new RuntimeException("Mã nhân viên đã tồn tại: " + banQuanLy.getMaNhanVien());
        if (banQuanLyRepository.findByCccd(banQuanLy.getCccd()).isPresent())
            throw new RuntimeException("CCCD đã tồn tại: " + banQuanLy.getCccd());
        if (banQuanLyRepository.findBySoDienThoai(banQuanLy.getSoDienThoai()).isPresent())
            throw new RuntimeException("Số điện thoại đã tồn tại: " + banQuanLy.getSoDienThoai());

        BanQuanLy saved = banQuanLyRepository.save(banQuanLy);
        writeToFile.exportBanQuanLyData();
        return saved;
    }

    // ========================= READ =========================
    public List<BanQuanLy> getAllBanQuanLy() {
        return banQuanLyRepository.findAll();
    }

    public Optional<BanQuanLy> getBanQuanLyById(Long id) {
        return banQuanLyRepository.findById(id);
    }

    public Optional<BanQuanLy> getBanQuanLyByMaNhanVien(String maNhanVien) {
        return banQuanLyRepository.findByMaNhanVien(maNhanVien);
    }

    // ========================= UPDATE =========================
    public BanQuanLy updateBanQuanLy(Long id, BanQuanLy banQuanLyUpdate) {
        BanQuanLy existing = banQuanLyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy ban quản lý với ID: " + id));

        if (banQuanLyRepository.existsByMaNhanVienAndIdNot(banQuanLyUpdate.getMaNhanVien(), id))
            throw new RuntimeException("Mã nhân viên đã tồn tại: " + banQuanLyUpdate.getMaNhanVien());
        if (banQuanLyRepository.existsByCccdAndIdNot(banQuanLyUpdate.getCccd(), id))
            throw new RuntimeException("CCCD đã tồn tại: " + banQuanLyUpdate.getCccd());
        if (banQuanLyRepository.existsBySoDienThoaiAndIdNot(banQuanLyUpdate.getSoDienThoai(), id))
            throw new RuntimeException("Số điện thoại đã tồn tại: " + banQuanLyUpdate.getSoDienThoai());

        existing.setHoTen(banQuanLyUpdate.getHoTen());
        existing.setGioiTinh(banQuanLyUpdate.getGioiTinh());
        existing.setCccd(banQuanLyUpdate.getCccd());
        existing.setSoDienThoai(banQuanLyUpdate.getSoDienThoai());
        existing.setNgaySinh(banQuanLyUpdate.getNgaySinh());
        existing.setDiaChi(banQuanLyUpdate.getDiaChi());
        existing.setToaPhuTrach(banQuanLyUpdate.getToaPhuTrach());
        existing.setMaNhanVien(banQuanLyUpdate.getMaNhanVien());

        BanQuanLy saved = banQuanLyRepository.save(existing);
        writeToFile.exportBanQuanLyData();
        return saved;
    }

    // ========================= DELETE =========================
    public void deleteBanQuanLy(Long id) {
        BanQuanLy nhanVien = banQuanLyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy ban quản lý với ID: " + id));

        List<Phong> phongQuanLy = getPhongQuanLyByNhanVien(id);
        boolean coSinhVien = phongQuanLy.stream().anyMatch(phong -> !phong.getSinhViens().isEmpty());

        if (coSinhVien) {
            long tongSinhVien = phongQuanLy.stream().mapToInt(phong -> phong.getSinhViens().size()).sum();
            throw new RuntimeException("Không thể xóa nhân viên " + nhanVien.getHoTen() +
                    " đang quản lý " + tongSinhVien + " sinh viên. Hãy chuyển sinh viên ra khỏi phòng trước.");
        }

        banQuanLyRepository.deleteById(id);
        writeToFile.exportBanQuanLyData();
    }

    // ========================= BUSINESS LOGIC =========================
    public List<Phong> getPhongQuanLyByNhanVien(Long nhanVienId) {
        BanQuanLy nhanVien = banQuanLyRepository.findById(nhanVienId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));
        if ("Tất cả".equals(nhanVien.getToaPhuTrach())) return phongRepository.findAll();
        return phongRepository.findByToa(nhanVien.getToaPhuTrach());
    }

    public List<SinhVien> getSinhVienQuanLyByNhanVien(Long nhanVienId) {
        return getPhongQuanLyByNhanVien(nhanVienId).stream()
                .flatMap(phong -> phong.getSinhViens().stream())
                .toList();
    }

    public Map<String, Object> getThongKeNhanVien(Long nhanVienId) {
        List<Phong> phongQuanLy = getPhongQuanLyByNhanVien(nhanVienId);
        List<SinhVien> sinhVienQuanLy = getSinhVienQuanLyByNhanVien(nhanVienId);

        Map<String, Object> thongKe = new HashMap<>();
        thongKe.put("tongPhong", phongQuanLy.size());
        thongKe.put("tongSinhVien", sinhVienQuanLy.size());

        long phongTrong = phongQuanLy.stream().filter(phong -> phong.getSoNguoiHienTai() < phong.getSoNguoiToiDa()).count();
        long phongDay = phongQuanLy.stream().filter(phong -> phong.getSoNguoiHienTai() >= phong.getSoNguoiToiDa()).count();

        thongKe.put("phongTrong", phongTrong);
        thongKe.put("phongDay", phongDay);

        int tongSoNguoiToiDa = phongQuanLy.stream().mapToInt(Phong::getSoNguoiToiDa).sum();
        double tyLeSuDung = tongSoNguoiToiDa > 0 ? (double) sinhVienQuanLy.size() / tongSoNguoiToiDa * 100 : 0;
        thongKe.put("dienTichSuDung", String.format("%.1f%%", tyLeSuDung));

        return thongKe;
    }

    public List<BanQuanLy> searchBanQuanLy(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) return getAllBanQuanLy();
        return banQuanLyRepository.findByHoTenContainingIgnoreCase(keyword);
    }

    public List<BanQuanLy> getNhanVienTheoToa(String toa) {
        return banQuanLyRepository.findByToaPhuTrachOrAll(toa);
    }

    public Map<String, Object> getThongKeTongQuan() {
        Map<String, Object> thongKe = new HashMap<>();
        thongKe.put("tongNhanVien", banQuanLyRepository.count());
        thongKe.put("phanBoNhanVien", banQuanLyRepository.countByToaPhuTrach());
        thongKe.put("tongPhong", phongRepository.count());
        thongKe.put("tongSinhVien", sinhVienRepository.count());
        return thongKe;
    }

    public boolean kiemTraQuyenQuanLy(Long nhanVienId, String soPhong, String toa) {
        BanQuanLy nhanVien = banQuanLyRepository.findById(nhanVienId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));
        Phong phong = phongRepository.findBySoPhongAndToa(soPhong, toa)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng"));
        return nhanVien.coTheQuanLyPhong(phong);
    }

    public String getThongKeTongQuanText() {
        long tongNhanVien = banQuanLyRepository.count();
        long tongPhong = phongRepository.count();
        long tongSinhVien = sinhVienRepository.count();
        return String.format("Tổng số: %d nhân viên | %d phòng | %d sinh viên",
                tongNhanVien, tongPhong, tongSinhVien);
    }

    public boolean isPhongConTrong(Phong phong) {
        return phong.getSoNguoiHienTai() < phong.getSoNguoiToiDa();
    }

    public int getSoChoTrong(Phong phong) {
        return phong.getSoNguoiToiDa() - phong.getSoNguoiHienTai();
    }

    public long countAllPhong() {
        return phongRepository.count();
    }
}
