package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.controller.WriteToFile;
import vn.edu.quanlynhatro.model.Phong;
import vn.edu.quanlynhatro.model.PhongId;
import vn.edu.quanlynhatro.repository.PhongRepository;
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

    public List<Phong> getAllPhong() {
        return phongRepository.findAll();
    }

    // ✅ Tìm phòng theo (soPhong, toa)
    public Optional<Phong> timKiemTheoSoPhongVaToa(String soPhong, String toa) {
        return phongRepository.findById(new PhongId(soPhong, toa));
    }

    // ✅ Tìm phòng còn trống
    public List<Phong> timKiemPhongTrong() {
        return phongRepository.findPhongConCho();
    }

    public List<Phong> getPhongTheoTrangThai(boolean trangThai) {
        return phongRepository.findByTrangThai(trangThai);
    }

    public List<Phong> getPhongTheoToa(String toa) {
        return phongRepository.findByToa(toa);
    }

    // ✅ Kiểm tra trùng phòng (soPhong + toa)
    public boolean kiemTraTrungPhong(String soPhong, String toa) {
        return phongRepository.existsBySoPhongAndToa(soPhong, toa);
    }

    // ✅ Thêm phòng (không ném exception)
    public boolean themPhong(Phong phong) {
        if (kiemTraTrungPhong(phong.getSoPhong(), phong.getToa())) {
            return false; // báo là trùng
        }
        phongRepository.save(phong);
        writeToFile.exportPhongData();
        return true;
    }

    public void suaPhong(Phong phong) {
        phongRepository.save(phong);
        writeToFile.exportPhongData();
    }

    public void xoaPhong(String soPhong, String toa) {
        phongRepository.deleteById(new PhongId(soPhong, toa));
        writeToFile.exportPhongData();
    }
}
