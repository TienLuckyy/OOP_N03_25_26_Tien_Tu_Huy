package vn.edu.quanlynhatro.util;

import vn.edu.quanlynhatro.model.TaiKhoan;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Utility nhỏ chạy từ command-line (maven/IDE) để in danh sách tài khoản từ data/taikhoan.dat
 * Lưu ý: chỉ dùng cho debug local, không commit output chứa mật khẩu vào nơi công khai.
 */
public class DebugPrintAccounts {
    public static void main(String[] args) {
        String path = "data/taikhoan.dat";
        if (args.length > 0) path = args[0];
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                List<?> list = (List<?>) obj;
                System.out.println("Found " + list.size() + " accounts:");
                for (Object o : list) {
                    if (o instanceof TaiKhoan) {
                        TaiKhoan tk = (TaiKhoan) o;
                        System.out.println("- username=" + tk.getUsername() + ", passwordHash=" + tk.getPassword() + ", role=" + tk.getRole());
                    } else {
                        System.out.println("- Unknown object: " + o);
                    }
                }
            } else {
                System.out.println("File does not contain a List: " + obj);
            }
        } catch (Exception e) {
            System.err.println("Error reading accounts file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
