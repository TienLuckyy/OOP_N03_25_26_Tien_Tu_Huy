package vn.edu.quanlynhatro.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.Date; // Giữ lại Date nếu bạn chưa đổi Nguoi sang LocalDate

// Lombok annotations để tự động tạo getters/setters/constructors
@Data
@NoArgsConstructor // Cần thiết cho JPA/Hibernate
@SuperBuilder // Cho phép sử dụng builder pattern, kế thừa từ lớp cha
@EqualsAndHashCode(callSuper = true) // Đảm bảo so sánh cả thuộc tính lớp cha

// JPA annotations
@Entity
@Table(name = "ban_quan_ly") // Tên bảng trong MySQL
public class BanQuanLy extends Nguoi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Cần một khóa chính riêng biệt cho mỗi Entity
    private Long id; 

    // Bạn có thể thêm các thuộc tính riêng của BanQuanLy ở đây (ví dụ: chucVu)
    // private String chucVu;

    // Constructor đã được @SuperBuilder và @NoArgsConstructor tạo tự động.
    // Nếu bạn muốn dùng constructor thủ công, bạn vẫn có thể giữ lại.
    /*
    public BanQuanLy(String hoTen, String gioiTinh, String cccd, String soDienThoai, Date ngaySinh, String diaChi) {
        super(hoTen, gioiTinh, cccd, soDienThoai, ngaySinh, diaChi);
    }
    */
    
    @Override
    public String getThongTin() {
        // Sử dụng getter (getHoTen) thay vì truy cập trực tiếp biến (hoTen)
        return "Ban Quan Ly: " + getHoTen() + " - CCCD: " + getCccd(); 
    }
}