package vn.edu.quanlynhatro.controller;

import vn.edu.quanlynhatro.model.SinhVien;
import vn.edu.quanlynhatro.service.SinhVienService;

import java.util.Date;
import java.util.Scanner;

public class SinhVienController {
    private final SinhVienService sinhVienService = new SinhVienService();
    private final Scanner scanner = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("\n===== QUẢN LÝ SINH VIÊN =====");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Xem danh sách");
            System.out.println("3. Sửa sinh viên");
            System.out.println("4. Xóa sinh viên");
            System.out.println("5. Tìm sinh viên");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int chon;
            try {
                chon = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Vui lòng nhập số!");
                continue;
            }

            switch (chon) {
                case 1:
                    themSinhVienUI();
                    break;
                case 2:
                    hienThiDanhSach();
                    break;
                case 3:
                    suaSinhVienUI();
                    break;
                case 4:
                    xoaSinhVienUI();
                    break;
                case 5:
                    timSinhVienUI();
                    break;
                case 0:
                    System.out.println("👋 Thoát chương trình.");
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }

    private void themSinhVienUI() {
        System.out.print("Nhập MSSV: ");
        String mssv = scanner.nextLine();
        System.out.print("Nhập họ tên: ");
        String hoTen = scanner.nextLine();
        System.out.print("Giới tính: ");
        String gioiTinh = scanner.nextLine();
        System.out.print("CCCD: ");
        String cccd = scanner.nextLine();
        System.out.print("SĐT: ");
        String sdt = scanner.nextLine();
        System.out.print("Địa chỉ: ");
        String diaChi = scanner.nextLine();
        System.out.print("Lớp: ");
        String lop = scanner.nextLine();
        System.out.print("Ngành học: ");
        String nganhHoc = scanner.nextLine();
        System.out.print("Quê quán: ");
        String queQuan = scanner.nextLine();

        SinhVien sv = new SinhVien(mssv, hoTen, gioiTinh, cccd, sdt, new Date(), diaChi, lop, nganhHoc, queQuan);
        sinhVienService.themSinhVien(sv);
        System.out.println("✅ Đã thêm sinh viên!");
    }

    private void hienThiDanhSach() {
        if (sinhVienService.getDanhSachSinhVien().isEmpty()) {
            System.out.println("⚠ Danh sách trống!");
        } else {
            for (SinhVien sv : sinhVienService.getDanhSachSinhVien()) {
                System.out.println(sv);
            }
        }
    }

    private void suaSinhVienUI() {
        System.out.print("Nhập MSSV cần sửa: ");
        String mssv = scanner.nextLine();
        SinhVien sv = sinhVienService.timSinhVien(mssv);
        if (sv != null) {
            System.out.print("Tên mới (Enter bỏ qua): ");
            String ten = scanner.nextLine();
            if (!ten.isEmpty()) sv.setHoTen(ten);

            System.out.print("SĐT mới (Enter bỏ qua): ");
            String sdt = scanner.nextLine();
            if (!sdt.isEmpty()) sv.setSoDienThoai(sdt);

            System.out.print("Địa chỉ mới (Enter bỏ qua): ");
            String diaChi = scanner.nextLine();
            if (!diaChi.isEmpty()) sv.setDiaChi(diaChi);

            System.out.print("Lớp mới (Enter bỏ qua): ");
            String lop = scanner.nextLine();
            if (!lop.isEmpty()) sv.setLop(lop);

            System.out.print("Ngành học mới (Enter bỏ qua): ");
            String nganhHoc = scanner.nextLine();
            if (!nganhHoc.isEmpty()) sv.setNganhHoc(nganhHoc);

            System.out.print("Quê quán mới (Enter bỏ qua): ");
            String queQuan = scanner.nextLine();
            if (!queQuan.isEmpty()) sv.setQueQuan(queQuan);

            System.out.println("✅ Đã cập nhật sinh viên!");
        } else {
            System.out.println("❌ Không tìm thấy sinh viên!");
        }
    }

    private void xoaSinhVienUI() {
        System.out.print("Nhập MSSV cần xóa: ");
        String mssv = scanner.nextLine();
        if (sinhVienService.xoaSinhVien(mssv)) {
            System.out.println("✅ Đã xóa!");
        } else {
            System.out.println("❌ Không tìm thấy sinh viên!");
        }
    }

    private void timSinhVienUI() {
        System.out.print("Nhập MSSV cần tìm: ");
        String mssv = scanner.nextLine();
        SinhVien sv = sinhVienService.timSinhVien(mssv);
        if (sv != null) {
            System.out.println(sv);
        } else {
            System.out.println("❌ Không tìm thấy!");
        }
    }
}
