package vn.edu.quanlynhatro;

import controller.*; // Sử dụng dấu * để import tất cả các controller cho gọn
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Khởi tạo tất cả các Controller cần thiết
        SinhVienController svController = new SinhVienController();
        PhongController phongController = new PhongController();
        HopDongController hopDongController = new HopDongController();
        BQLController bqlController = new BQLController();
        TaiKhoanController taiKhoanController = new TaiKhoanController();

        Scanner scanner = new Scanner(System.in);

        // 2. Vòng lặp vô hạn để hiển thị menu chính
        while (true) {
            System.out.println("\n=== HỆ THỐNG QUẢN LÝ NHÀ TRỌ SINH VIÊN ===");
            System.out.println("1. Quản lý Sinh Viên");
            System.out.println("2. Quản lý Phòng Trọ");
            System.out.println("3. Quản lý Hợp Đồng");
            System.out.println("4. Quản lý Ban Quản Lý");
            System.out.println("5. Quản lý Tài Khoản");
            System.out.println("0. Thoát chương trình");
            System.out.print(">> Mời bạn chọn chức năng: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số.");
                continue;
            }

            // 3. Điều hướng đến controller tương ứng
            switch (choice) {
                case 1:
                    // svController.menuSinhVien(); // Gọi menu tương ứng
                    System.out.println("Chức năng Quản lý Sinh Viên đang được phát triển.");
                    break;
                case 2:
                    // phongController.menuPhong(); // Gọi menu tương ứng
                    System.out.println("Chức năng Quản lý Phòng Trọ đang được phát triển.");
                    break;
                case 3:
                    hopDongController.menuHopDong(); // Chức năng này đã hoàn thiện
                    break;
                case 4:
                     // bqlController.menuBQL(); // Gọi menu tương ứng
                    System.out.println("Chức năng Quản lý BQL đang được phát triển.");
                    break;
                case 5:
                     // taiKhoanController.menuTaiKhoan(); // Gọi menu tương ứng
                    System.out.println("Chức năng Quản lý Tài Khoản đang được phát triển.");
                    break;
                case 0:
                    System.out.println("Cảm ơn đã sử dụng chương trình!");
                    System.exit(0); // Thoát hoàn toàn
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }
}