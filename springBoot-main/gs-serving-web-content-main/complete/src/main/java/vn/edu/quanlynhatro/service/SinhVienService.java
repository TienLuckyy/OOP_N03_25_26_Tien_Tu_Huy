package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.util.FileUtil;
import vn.edu.quanlynhatro.model.SinhVien;

import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SinhVienService {

    private List<SinhVien> danhSachSinhVien;
    private static final String FILE_NAME = "data/sinhvien.dat";

    public SinhVienService() {
        this.danhSachSinhVien = new ArrayList<>();
    }

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void loadDataFromFile() {
        List<SinhVien> loadedData = (List<SinhVien>) FileUtil.docFile(FILE_NAME);
        if (loadedData != null) {
            this.danhSachSinhVien = loadedData;
        }
    }

    public List<SinhVien> getAllSinhVien() {
        return this.danhSachSinhVien;
    }

    public boolean themSinhVien(SinhVien sv) {
        if (sv == null || sv.getMssv() == null || sv.getMssv().isEmpty()) {
            return false;
        }

        boolean isExist = danhSachSinhVien.stream()
                .anyMatch(s -> s.getMssv().equalsIgnoreCase(sv.getMssv()));

        if (isExist) {
            return false;
        }

        this.danhSachSinhVien.add(sv);
        luuFile();
        return true;
    }

    public boolean suaSinhVien(SinhVien svDaSua) {
        for (int i = 0; i < danhSachSinhVien.size(); i++) {
            if (danhSachSinhVien.get(i).getMssv().equalsIgnoreCase(svDaSua.getMssv())) {
                danhSachSinhVien.set(i, svDaSua);
                luuFile();
                return true;
            }
        }
        return false;
    }

    public boolean xoaSinhVien(String mssv) {
        boolean isRemoved = danhSachSinhVien.removeIf(sv -> sv.getMssv().equalsIgnoreCase(mssv));
        if (isRemoved) {
            luuFile();
            return true;
        } else {
            return false;
        }
    }

    public SinhVien timKiemTheoMssv(String mssv) {
        return danhSachSinhVien.stream()
                .filter(sv -> sv.getMssv().equalsIgnoreCase(mssv))
                .findFirst()
                .orElse(null);
    }

    public Optional<SinhVien> timKiemTheoMssvOptional(String mssv) {
        return danhSachSinhVien.stream()
                .filter(sv -> sv.getMssv().equalsIgnoreCase(mssv))
                .findFirst();
    }

    public List<SinhVien> timKiemTheoTen(String ten) {
        return danhSachSinhVien.stream()
                .filter(sv -> sv.getHoTen() != null && sv.getHoTen().toLowerCase().contains(ten.toLowerCase()))
                .collect(Collectors.toList());
    }

    private void luuFile() {
        FileUtil.ghiFile(FILE_NAME, this.danhSachSinhVien);
    }
}