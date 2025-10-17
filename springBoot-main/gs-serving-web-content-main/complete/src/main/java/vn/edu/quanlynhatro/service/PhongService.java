package vn.edu.quanlynhatro.service;
import vn.edu.quanlynhatro.controller.WriteToFile;
import vn.edu.quanlynhatro.model.Phong;
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

    public Optional<Phong> timKiemTheoSoPhong(String soPhong) {
        return phongRepository.findById(soPhong);
    }

    public List<Phong> timKiemPhongTrong() {
        return phongRepository.findPhongConCho();
    }

    public List<Phong> getPhongTheoTrangThai(boolean trangThai) {
        return phongRepository.findByTrangThai(trangThai);
    }

    public List<Phong> getPhongTheoToa(String toa) {
        return phongRepository.findByToa(toa);
    }

    public void themPhong(Phong phong) {
        phongRepository.save(phong);
        writeToFile.exportPhongData();
    }

    public void suaPhong(Phong phong) {
        phongRepository.save(phong);
        writeToFile.exportPhongData();
    }

    public void xoaPhong(String soPhong) {
        phongRepository.deleteById(soPhong);
        writeToFile.exportPhongData();
    }
}
