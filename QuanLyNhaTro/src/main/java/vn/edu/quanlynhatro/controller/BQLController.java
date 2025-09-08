package vn.edu.quanlynhatro.controller;

import model.BanQuanLy;
import service.BQLService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Lớp BQLController xử lý giao tiếp với người dùng cho các chức năng
 * liên quan đến quản lý thành viên Ban Quản Lý.
 */
public class BQLController {
    private final BQLService bqlService = new BQLService();
    private final Scanner scanner = new Scanner(System.in);
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Hiển thị menu chính cho chức năng quản lý BQL.
     */
    public void menuBQL() {
        while (true) {
            System.out.println("\n---[ MENU QUẢN LÝ BAN QUẢN LÝ ]---");
            System.out.println("1. Thêm thành viên BQL mới");
            System.out.println("2. Sửa thông tin thành viên BQL");
            System.out.println("3. Xóa thành viên BQL");
            System.out.println("4. Tìm kiếm thành viên BQL theo CCCD");
            System.out.println("5. Hiển thị toàn bộ danh sách BQL");
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
                    hienThiThemBQLUI();
                    break;
                case 2:
                    hienThiSuaBQLUI();
                    break;
                case 3:
                    hienThiXoaBQLUI();
                    break;
                case 4:
                    hienThiTimKiemBQLUI();
                    break;
                case 5:
                    hienThiTatCaBQL();
                    break;
                case 0:
                    return; // Thoát khỏi menu BQL
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
            }
        }
    }

    /**
     * Giao diện cho chức năng thêm thành viên BQL.
     */
    private void hienThiThemBQLUI() {
        System.out.println("\n--- Thêm Thành Viên BQL Mới ---");
        System.out.print("Nhập CCCD: ");
        String cccd = scanner.nextLine();
        System.out.print("Nhập họ tên: ");
        String hoTen = scanner.nextLine();
        System.out.print("Nhập giới tính: ");
        String gioiTinh = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String sdt = scanner.nextLine();
        System.out.print("Nhập địa chỉ: ");
        String diaChi = scanner.nextLine();
        Date ngaySinh = nhapNgay("Nhập ngày sinh (dd/MM/yyyy): ");

        BanQuanLy bqlMoi = new BanQuanLy(cccd, sdt, gioiTinh, hoTen, ngaySinh, diaChi);
        bqlService.themBQL(bqlMoi);
    }

    /**
     * Giao diện cho chức năng sửa thông tin thành viên BQL.
     */
    private void hienThiSuaBQLUI() {
        System.out.println("\n--- Sửa Thông Tin Thành Viên BQL ---");
        System.out.print("Nhập CCCD của thành viên cần sửa: ");
        String cccd = scanner.nextLine();

        BanQuanLy bqlCanSua = bqlService.timKiemTheoCccd(cccd);
        if (bqlCanSua == null) {
            System.out.println("Không tìm thấy thành viên BQL với CCCD này.");
            return;
        }

        System.out.println("Nhập thông tin mới (để trống nếu không muốn thay đổi):");
        
        System.out.print("Nhập họ tên mới (" + bqlCanSua.getHoTen() + "): ");
        String hoTenMoi = scanner.nextLine();
        if (hoTenMoi.trim().isEmpty()) hoTenMoi = bqlCanSua.getHoTen();

        System.out.print("Nhập giới tính mới (" + bqlCanSua.getGioiTinh() + "): ");
        String gioiTinhMoi = scanner.nextLine();
        if (gioiTinhMoi.trim().isEmpty()) gioiTinhMoi = bqlCanSua.getGioiTinh();

        System.out.print("Nhập SĐT mới (" + bqlCanSua.getSoDienThoai() + "): ");
        String sdtMoi = scanner.nextLine();
        if (sdtMoi.trim().isEmpty()) sdtMoi = bqlCanSua.getSoDienThoai();

        System.out.print("Nhập địa chỉ mới (" + bqlCanSua.getDiaChi() + "): ");
        String diaChiMoi = scanner.nextLine();
        if (diaChiMoi.trim().isEmpty()) diaChiMoi = bqlCanSua.getDiaChi();
        
        // (CCCD là khóa chính nên không cho sửa)
        BanQuanLy bqlDaSua = new BanQuanLy(cccd, sdtMoi, gioiTinhMoi, hoTenMoi, bqlCanSua.getNgaySinh(), diaChiMoi);
        bqlService.suaBQL(bqlDaSua);
    }

    /**
     * Giao diện cho chức năng xóa thành viên BQL.
     */
    private void hienThiXoaBQLUI() {
        System.out.println("\n--- Xóa Thành Viên BQL ---");
        System.out.print("Nhập CCCD của thành viên cần xóa: ");
        String cccd = scanner.nextLine();
        bqlService.xoaBQL(cccd);
    }

    /**
     * Giao diện tìm kiếm thành viên BQL.
     */
    private void hienThiTimKiemBQLUI() {
        System.out.println("\n--- Tìm Kiếm Thành Viên BQL ---");
        System.out.print("Nhập CCCD cần tìm: ");
        String cccd = scanner.nextLine();
        BanQuanLy bql = bqlService.timKiemTheoCccd(cccd);
        if (bql != null) {
            System.out.println("Đã tìm thấy: " + bql);
        } else {
            System.out.println("Không tìm thấy thành viên nào có CCCD là " + cccd);
        }
    }

    /**
     * Hiển thị tất cả thành viên BQL.
     */
    private void hienThiTatCaBQL() {
        System.out.println("\n--- Danh Sách Toàn Bộ Thành Viên BQL ---");
        List<BanQuanLy> danhSach = bqlService.getAllBQL();
        if (danhSach.isEmpty()) {
            System.out.println("Hiện chưa có thành viên BQL nào trong hệ thống.");
        } else {
            danhSach.forEach(System.out::println);
        }
    }

    /**
     * Phương thức trợ giúp để nhập ngày tháng một cách an toàn.
     */
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