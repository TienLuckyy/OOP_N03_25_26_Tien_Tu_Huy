package vn.edu.quanlynhatro.model;

import jakarta.persistence.MappedSuperclass;
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

    private String hoTen;
    private String gioiTinh;
    private String cccd;
    private String soDienThoai;
    private LocalDate ngaySinh; 
    private String diaChi;
    
    public abstract String getThongTin(); 
}