// package vn.edu.quanlynhatro.controller;

// import vn.edu.quanlynhatro.service.HopDongService;
// import vn.edu.quanlynhatro.model.HopDong;

// import java.text.ParseException;
// import java.text.SimpleDateFormat;
// import java.util.Date;
// import java.util.List;
// import java.util.Scanner;

// /**
//  * Lớp HopDongController xử lý giao tiếp với người dùng cho các chức năng
//  * liên quan đến quản lý hợp đồng.
//  */
// public class HopDongController {
//     private final HopDongService hopDongService = new HopDongService();
//     private final Scanner scanner = new Scanner(System.in);
//     // Định dạng ngày tháng để xử lý input của người dùng
//     private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

//     /**
//      * Hiển thị menu chính cho chức năng quản lý hợp đồng và xử lý lựa chọn.
//      */
//     public void menuHopDong() {
//         while (true) {
//             System.out.println("\n---[ MENU QUẢN LÝ HỢP ĐỒNG ]---");
//             System.out.println("1. Tạo hợp đồng mới");
//             System.out.println("2. Hủy hợp đồng");
//             System.out.println("3. Tìm hợp đồng theo Mã Sinh Viên");
//             System.out.println("4. Xem danh sách hợp đồng sắp hết hạn");
//             System.out.println("5. Hiển thị tất cả hợp đồng");
//             System.out.println("0. Quay lại menu chính");
//             System.out.print(">> Vui lòng chọn chức năng: ");

//             int choice;
//             try {
//                 choice = Integer.parseInt(scanner.nextLine());
//             } catch (NumberFormatException e) {
//                 System.out.println("Lỗi: Vui lòng nhập một số.");
//                 continue;
//             }

//             switch (choice) {
//                 case 1:
//                     hienThiTaoHopDongUI();
//                     break;
//                 case 2:
//                     hienThiHuyHopDongUI();
//                     break;
//                 case 3:
//                     hienThiTimHopDongTheoSVUI();
//                     break;
//                 case 4:
//                     hienThiHopDongSapHetHanUI();
//                     break;
//                 case 5:
//                     hienThiTatCaHopDong();
//                     break;
//                 case 0:
//                     return; // Thoát khỏi menu quản lý hợp đồng
//                 default:
//                     System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
//             }
//         }
//     }

//     /**
//      * Giao diện cho chức năng tạo hợp đồng mới.
//      */
//     private void hienThiTaoHopDongUI() {
//         System.out.println("\n--- Tạo Hợp Đồng Mới ---");
//         System.out.print("Nhập mã hợp đồng: ");
//         String maHD = scanner.nextLine();
//         System.out.print("Nhập mã sinh viên (MSSV): ");
//         String maSV = scanner.nextLine();
//         System.out.print("Nhập mã phòng (Số phòng): ");
//         String maPhong = scanner.nextLine();

//         Date ngayBatDau = nhapNgay("Nhập ngày bắt đầu (dd/MM/yyyy): ");
//         Date ngayKetThuc = nhapNgay("Nhập ngày kết thúc (dd/MM/yyyy): ");
        
//         double tienCoc = 0;
//         boolean validTienCoc = false;
//         while (!validTienCoc) {
//             try {
//                 System.out.print("Nhập số tiền cọc: ");
//                 tienCoc = Double.parseDouble(scanner.nextLine());
//                 validTienCoc = true;
//             } catch (NumberFormatException e) {
//                 System.out.println("Lỗi: Vui lòng nhập số tiền hợp lệ.");
//             }
//         }

//         HopDong newHopDong = new HopDong(maHD, maSV, maPhong, ngayBatDau, ngayKetThuc, tienCoc, "Đang hiệu lực");
//         hopDongService.taoHopDong(newHopDong);
//     }

//     /**
//      * Giao diện cho chức năng hủy hợp đồng.
//      */
//     private void hienThiHuyHopDongUI() {
//         System.out.println("\n--- Hủy Hợp Đồng ---");
//         System.out.print("Nhập mã hợp đồng cần hủy: ");
//         String maHD = scanner.nextLine();
//         hopDongService.huyHopDong(maHD);
//     }
    
//     /**
//      * Giao diện tìm hợp đồng theo MSSV.
//      */
//     private void hienThiTimHopDongTheoSVUI() {
//         System.out.println("\n--- Tìm Hợp Đồng Theo Sinh Viên ---");
//         System.out.print("Nhập mã sinh viên (MSSV): ");
//         String maSV = scanner.nextLine();
//         List<HopDong> result = hopDongService.timKiemTheoMaSV(maSV);
//         if (result.isEmpty()) {
//             System.out.println("Không tìm thấy hợp đồng nào cho sinh viên này.");
//         } else {
//             System.out.println("Các hợp đồng tìm thấy:");
//             result.forEach(System.out::println);
//         }
//     }

//     /**
//      * Giao diện hiển thị các hợp đồng sắp hết hạn.
//      */
//     private void hienThiHopDongSapHetHanUI() {
//         System.out.println("\n--- Danh Sách Hợp Đồng Sắp Hết Hạn (trong 30 ngày) ---");
//         List<HopDong> result = hopDongService.kiemTraHopDongSapHetHan();
//         if (result.isEmpty()) {
//             System.out.println("Không có hợp đồng nào sắp hết hạn.");
//         } else {
//             result.forEach(System.out::println);
//         }
//     }
    
//     /**
//      * Hiển thị tất cả hợp đồng trong hệ thống.
//      */
//     private void hienThiTatCaHopDong() {
//         System.out.println("\n--- Tất Cả Hợp Đồng ---");
//         List<HopDong> all = hopDongService.getAllHopDong();
//         if (all.isEmpty()) {
//             System.out.println("Chưa có hợp đồng nào trong hệ thống.");
//         } else {
//             all.forEach(System.out::println);
//         }
//     }
    
//     /**
//      * Phương thức trợ giúp để nhập ngày tháng một cách an toàn.
//      */
//     private Date nhapNgay(String prompt) {
//         Date date = null;
//         while (date == null) {
//             System.out.print(prompt);
//             try {
//                 date = dateFormat.parse(scanner.nextLine());
//             } catch (ParseException e) {
//                 System.out.println("Định dạng ngày không hợp lệ. Vui lòng nhập lại theo định dạng dd/MM/yyyy.");
//             }
//         }
//         return date;
//     }
// }