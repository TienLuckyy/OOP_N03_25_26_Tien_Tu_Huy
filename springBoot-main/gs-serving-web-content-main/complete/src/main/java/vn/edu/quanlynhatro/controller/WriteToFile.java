package vn.edu.quanlynhatro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.quanlynhatro.model.Phong;
import vn.edu.quanlynhatro.repository.PhongRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class WriteToFile {

    @Autowired
    private PhongRepository phongRepository;

    // ✅ Đường dẫn file lưu dữ liệu
    private final String filePath =
        "C:\\Users\\duong\\OOP_N03_25_26_Tien_Tu_Huy\\springBoot-main\\gs-serving-web-content-main\\complete\\File\\phong.txt";

    // ✅ Ghi toàn bộ dữ liệu phòng ra file UTF-8
    public void exportPhongData() {
        List<Phong> danhSachPhong = phongRepository.findAll();
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Tạo thư mục nếu chưa có

        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(file, false), StandardCharsets.UTF_8)) {

            writer.write("\uFEFF"); // ✅ Ghi BOM UTF-8 (giúp Notepad hiển thị đúng tiếng Việt)
            writer.write("===== DANH SÁCH PHÒNG =====\n");

            for (Phong phong : danhSachPhong) {
                writer.write(formatPhong(phong));
                writer.write("\n");
            }

            writer.write("===========================\n");
            writer.write("Cập nhật lần cuối: " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n");

            writer.flush();
            System.out.println("✅ Đã lưu danh sách phòng vào: " + filePath);
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi ghi file phong.txt: " + e.getMessage());
        }
    }

    private String formatPhong(Phong phong) {
        return String.format(
            "Số phòng: %s | Tòa: %s | Tiền điện nước: %.2f | Hiện tại: %d | Tối đa: %d | Trạng thái: %s",
            phong.getSoPhong(),
            phong.getToa(),
            phong.getTienDienNuoc() != null ? phong.getTienDienNuoc() : 0.0,
            phong.getSoNguoiHienTai() != null ? phong.getSoNguoiHienTai() : 0,
            phong.getSoNguoiToiDa() != null ? phong.getSoNguoiToiDa() : 0,
            Boolean.TRUE.equals(phong.getTrangThai()) ? "Đang thuê" : "Trống"
        );
    }
}
