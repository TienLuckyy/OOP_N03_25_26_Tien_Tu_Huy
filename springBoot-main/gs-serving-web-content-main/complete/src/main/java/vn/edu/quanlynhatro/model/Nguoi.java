package vn.edu.quanlynhatro.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;

// Lombok annotations
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder // Cho phép lớp con gọi builder của lớp cha
// JPA annotation QUAN TRỌNG: Chỉ định đây là lớp cha ánh xạ
@MappedSuperclass 
public abstract class Nguoi { 

    private String hoTen;
    private String gioiTinh;
    private String cccd;
    private String soDienThoai;
    private LocalDate ngaySinh; // Đã đổi từ java.util.Date sang java.time.LocalDate
    private String diaChi;
    
    // Bạn có thể giữ lại hàm abstract này nếu muốn
    public abstract String getThongTin(); 
}