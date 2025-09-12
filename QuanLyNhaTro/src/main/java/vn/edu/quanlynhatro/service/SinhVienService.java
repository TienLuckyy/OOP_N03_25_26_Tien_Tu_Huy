package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.SinhVien;
import java.util.ArrayList;
import java.util.List;

public class SinhVienService {
    private final List<SinhVien> danhSachSinhVien = new ArrayList<>();

    // Thêm sinh viên
    public void themSinhVien(SinhVien sv) {
        danhSachSinhVien.add(sv);
    }

    // Lấy danh sách sinh viên
    public List<SinhVien> getDanhSachSinhVien() {
        return danhSachSinhVien;
    }

    // Tìm sinh viên theo MSSV
    public SinhVien timSinhVien(String mssv) {
        for (SinhVien sv : danhSachSinhVien) {
            if (sv.getMssv().equalsIgnoreCase(mssv)) {
                return sv;
            }
        }
        return null;
    }

    // Xóa sinh viên
    public boolean xoaSinhVien(String mssv) {
        SinhVien sv = timSinhVien(mssv);
        if (sv != null) {
            danhSachSinhVien.remove(sv);
            return true;
        }
        return false;
    }

    // Cập nhật thông tin sinh viên
    public boolean capNhatSinhVien(String mssv, SinhVien svMoi) {
        SinhVien svCu = timSinhVien(mssv);
        if (svCu != null) {
            if (svMoi.getHoTen() != null && !svMoi.getHoTen().isEmpty()) svCu.setHoTen(svMoi.getHoTen());
            if (svMoi.getSoDienThoai() != null && !svMoi.getSoDienThoai().isEmpty()) svCu.setSoDienThoai(svMoi.getSoDienThoai());
            if (svMoi.getDiaChi() != null && !svMoi.getDiaChi().isEmpty()) svCu.setDiaChi(svMoi.getDiaChi());
            if (svMoi.getLop() != null && !svMoi.getLop().isEmpty()) svCu.setLop(svMoi.getLop());
            if (svMoi.getNganhHoc() != null && !svMoi.getNganhHoc().isEmpty()) svCu.setNganhHoc(svMoi.getNganhHoc());
            if (svMoi.getQueQuan() != null && !svMoi.getQueQuan().isEmpty()) svCu.setQueQuan(svMoi.getQueQuan());
            return true;
        }
        return false;
    }
}
