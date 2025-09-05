package main;

import model.SinhVien;
import model.TaiKhoan;
import model.User;
import model.Phong;
import model.BanQuanLy;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== HỆ THỐNG QUẢN LÝ ===");

        // Tạo sinh viên mẫu
        SinhVien sv = new SinhVien("SV001", "Nguyen Van A", "Hanoi", new Date(2000-1900, 1-1, 15), "Nam", "0123456789", "123456789", "CTK42", "CNTT");
        System.out.println("Thông tin sinh viên:"); 
        System.out.println("MSSV: " + sv.getMssv());
        System.out.println("Tên: " + sv.getTen());
        System.out.println("Quê quán: " + sv.getQueQuan());
        System.out.println("Ngày sinh: " + sv.getNgaySinh());
        System.out.println("Giới tính: " + sv.getGioiTinh());
        System.out.println("SĐT: " + sv.getSdt());
        System.out.println("CCCD: " + sv.getCccd());
        System.out.println("Lớp: " + sv.getLop());
        System.out.println("Ngành học: " + sv.getNganhHoc());
        System.out.println();
        // Tạo tài khoản mẫu
        TaiKhoan tk = new TaiKhoan
        TaiKhoan tk = new TaiKhoan("sv001", "123456", "SinhVien");
        System.out.println("Thông tin tài khoản:"); 
        System.out.println("Username: " + tk.getUsername());
        System.out.println("Password: " + tk.getPassword());
        System.out.println("Role: " + tk.getRole());
        System.out.println("Kiểm tra đăng nhập với đúng mật khẩu: " + (tk
.checkLogin("sv001", "123456") ? "Thành công" : "Thất bại"));
        System.out.println("Kiểm tra đăng nhập với mật khẩu sai: " + (tk    .checkLogin("sv001", "wrongpass") ? "Thành công" : "Thất bại"));
        System.out.println();
        // Tạo phòng mẫu
        Phong phong = new Phong("P001", "Phòng 1", 4, "Phòng máy tính");
        System.out.println("Thông tin phòng:");
        System.out.println("Mã phòng: " + phong.getMaPhong());
        System.out.println("Tên phòng: " + phong.getTenPhong());
        System.out.println("Sức chứa: " + phong.getSucChua());
        System.out.println("Mô tả: " + phong.getMoTa());
        System.out.println();
        // Tạo ban quản lý mẫu
        BanQuanLy bql = new BanQuanLy("BQL001", "Nguyen Van B", "0123456789", " Manager");
        System.out.println("Thông tin ban quản lý:");
        System.out.println("Mã BQL: " + bql.getMaBQL());
        System.out.println("Tên: " + bql.getTen());
        System.out.println("SĐT: " + bql.getSdt());
        System.out.println("Chức vụ: " + bql.getChucVu());
        System.out.println();
        System.out.println("=== KẾT THÚC CHƯƠNG TRÌNH ===");
        
    }
}
