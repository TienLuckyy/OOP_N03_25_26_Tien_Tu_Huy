package vn.edu.quanlynhatro.model; 

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "sinh_vien")
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class SinhVien extends Nguoi {

    // PHẢI CÓ @Id
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    private String mssv;
    private String lop;
    private String nganhHoc;
    private String queQuan;
    
    @Override
    public String getThongTin() {
        return "Sinh viên: " + getHoTen() + " - MSSV: " + mssv;
    }
}