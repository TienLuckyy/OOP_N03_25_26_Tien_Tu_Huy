package vn.edu.quanlynhatro.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(
    name = "ban_quan_ly",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "cccd"),
        @UniqueConstraint(columnNames = "so_dien_thoai")
    }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AttributeOverrides({
    @AttributeOverride(name = "cccd", column = @Column(name = "cccd", unique = true)),
    @AttributeOverride(name = "soDienThoai", column = @Column(name = "so_dien_thoai", unique = true))
})
public class BanQuanLy extends Nguoi {

    @Column(name = "ma_nhan_vien", nullable = false)
    private String maNhanVien;

    @Column(name = "toa_phu_trach")
    private String toaPhuTrach;

    // @Column(name = "chuc_vu")
    // private String chucVu;

    @Override
    public String getThongTin() {
        return "Ban quản lý: " + getHoTen() + " - " + maNhanVien + " (Tòa " + toaPhuTrach + ")";
    }

    public boolean coTheQuanLyPhong(Phong phong) {
        if (phong == null) return false;
        return "Tất cả".equalsIgnoreCase(this.toaPhuTrach)
                || this.toaPhuTrach.equalsIgnoreCase(phong.getToa());
    }
    
}
