package vn.edu.quanlynhatro.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(PhongId.class) // ✅ Khai báo khóa chính phức hợp
@Table(name = "phong")
public class Phong implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "so_phong", length = 10)
    private String soPhong;

    @Id
    @Column(name = "toa", length = 50, nullable = false)
    private String toa;

    @Column(name = "tien_nha")
    private Double tienNha;

    @Column(name = "so_nguoi_hien_tai")
    private Integer soNguoiHienTai;

    @Column(name = "so_nguoi_toi_da")
    private Integer soNguoiToiDa;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    public Phong() {}

    public Phong(String soPhong, String toa, Double tienNha,
                 Integer soNguoiHienTai, Integer soNguoiToiDa, Boolean trangThai) {
        this.soPhong = soPhong;
        this.toa = toa;
        this.tienNha = tienNha;
        this.soNguoiHienTai = soNguoiHienTai;
        this.soNguoiToiDa = soNguoiToiDa;
        this.trangThai = trangThai;
    }

    // Getters & Setters
    public String getSoPhong() { return soPhong; }
    public void setSoPhong(String soPhong) { this.soPhong = soPhong; }

    public String getToa() { return toa; }
    public void setToa(String toa) { this.toa = toa; }

    public Double getTienNha() { return tienNha; }
    public void setTienNha(Double tienNha) { this.tienNha = tienNha; }

    public Integer getSoNguoiHienTai() { return soNguoiHienTai; }
    public void setSoNguoiHienTai(Integer soNguoiHienTai) { this.soNguoiHienTai = soNguoiHienTai; }

    public Integer getSoNguoiToiDa() { return soNguoiToiDa; }
    public void setSoNguoiToiDa(Integer soNguoiToiDa) { this.soNguoiToiDa = soNguoiToiDa; }

    public Boolean getTrangThai() { return trangThai; }
    public void setTrangThai(Boolean trangThai) { this.trangThai = trangThai; }

    public boolean isTrangThai() {
        return Boolean.TRUE.equals(trangThai);
    }

    @Override
    public String toString() {
        return "Phong{" +
                "soPhong='" + soPhong + '\'' +
                ", toa='" + toa + '\'' +
                ", tienNha=" + tienNha +
                ", soNguoiHienTai=" + soNguoiHienTai +
                ", soNguoiToiDa=" + soNguoiToiDa +
                ", trangThai=" + trangThai +
                '}';
    }
}
