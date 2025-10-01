package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.util.FileUtil;
import vn.edu.quanlynhatro.model.SinhVien;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SinhVienService {

    private List<SinhVien> danhSachSinhVien;
    private static final String FILE_NAME = "data/sinhvien.dat";

    @SuppressWarnings("unchecked")
    public SinhVienService() {
        this.danhSachSinhVien = (List<SinhVien>) FileUtil.docFile(FILE_NAME);
        if (this.danhSachSinhVien == null) {
            this.danhSachSinhVien = new ArrayList<>();
        }
    }

    public List<SinhVien> getAllSinhVien() {
        return this.danhSachSinhVien;
    }

    public void themSinhVien(SinhVien sv) {
        if (sv == null || sv.getMssv() == null || sv.getMssv().isEmpty()) {
            System.out.println("Lỗi: MSSV không hợp lệ!");
            return;
        }

        boolean isExist = danhSachSinhVien.stream()
                .anyMatch(s -> s.getMssv().equalsIgnoreCase(sv.getMssv()));

        if (isExist) {
            System.out.println("Lỗi: Mã số sinh viên " + sv.getMssv() + " đã tồn tại!");
            return;
        }

        danhSachSinhVien.add(sv);
        luuFile();
        System.out.println("Thêm sinh viên thành công.");
    }

    public boolean suaSinhVien(SinhVien svDaSua) {
        for (int i = 0; i < danhSachSinhVien.size(); i++) {
            if (danhSachSinhVien.get(i).getMssv().equalsIgnoreCase(svDaSua.getMssv())) {
                danhSachSinhVien.set(i, svDaSua);
                luuFile();
                System.out.println("Cập nhật thông tin sinh viên thành công.");
                return true;
            }
        }
        System.out.println("Lỗi: Không tìm thấy sinh viên có MSSV " + svDaSua.getMssv());
        return false;
    }

    public boolean xoaSinhVien(String mssv) {
        boolean isRemoved = danhSachSinhVien.removeIf(sv -> sv.getMssv().equalsIgnoreCase(mssv));
        if (isRemoved) {
            luuFile();
            System.out.println("Xóa sinh viên thành công.");
            return true;
        } else {
            System.out.println("Lỗi: Không tìm thấy sinh viên có MSSV " + mssv);
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
                .toList();
    }

    private void luuFile() {
        FileUtil.ghiFile(FILE_NAME, this.danhSachSinhVien);
    }
}
