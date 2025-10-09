package vn.edu.quanlynhatro.model;

import java.util.Date;
import jakarta.persistence.Entity; 
import jakarta.persistence.Id;
import jakarta.persistence.Transient; 

@Entity 
public class SinhVien extends Nguoi { 
    
    @Id 
    private String mssv;
    
    private String lop;
    private String nganhHoc;
    private String queQuan;

    public SinhVien() {
        super(); 
    }

    public SinhVien(String mssv, String hoTen, String gioiTinh, String cccd, String soDienThoai,
                    Date ngaySinh, String diaChi, String lop, String nganhHoc, String queQuan) {
        super(hoTen, gioiTinh, cccd, soDienThoai, ngaySinh, diaChi); 
        this.mssv = mssv;
        this.lop = lop;
        this.nganhHoc = nganhHoc;
        this.queQuan = queQuan;
    }

    public String getMssv() { return mssv; }
    public void setMssv(String mssv) { this.mssv = mssv; }
    public String getLop() { return lop; }
    public void setLop(String lop) { this.lop = lop; }
    public String getNganhHoc() { return nganhHoc; }
    public void setNganhHoc(String nganhHoc) { this.nganhHoc = nganhHoc; }
    public String getQueQuan() { return queQuan; }
    public void setQueQuan(String queQuan) { this.queQuan = queQuan; }

    @Override
    @Transient 
    public String getThongTin() {
        return "Sinh viên: " + getHoTen() + " - MSSV: " + mssv;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "mssv='" + mssv + '\'' +
                ", hoTen='" + getHoTen() + '\'' +
                ", gioiTinh='" + getGioiTinh() + '\'' +
                ", cccd='" + getCccd() + '\'' +
                ", soDienThoai='" + getSoDienThoai() + '\'' +
                ", ngaySinh=" + getNgaySinh() +
                ", diaChi='" + getDiaChi() + '\'' +
                ", lop='" + lop + '\'' +
                ", nganhHoc='" + nganhHoc + '\'' +
                ", queQuan='" + queQuan + '\'' +
                '}';
    }
}