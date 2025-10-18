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

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    private String mssv;
    private String lop;
    private String nganhHoc;
    private String queQuan;

    // 🔗 Quan hệ N sinh viên – 1 phòng
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "so_phong", referencedColumnName = "so_phong"),
        @JoinColumn(name = "toa", referencedColumnName = "toa")
    })
    private Phong phong;

    @Override
    public String getThongTin() {
        return "Sinh viên: " + getHoTen() + " - MSSV: " + mssv;
    }
}
