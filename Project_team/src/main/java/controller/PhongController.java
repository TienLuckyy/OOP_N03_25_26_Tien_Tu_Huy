package vn.edu.quanlynhatro.controller;

import vn.edu.quanlynhatro.service.PhongService;
import vn.edu.quanlynhatro.model.Phong;

import java.util.List;
import java.util.Scanner;

public class PhongController {
    private final PhongService phongService = new PhongService();
    private final Scanner scanner = new Scanner(System.in);

    public void menuPhong() {
        while (true) {
            System.out.println("\n---[ MENU QUẢN LÝ PHÒNG TRỌ ]---");
            System.out.println("1. Thêm phòng mới");
            System.out.println("2. Sửa thông tin phòng");
            System.out.println("3. Xóa phòng");
            System.out.println("4. Tìm kiếm phòng theo số phòng");
            System.out.println("5. Hiển thị danh sách phòng còn trống");
            System.out.println("6. Hiển thị tất cả phòng");
            System.out.println("0. Quay lại menu chính");
            System.out.print(">> Vui lòng chọn chức năng: ");

            int choice = getInputNumber();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    hienThiThemPhongUI();
                    break;
                case 2:
                    hienThiSuaPhongUI();
                    break;
                case 3:
                    hienThiXoaPhongUI();
                    break;
                case 4:
                    hienThiTimKiemPhongUI();
                    break;
                case 5:
                    hienThiPhongTrong();
                    break;
                case 6:
                    hienThiTatCaPhong();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
            }
        }
    }

    private void hienThiThemPhongUI() {
        System.out.println("\n--- Thêm Phòng Mới ---");
        System.out.print("Nhập số phòng: ");
        String soPhong = scanner.nextLine();
        System.out.print("Nhập tiền điện nước (mặc định): ");
        double tienDienNuoc = getInputDouble();
        scanner.nextLine();

        // Mặc định khi thêm mới, phòng sẽ ở trạng thái trống (false)
        Phong phongMoi = new Phong(soPhong, false, tienDienNuoc);
        phongService.themPhong(phongMoi);
    }

    private void hienThiSuaPhongUI() {
        System.out.println("\n--- Sửa Thông Tin Phòng ---");
        System.out.print("Nhập số phòng cần sửa: ");
        String soPhong = scanner.nextLine();

        Phong phongCanSua = phongService.timKiemTheoSoPhong(soPhong);
        if (phongCanSua == null) {
            System.out.println("Không tìm thấy phòng này.");
            return;
        }

        System.out.print("Nhập tiền điện nước mới (" + phongCanSua.getTienDienNuoc() + "): ");
        double tienDienNuocMoi = getInputDouble();
        scanner.nextLine();

        // Trạng thái phòng thường được thay đổi thông qua hợp đồng, không sửa trực tiếp
        // ở đây trừ khi có yêu cầu đặc biệt (ví dụ: phòng đang sửa chữa).
        phongCanSua.setTienDienNuoc(tienDienNuocMoi);
        phongService.suaPhong(phongCanSua);
    }

    private void hienThiXoaPhongUI() {
        System.out.println("\n--- Xóa Phòng ---");
        System.out.print("Nhập số phòng cần xóa: ");
        String soPhong = scanner.nextLine();
        phongService.xoaPhong(soPhong);
    }

    private void hienThiTimKiemPhongUI() {
        System.out.println("\n--- Tìm Kiếm Phòng ---");
        System.out.print("Nhập số phòng cần tìm: ");
        String soPhong = scanner.nextLine();
        Phong phong = phongService.timKiemTheoSoPhong(soPhong);
        if (phong != null) {
            System.out.println("Đã tìm thấy: " + phong);
        } else {
            System.out.println("Không tìm thấy phòng có số " + soPhong);
        }
    }
    
    private void hienThiPhongTrong() {
        System.out.println("\n--- Danh Sách Phòng Còn Trống ---");
        List<Phong> danhSach = phongService.timKiemPhongTrong();
        if (danhSach.isEmpty()) {
            System.out.println("Hiện tại không có phòng nào còn trống.");
        } else {
            danhSach.forEach(System.out::println);
        }
    }

    private void hienThiTatCaPhong() {
        System.out.println("\n--- Danh Sách Toàn Bộ Phòng ---");
        List<Phong> danhSach = phongService.getAllPhong();
        if (danhSach.isEmpty()) {
            System.out.println("Chưa có phòng nào trong hệ thống.");
        } else {
            danhSach.forEach(System.out::println);
        }
    }
    
    // Helper methods for safe input
    private int getInputNumber() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Lỗi: Vui lòng nhập một số. Thử lại: ");
            }
        }
    }

    private double getInputDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Lỗi: Vui lòng nhập một số hợp lệ. Thử lại: ");
            }
        }
    }
}
