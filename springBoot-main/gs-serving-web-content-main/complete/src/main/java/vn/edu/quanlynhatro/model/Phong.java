package vn.edu.quanlynhatro.model;

import java.io.Serializable;

public class Phong implements Serializable {
    private static final long serialVersionUID = 1L;

    // Thuộc tính
    private String soPhong;
    private boolean trangThai; // true = đang sử dụng, false = trống
    private double tienDienNuoc;

    // Constructor
    public Phong(String soPhong, boolean trangThai, double tienDienNuoc) {
        this.soPhong = soPhong;
        this.trangThai = trangThai;
        this.tienDienNuoc = tienDienNuoc;
    }

    // Getter & Setter
    public String getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(String soPhong) {
        this.soPhong = soPhong;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public double getTienDienNuoc() {
        return tienDienNuoc;
    }

    public void setTienDienNuoc(double tienDienNuoc) {
        this.tienDienNuoc = tienDienNuoc;
    }

    // Hàm hiển thị thông tin
    public void hienThiThongTin() {
        System.out.println("Phòng: " + soPhong +
                           " | Trạng thái: " + (trangThai ? "Đang sử dụng" : "Trống") +
                           " | Tiền điện nước: " + tienDienNuoc);
    }
}
