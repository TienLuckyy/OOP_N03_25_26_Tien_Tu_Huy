package vn.edu.quanlynhatro.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;

@MappedSuperclass 
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Nguoi { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 🎯 THÊM TRƯỜNG ID VÀO LỚP CHA

    @Column(name = "ho_ten", nullable = false)
    private String hoTen;
    
    @Column(name = "gioi_tinh")
    private String gioiTinh;
    
    @Column(name = "cccd", unique = true)
    private String cccd;
    
    @Column(name = "so_dien_thoai")
    private String soDienThoai;
    
    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh; 
    
    @Column(name = "dia_chi", columnDefinition = "TEXT")
    private String diaChi;
    
    public abstract String getThongTin(); 
}