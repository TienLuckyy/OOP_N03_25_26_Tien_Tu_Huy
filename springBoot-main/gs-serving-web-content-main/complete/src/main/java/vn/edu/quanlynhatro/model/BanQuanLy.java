package vn.edu.quanlynhatro.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "ban_quan_ly")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class BanQuanLy extends Nguoi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chucVu;
    private String toaPhuTrach;

    @Override
    public String getThongTin() {
        return "Ban quản lý: " + getHoTen() + " - " + chucVu + " (Tòa " + toaPhuTrach + ")";
    }
}
