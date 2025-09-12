package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.SinhVien;
import java.util.Date;

public class SinhVienTest {
    public static void main(String[] args) {
        SinhVienService service = new SinhVienService();

        // Thêm sinh viên
        SinhVien sv1 = new SinhVien("SV001", "Nguyen Van A", "Nam", "123456789", "0987654321",
                new Date(), "Hà Nội", "CNTT1", "Công nghệ thông tin", "Hà Nội");
        SinhVien sv2 = new SinhVien("SV002", "Tran Thi B", "Nữ", "987654321", "0912345678",
                new Date(), "TP.HCM", "KT01", "Kế toán", "TP.HCM");

        service.themSinhVien(sv1);
        service.themSinhVien(sv2);

        System.out.println("===== DANH SÁCH SAU KHI THÊM =====");
        service.getDanhSachSinhVien().forEach(System.out::println);

        // Tìm sinh viên
        System.out.println("\n===== TÌM SINH VIÊN MSSV = SV001 =====");
        System.out.println(service.timSinhVien("SV001"));

        // Cập nhật sinh viên
        SinhVien svUpdate = new SinhVien("SV001", "Nguyen Van A Updated", "Nam", "123456789", "0123456789",
                new Date(), "Đà Nẵng", "CNTT2", "Khoa học máy tính", "Đà Nẵng");
        service.capNhatSinhVien("SV001", svUpdate);

        System.out.println("\n===== DANH SÁCH SAU KHI CẬP NHẬT =====");
        service.getDanhSachSinhVien().forEach(System.out::println);

        // Xóa sinh viên
        service.xoaSinhVien("SV002");
        System.out.println("\n===== DANH SÁCH SAU KHI XÓA SV002 =====");
        service.getDanhSachSinhVien().forEach(System.out::println);
    }
}
