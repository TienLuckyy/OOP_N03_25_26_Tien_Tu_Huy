package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.Phong;

import java.util.ArrayList;
import java.util.List;

public class PhongService {
    private final List<Phong> danhSachPhong = new ArrayList<>();

    // Thêm phòng
    public void themPhong(Phong phong) {
        danhSachPhong.add(phong);
        System.out.println("✅ Đã thêm phòng: " + phong.getSoPhong());
    }

    // Sửa phòng
    public void suaPhong(Phong phong) {
        for (int i = 0; i < danhSachPhong.size(); i++) {
            if (danhSachPhong.get(i).getSoPhong().equalsIgnoreCase(phong.getSoPhong())) {
                danhSachPhong.set(i, phong);
                System.out.println("✅ Đã cập nhật thông tin phòng " + phong.getSoPhong());
                return;
            }
        }
        System.out.println("❌ Không tìm thấy phòng để sửa!");
    }

    // Xóa phòng
    public boolean xoaPhong(String soPhong) {
        Phong phong = timKiemTheoSoPhong(soPhong);
        if (phong != null) {
            danhSachPhong.remove(phong);
            System.out.println("✅ Đã xóa phòng: " + soPhong);
            return true;
        }
        System.out.println("❌ Không tìm thấy phòng: " + soPhong);
        return false;
    }

    // Tìm phòng theo số phòng
    public Phong timKiemTheoSoPhong(String soPhong) {
        for (Phong phong : danhSachPhong) {
            if (phong.getSoPhong().equalsIgnoreCase(soPhong)) {
                return phong;
            }
        }
        return null;
    }

    // Lấy danh sách phòng còn trống
    public List<Phong> timKiemPhongTrong() {
        List<Phong> dsTrong = new ArrayList<>();
        for (Phong phong : danhSachPhong) {
            if (!phong.isTrangThai()) {
                dsTrong.add(phong);
            }
        }
        return dsTrong;
    }

    // Lấy tất cả phòng
    public List<Phong> getAllPhong() {
        return danhSachPhong;
    }
}
