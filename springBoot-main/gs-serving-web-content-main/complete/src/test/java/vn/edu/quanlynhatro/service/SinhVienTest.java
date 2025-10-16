// package vn.edu.quanlynhatro.service;

// import vn.edu.quanlynhatro.model.SinhVien;

// import java.util.Date;
// import java.util.List;

// public class SinhVienTest {
//     public static void main(String[] args) {
//         SinhVienService service = new SinhVienService();

//         SinhVien sv1 = new SinhVien(
//                 "SV001", "Nguyen Van A", "Nam", "012345678",
//                 "0987654321", new Date(), "Hà Nội", "CNTT1", "Cong Nghe Thong Tin", "Nam Đinh"
//         );

//         SinhVien sv2 = new SinhVien(
//                 "SV002", "Tran Thi B", "Nu", "987654321",
//                 "0912345678", new Date(), "Ha Noi", "CNTT2", "Khoa học máy tính", "Hai Duong"
//         );

//         service.themSinhVien(sv1);
//         service.themSinhVien(sv2);

//         SinhVien sv2Updated = new SinhVien(
//                 "SV002", "Tran Thi C", "Nam", "987654321",
//                 "0912345678", new Date(), "Ha Noi", "CNTT2", "Khoa hoc du lieu", "Hai Duong"
//         );
//         service.suaSinhVien(sv2Updated);

//         SinhVien found = service.timKiemTheoMssv("SV002");
//         if (found != null) {
//             System.out.println("Tim thay sinh vien: " + found.getThongTin());
//         }

//         List<SinhVien> ketQuaTimTen = service.timKiemTheoTen("Bao");
//         System.out.println("Ket qua tim kiem theo ten:");
//         ketQuaTimTen.forEach(sv -> System.out.println(sv.getThongTin()));

//         service.xoaSinhVien("SV001");

//         service.getAllSinhVien().forEach(sv -> {
//     System.out.println(sv.getThongTin());
//     });

//     }
// }
