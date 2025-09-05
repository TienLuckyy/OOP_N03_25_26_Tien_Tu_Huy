public class User {
    private String ten;
    private String maSinhVien;
    private int soPhong;
    private String soDienThoai;

    public User(String ten, String maSinhVien, int soPhong, String soDienThoai) {
        this.ten = ten;
        this.maSinhVien = maSinhVien;
        this.soPhong = soPhong;
        this.soDienThoai = soDienThoai;
    }

    public String getTen() {
        return ten;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void hienThiThongTin() {
        System.out.println("Ten: " + ten);
        System.out.println("Ma sinh vien: " + maSinhVien);
        System.out.println("So phong: " + soPhong);
        System.out.println("So dien thoai: " + soDienThoai);
    }
}
