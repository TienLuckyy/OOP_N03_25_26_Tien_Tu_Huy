

import java.io.Serializable;
import java.util.Date;

public class SinhVien implements Serializable {

    // serialVersionUID cần thiết cho việc serialization, giúp kiểm soát phiên bản của lớp.
    private static final long serialVersionUID = 1L;

    // --- Các thuộc tính của Sinh Viên ---
    private String mssv;      
    private String ten;       //them
    private String queQuan;   
    private Date ngaySinh;    //them
    private String gioiTinh;  
    private String sdt;      
    private String cccd;      
    private String lop;       
    private String nganhHoc;  

    // --- Hàm khởi tạo (Constructors) ---

    /**
     * Hàm khởi tạo mặc định (không tham số).
     */
    public SinhVien() {
    }

    /**
     * Hàm khởi tạo đầy đủ tham số để tạo một đối tượng SinhVien mới.
     *
     * @param mssv      Mã số sinh viên
     * @param ten       Họ và tên   //add
     * @param queQuan   Quê quán
     * @param ngaySinh  Ngày sinh   //add
     * @param gioiTinh  Giới tính
     * @param sdt       Số điện thoại
     * @param cccd      Số CCCD
     * @param lop       Lớp học
     * @param nganhHoc  Ngành học
     */
    public SinhVien(String mssv, String ten, String queQuan, Date ngaySinh, String gioiTinh, String sdt, String cccd, String lop, String nganhHoc) {
        this.mssv = mssv;
        this.ten = ten;
        this.queQuan = queQuan;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.cccd = cccd;
        this.lop = lop;
        this.nganhHoc = nganhHoc;
    }


    // --- Các phương thức Getter và Setter ---

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getNganhHoc() {
        return nganhHoc;
    }

    public void setNganhHoc(String nganhHoc) {
        this.nganhHoc = nganhHoc;
    }


    // --- Phương thức toString() ---

    /**
     * Trả về một chuỗi đại diện cho thông tin của đối tượng SinhVien.
     * Rất hữu ích cho việc gỡ lỗi (debug) và ghi log.
     *
     * @return Chuỗi thông tin sinh viên.
     */
    @Override
    public String toString() {
        return "SinhVien{" +
                "mssv='" + mssv + '\'' +
                ", ten='" + ten + '\'' +
                ", queQuan='" + queQuan + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", sdt='" + sdt + '\'' +
                ", cccd='" + cccd + '\'' +
                ", lop='" + lop + '\'' +
                ", nganhHoc='" + nganhHoc + '\'' +
                '}';
    }
}