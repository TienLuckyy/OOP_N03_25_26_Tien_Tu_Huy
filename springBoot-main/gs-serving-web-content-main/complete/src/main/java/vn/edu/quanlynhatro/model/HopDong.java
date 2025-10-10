package vn.edu.quanlynhatro.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Lớp HopDong đại diện cho hợp đồng thuê phòng của sinh viên.
 * implements Serializable là bắt buộc để có thể ghi đối tượng này xuống file.
 */
public class HopDong implements Serializable {
    // serialVersionUID giúp kiểm soát phiên bản của lớp khi đọc/ghi file, tránh lỗi
    private static final long serialVersionUID = 5L;

    private String maHopDong;
    private String maSV;      // Dùng để liên kết với đối tượng SinhVien
    private String maPhong;   // Dùng để liên kết với đối tượng Phong
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private double tienCoc;
    private String trangThai; // Các trạng thái: "Đang hiệu lực", "Đã hết hạn", "Đã hủy"

    // Constructor không tham số
    public HopDong() {
    }

    // Constructor đầy đủ tham số
    public HopDong(String maHopDong, String maSV, String maPhong, Date ngayBatDau,
                    Date ngayKetThuc, double tienCoc, String trangThai) {
        this.maHopDong = maHopDong;
        this.maSV = maSV;
        this.maPhong = maPhong;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tienCoc = tienCoc;
        this.trangThai = trangThai;
    }

    // --- Getters and Setters ---
    public String getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(String maHopDong) {
        this.maHopDong = maHopDong;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public double getTienCoc() {
        return tienCoc;
    }

    public void setTienCoc(double tienCoc) {
        this.tienCoc = tienCoc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    /**
     * Ghi đè phương thức toString() để hiển thị thông tin đối tượng một cách rõ ràng.
     * Có định dạng ngày tháng và tiền tệ để dễ đọc hơn.
     */
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "HopDong [" +
                "Mã HĐ: '" + maHopDong + '\'' +
                ", MSSV: '" + maSV + '\'' +
                ", Mã Phòng: '" + maPhong + '\'' +
                ", Bắt đầu: " + sdf.format(ngayBatDau) +
                ", Kết thúc: " + sdf.format(ngayKetThuc) +
                ", Tiền cọc: " + String.format("%,.0f", tienCoc) + " VND" +
                ", Trạng thái: '" + trangThai + '\'' +
                ']';
    }
}