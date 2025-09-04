import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App {
    public static void main(String[] args) {
        try {
            // Định nghĩa template ngày sinh
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // 1. Tạo sinh viên bằng constructor đầy đủ
            Date ngaySinh1 = sdf.parse("30/04/2004");
            SinhVien sv1 = new SinhVien("SV001", "Nguyen Van A", "Hà Nội",
                                        ngaySinh1, "Nam", "0123456789",
                                        "123456789", "CNTT1", "Công nghệ thông tin");

            System.out.println("===== Sinh viên 1 =====");
            System.out.println(sv1);

            // 2. Tạo sinh viên bằng constructor mặc định + setter
            SinhVien sv2 = new SinhVien();
            sv2.setMssv("SV002");
            sv2.setTen("Tran Thi B");
            sv2.setQueQuan("TP.HCM");
            sv2.setNgaySinh(sdf.parse("15/08/2003"));
            sv2.setGioiTinh("Nữ");
            sv2.setSdt("0987654321");
            sv2.setCccd("987654321");
            sv2.setLop("CNTT2");
            sv2.setNganhHoc("Khoa học máy tính");

            System.out.println("\n===== Sinh viên 2 =====");
            System.out.println(sv2);

            // 3. Lấy thông tin cụ thể bằng getter
            System.out.println("\nNgày sinh sv2: " + sdf.format(sv2.getNgaySinh()));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
