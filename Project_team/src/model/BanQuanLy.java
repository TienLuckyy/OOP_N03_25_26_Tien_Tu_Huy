package model;

import java.io.Serializable;
import java.util.Date;

/**
 * Lớp này đại diện cho thông tin của một thành viên Ban Quản lý (BQL).
 * Lớp được thiết kế theo nguyên tắc đóng gói (encapsulation).
 */
public class BanQuanLy implements Serializable {

    
    // 1. CÁC BIẾN THÀNH VIÊN ĐƯỢC KHAI BÁO PRIVATE
    //
    private String cccd;
    private String soDienThoai;
    private String gioiTinh;
    private String hoTen;
    private Date ngaySinh;
    private String diaChi;

    /**
     * Hàm khởi tạo không tham số
     */
    public BanQuanLy() {
    }

    /**
     * Hàm khởi tạo đầy đủ tham số
     */
    public BanQuanLy(String cccd, String soDienThoai, String gioiTinh, String hoTen, Date ngaySinh, String diaChi) {
        this.cccd = cccd;
        this.soDienThoai = soDienThoai;
        this.gioiTinh = gioiTinh;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
    }

    //
    // 2. CUNG CẤP CÁC PHƯƠNG THỨC GETTER VÀ SETTER PUBLIC
    //

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        // Ví dụ về logic kiểm tra dữ liệu: CCCD không được để trống
        if (cccd != null && !cccd.trim().isEmpty()) {
            this.cccd = cccd;
        } else {
            System.out.println("Lỗi: CCCD không hợp lệ.");
        }
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "BanQuanLy{" +
                "hoTen='" + hoTen + '\'' +
                ", cccd='" + cccd + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                '}';
    }
}