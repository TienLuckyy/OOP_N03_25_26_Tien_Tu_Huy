package vn.edu.quanlynhatro.controller;

import vn.edu.quanlynhatro.service.SinhVienService;
import vn.edu.quanlynhatro.model.SinhVien;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SinhVienController {
    private final SinhVienService sinhVienService = new SinhVienService();
    private final Scanner scanner = new Scanner(System.in);
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void menuSinhVien() {
        while (true) {
            System.out.println("\n---[ MENU QUẢN LÝ SINH VIÊN ]---");
            System.out.println("1. Thêm sinh viên mới");
            System.out.println("2. Sửa thông tin sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Tìm kiếm sinh viên theo MSSV");
            System.out.println("5. Hiển thị toàn bộ danh sách sinh viên");
            System.out.println("0. Quay lại menu chính");
            System.out.print(">> Vui lòng chọn chức năng: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số.");
                continue;
            }

            switch (choice) {
                case 1:
                    hienThiThemSVUI();
                    break;
                case 2:
                    hienThiSuaSVUI();
                    break;
                case 3:
                    hienThiXoaSVUI();
                    break;
                case 4:
                    hienThiTimKiemSVUI();
                    break;
                case 5:
                    hienThiTatCaSV();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
            }
        }
    }

    private void hienThiThemSVUI() {
        System.out.println("\n--- Thêm Sinh Viên Mới ---");
        System.out.print("Nhập MSSV: ");
        String mssv = scanner.nextLine();
        System.out.print("Nhập họ tên: ");
        String hoTen = scanner.nextLine();
        System.out.print("Nhập giới tính: ");
        String gioiTinh = scanner.nextLine();
        System.out.print("Nhập CCCD: ");
        String cccd = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String soDienThoai = scanner.nextLine();
        Date ngaySinh = nhapNgay("Nhập ngày sinh (dd/MM/yyyy): ");
        System.out.print("Nhập địa chỉ: ");
        String diaChi = scanner.nextLine();
        System.out.print("Nhập lớp: ");
        String lop = scanner.nextLine();
        System.out.print("Nhập ngành học: ");
        String nganhHoc = scanner.nextLine();
        System.out.print("Nhập quê quán: ");
        String queQuan = scanner.nextLine();

        SinhVien svMoi = new SinhVien(mssv, hoTen, gioiTinh, cccd, soDienThoai, ngaySinh, diaChi, lop, nganhHoc, queQuan);
        sinhVienService.themSinhVien(svMoi);
    }

    private void hienThiSuaSVUI() {
        System.out.println("\n--- Sửa Thông Tin Sinh Viên ---");
        System.out.print("Nhập MSSV của sinh viên cần sửa: ");
        String mssv = scanner.nextLine();

        SinhVien svCanSua = sinhVienService.timKiemTheoMssv(mssv);
        if (svCanSua == null) {
            System.out.println("Không tìm thấy sinh viên với MSSV này.");
            return;
        }

        System.out.println("Nhập thông tin mới (để trống nếu không muốn thay đổi):");

        System.out.print("Họ tên mới (" + svCanSua.getHoTen() + "): ");
        String hoTenMoi = scanner.nextLine();
        if (!hoTenMoi.trim().isEmpty()) svCanSua.setHoTen(hoTenMoi);

        System.out.print("Quê quán mới (" + svCanSua.getQueQuan() + "): ");
        String queQuanMoi = scanner.nextLine();
        if (!queQuanMoi.trim().isEmpty()) svCanSua.setQueQuan(queQuanMoi);

        System.out.print("SĐT mới (" + svCanSua.getSoDienThoai() + "): ");
        String sdtMoi = scanner.nextLine();
        if (!sdtMoi.trim().isEmpty()) svCanSua.setSoDienThoai(sdtMoi);

        System.out.print("Lớp mới (" + svCanSua.getLop() + "): ");
        String lopMoi = scanner.nextLine();
        if (!lopMoi.trim().isEmpty()) svCanSua.setLop(lopMoi);

        System.out.print("Ngành học mới (" + svCanSua.getNganhHoc() + "): ");
        String nganhMoi = scanner.nextLine();
        if (!nganhMoi.trim().isEmpty()) svCanSua.setNganhHoc(nganhMoi);

        sinhVienService.suaSinhVien(svCanSua);
    }

    private void hienThiXoaSVUI() {
        System.out.println("\n--- Xóa Sinh Viên ---");
        System.out.print("Nhập MSSV của sinh viên cần xóa: ");
        String mssv = scanner.nextLine();
        sinhVienService.xoaSinhVien(mssv);
    }

    private void hienThiTimKiemSVUI() {
        System.out.println("\n--- Tìm Kiếm Sinh Viên ---");
        System.out.print("Nhập MSSV cần tìm: ");
        String mssv = scanner.nextLine();
        SinhVien sv = sinhVienService.timKiemTheoMssv(mssv);
        if (sv != null) {
            System.out.println("Đã tìm thấy: " + sv);
        } else {
            System.out.println("Không tìm thấy sinh viên nào có MSSV là " + mssv);
        }
    }

    private void hienThiTatCaSV() {
        System.out.println("\n--- Danh Sách Toàn Bộ Sinh Viên ---");
        List<SinhVien> danhSach = sinhVienService.getAllSinhVien();
        if (danhSach.isEmpty()) {
            System.out.println("Hiện chưa có sinh viên nào trong hệ thống.");
        } else {
            danhSach.forEach(System.out::println);
        }
    }

    private Date nhapNgay(String prompt) {
        Date date = null;
        while (date == null) {
            System.out.print(prompt);
            try {
                date = dateFormat.parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.println("Định dạng ngày không hợp lệ. Vui lòng nhập lại theo định dạng dd/MM/yyyy.");
            }
        }
        return date;
    }
}
