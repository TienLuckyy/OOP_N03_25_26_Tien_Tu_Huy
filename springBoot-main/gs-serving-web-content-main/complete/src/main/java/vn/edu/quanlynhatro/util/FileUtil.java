package vn.edu.quanlynhatro.util;

import java.io.*;

/**
 * Lớp tiện ích chứa các phương thức static để xử lý việc đọc/ghi file nhị phân.
 */
public class FileUtil {

    /**
     * Ghi một đối tượng bất kỳ xuống file.
     * @param fileName Tên file (bao gồm cả đường dẫn, vd: "data/sinhvien.dat")
     * @param data Dữ liệu cần ghi (ví dụ: một ArrayList)
     */
    public static void ghiFile(String fileName, Object data) {
        // Sử dụng try-with-resources để đảm bảo stream được đóng tự động
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file " + fileName + ": " + e.getMessage());
        }
    }

    /**
     * Đọc một đối tượng từ file.
     * @param fileName Tên file cần đọc
     * @return Đối tượng đọc được từ file, hoặc null nếu file không tồn tại hoặc có lỗi.
     */
    public static Object docFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return null; // Trả về null nếu file không tồn tại
        }

        // Sử dụng try-with-resources
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Lỗi khi đọc file " + fileName + ": " + e.getMessage());
            return null;
        }
    }
}