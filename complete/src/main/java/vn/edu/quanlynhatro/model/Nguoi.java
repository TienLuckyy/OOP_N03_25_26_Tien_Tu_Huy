package vn.edu.quanlynhatro.model;

import java.io.Serializable;
import java.util.Date;

public abstract class Nguoi implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String hoTen;
    protected String gioiTinh;
    protected String cccd;
    protected String soDienThoai;
    protected Date ngaySinh;
    protected String diaChi;

    public Nguoi(String hoTen, String gioiTinh, String cccd, String soDienThoai, Date ngaySinh, String diaChi) {
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.cccd = cccd;
        this.soDienThoai = soDienThoai;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
    }

    // Getter & Setter
    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getCccd() { return cccd; }
    public void setCccd(String cccd) { this.cccd = cccd; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public Date getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(Date ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    // Phương thức trừu tượng để các class con override
    public abstract String getThongTin();
}
