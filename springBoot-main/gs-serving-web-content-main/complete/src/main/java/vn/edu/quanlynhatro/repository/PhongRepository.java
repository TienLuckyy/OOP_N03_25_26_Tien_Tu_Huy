package vn.edu.quanlynhatro.repository;

import vn.edu.quanlynhatro.model.Phong;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PhongRepository {
    
    private final List<Phong> danhSachPhong = new ArrayList<>();

    public PhongRepository() {
        danhSachPhong.add(new Phong("A101", true, 250000));
        danhSachPhong.add(new Phong("A102", false, 0));
        danhSachPhong.add(new Phong("B201", false, 0));
        danhSachPhong.add(new Phong("C305", true, 300000));
    }

    public List<Phong> findAll() { return danhSachPhong; }

    public Phong save(Phong phong) {
        if (findById(phong.getSoPhong()).isPresent()) { deleteById(phong.getSoPhong()); }
        danhSachPhong.add(phong);
        return phong;
    }

    public void deleteById(String soPhong) {
        danhSachPhong.removeIf(p -> p.getSoPhong().equals(soPhong));
    }

    public Optional<Phong> findById(String soPhong) {
        return danhSachPhong.stream()
                .filter(p -> p.getSoPhong().equals(soPhong))
                .findFirst();
    }

    public List<Phong> findByTrangThai(boolean trangThai) {
        return danhSachPhong.stream()
                .filter(p -> p.isTrangThai() == trangThai)
                .collect(Collectors.toList());
    }
}