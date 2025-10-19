package vn.edu.quanlynhatro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.quanlynhatro.model.BanQuanLy;
import vn.edu.quanlynhatro.repository.BanQuanLyRepository;

import java.util.List;

@Service
@Transactional
public class BanQuanLyService {

    @Autowired
    private BanQuanLyRepository banQuanLyRepository;

    public List<BanQuanLy> getAll() {
        return banQuanLyRepository.findAll();
    }

    public BanQuanLy getById(Long id) {
        return banQuanLyRepository.findById(id).orElse(null);
    }

    public BanQuanLy save(BanQuanLy bql) {
        return banQuanLyRepository.save(bql);
    }

    public void deleteById(Long id) {
        banQuanLyRepository.deleteById(id);
    }

    public List<BanQuanLy> searchByHoTen(String keyword) {
        return banQuanLyRepository.findByHoTenContainingIgnoreCase(keyword);
    }
}
