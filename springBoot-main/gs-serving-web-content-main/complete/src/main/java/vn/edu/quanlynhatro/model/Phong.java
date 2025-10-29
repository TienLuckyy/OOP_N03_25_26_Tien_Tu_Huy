package vn.edu.quanlynhatro.model;

import jakarta.persistence.*;
import java.io.Serializable;
// SỬA 1: Dùng Set thay vì List để quản lý quan hệ, tốt hơn cho JPA
import java.util.HashSet;
import java.util.Set;

@Entity
@IdClass(PhongId.class) // Khóa chính phức hợp
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

    // SỬA 2: Xóa trường này. Chúng ta sẽ không lưu nó trong database nữa.
    // @Column(name = "so_nguoi_hien_tai")
    // private Integer soNguoiHienTai; 

    @Column(name = "so_nguoi_toi_da")
    private Integer soNguoiToiDa;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    // SỬA 3: Dùng Set và khởi tạo nó
    // @OneToMany(mappedBy = "phong", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // private Set<SinhVien> sinhViens = new HashSet<>();
    @OneToMany(mappedBy = "phong", cascade = CascadeType.ALL)
    private Set<SinhVien> sinhViens = new HashSet<>();

    public Phong() {}

    // SỬA 4: Xóa 'soNguoiHienTai' khỏi constructor
    public Phong(String soPhong, String toa, Double tienNha,
                 /* Integer soNguoiHienTai, */ Integer soNguoiToiDa, Boolean trangThai) {
        this.soPhong = soPhong;
        this.toa = toa;
        this.tienNha = tienNha;
        // this.soNguoiHienTai = soNguoiHienTai; // Xóa dòng này
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

    
    // SỬA 5: THÊM PHƯƠNG THỨC NÀY
    /**
     * Phương thức này sẽ tự động đếm số sinh viên trong danh sách.
     * Nó không được lưu vào database (vì không có @Column).
     * Thymeleaf sẽ tự động gọi 'getSoNguoiHienTai()' khi bạn dùng ${phong.soNguoiHienTai}.
     */
    public Integer getSoNguoiHienTai() { 
        if (this.sinhViens == null) {
            return 0;
        }
        return this.sinhViens.size(); // Luôn trả về số lượng chính xác
    }
    
    // SỬA 6: Xóa Setter cho soNguoiHienTai
    // public void setSoNguoiHienTai(Integer soNguoiHienTai) { ... }


    public Integer getSoNguoiToiDa() { return soNguoiToiDa; }
    public void setSoNguoiToiDa(Integer soNguoiToiDa) { this.soNguoiToiDa = soNguoiToiDa; }

    public Boolean getTrangThai() { return trangThai; }
    public void setTrangThai(Boolean trangThai) { this.trangThai = trangThai; }

    public boolean isTrangThai() {
        return Boolean.TRUE.equals(trangThai);
    }

    // SỬA 7: Cập nhật getter/setter cho Set
    public Set<SinhVien> getSinhViens() {
        return sinhViens;
    }

    public void setSinhViens(Set<SinhVien> sinhViens) {
        this.sinhViens = sinhViens;
    }

    @Override
    public String toString() {
        return "Phong{" +
                "soPhong='" + soPhong + '\'' +
                ", toa='" + toa + '\'' +
                ", tienNha=" + tienNha +
                // SỬA 8: Xóa 'soNguoiHienTai' khỏi toString
                // ", soNguoiHienTai=" + soNguoiHienTai + 
                ", soNguoiToiDa=" + soNguoiToiDa +
                ", trangThai=" + trangThai +
                '}';
    }
}