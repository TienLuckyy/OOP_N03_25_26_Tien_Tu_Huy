package vn.edu.quanlynhatro.model;

import java.util.Date;

public class BanQuanLy extends Nguoi {
    public BanQuanLy(String hoTen, String gioiTinh, String cccd, String soDienThoai, Date ngaySinh, String diaChi) {
        super(hoTen, gioiTinh, cccd, soDienThoai, ngaySinh, diaChi);
    }

    @Override
    public String getThongTin() {
        return "Ban Quan Ly: " + hoTen + " - CCCD: " + cccd;
    }
}
