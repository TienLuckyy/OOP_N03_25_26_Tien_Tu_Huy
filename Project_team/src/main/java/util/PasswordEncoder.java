package vn.edu.quanlynhatro.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Lớp tiện ích để mã hóa mật khẩu.
 */
public class PasswordEncoder {

    /**
     * Mã hóa một chuỗi String sử dụng thuật toán MD5.
     * @param password Mật khẩu gốc (dạng plain text)
     * @return Chuỗi String đã được mã hóa MD5 (dạng hex, 32 ký tự)
     */
    public static String maHoaMD5(String password) {
        try {
            // Tạo một instance của MessageDigest với thuật toán MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Tính toán hash của chuỗi password
            byte[] messageDigest = md.digest(password.getBytes());

            // Chuyển mảng byte thành dạng số BigInteger
            BigInteger no = new BigInteger(1, messageDigest);

            // Chuyển BigInteger thành chuỗi hex
            String hashtext = no.toString(16);

            // Thêm các số 0 vào đầu để đảm bảo chuỗi luôn có 32 ký tự
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            // Lỗi này rất hiếm khi xảy ra
            throw new RuntimeException("Lỗi không tìm thấy thuật toán MD5", e);
        }
    }
}
