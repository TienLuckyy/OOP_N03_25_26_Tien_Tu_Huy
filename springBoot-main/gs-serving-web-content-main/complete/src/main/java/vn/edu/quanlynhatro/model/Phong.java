package vn.edu.quanlynhatro.model;

import java.io.Serializable;
// import jakarta.persistence.Transient; <-- XÓA DÒNG NÀY

public class Phong implements Serializable {
    private static final long serialVersionUID = 1L;

    private String soPhong;
    private boolean trangThai; 
    private double tienDienNuoc;

    public Phong() {}

    public Phong(String soPhong, boolean trangThai, double tienDienNuoc) {
        this.soPhong = soPhong;
        this.trangThai = trangThai;
        this.tienDienNuoc = tienDienNuoc;
    }

    // Getters & Setters (giữ nguyên)
    public String getSoPhong() { return soPhong; }
    public void setSoPhong(String soPhong) { this.soPhong = soPhong; }
    public boolean isTrangThai() { return trangThai; }
    public void setTrangThai(boolean trangThai) { this.trangThai = trangThai; }
    public double getTienDienNuoc() { return tienDienNuoc; }
    public void setTienDienNuoc(double tienDienNuoc) { this.tienDienNuoc = tienDienNuoc; }
    
    // XÓA CHÚ THÍCH @Transient VÀ GIỮ NGUYÊN PHƯƠNG THỨC
    public void hienThiThongTin() { 
        System.out.println("Phòng: " + soPhong + " | Trạng thái: " + (trangThai ? "Đang sử dụng" : "Trống") + " | Tiền điện nước: " + tienDienNuoc);
    }
}