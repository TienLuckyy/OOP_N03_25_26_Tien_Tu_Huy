public class TaiKhoan {
    // Encapsulation
    private String email;
    private String matKhau;

    // Constructor
    public TaiKhoan(String email, String matKhau) {
        this.email = email;
        this.matKhau = matKhau;
    }

    // Getter & Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    // Chức năng đăng ký
    public static TaiKhoan dangKy(String email, String matKhau) {
        return new TaiKhoan(email, matKhau);
    }

    // Chức năng đăng nhập
    public boolean dangNhap(String email, String matKhau) {
        return this.email.equals(email) && this.matKhau.equals(matKhau);
    }
}
