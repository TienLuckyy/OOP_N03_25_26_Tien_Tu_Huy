package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.Phong;

public class PhongTest {
    public static void main(String[] args) {
        PhongService service = new PhongService();

        // Thêm phòng
        Phong p1 = new Phong("101", false, 500000);
        Phong p2 = new Phong("102", true, 450000);
        Phong p3 = new Phong("103", false, 600000);

        service.themPhong(p1);
        service.themPhong(p2);
        service.themPhong(p3);

        System.out.println("\n--- Danh sách tất cả phòng ---");
        service.getAllPhong().forEach(Phong::hienThiThongTin);

        // Tìm kiếm
        System.out.println("\n--- Tìm phòng số 101 ---");
        Phong tim = service.timKiemTheoSoPhong("101");
        if (tim != null) tim.hienThiThongTin();

        // Cập nhật phòng
        System.out.println("\n--- Cập nhật phòng 103 ---");
        p3.setTienDienNuoc(700000);
        service.suaPhong(p3);

        // Hiển thị lại danh sách
        System.out.println("\n--- Danh sách sau khi cập nhật ---");
        service.getAllPhong().forEach(Phong::hienThiThongTin);

        // Xóa phòng
        System.out.println("\n--- Xóa phòng 102 ---");
        service.xoaPhong("102");

        System.out.println("\n--- Danh sách sau khi xóa ---");
        service.getAllPhong().forEach(Phong::hienThiThongTin);

        // Hiển thị phòng trống
        System.out.println("\n--- Danh sách phòng trống ---");
        service.timKiemPhongTrong().forEach(Phong::hienThiThongTin);
    }
}
