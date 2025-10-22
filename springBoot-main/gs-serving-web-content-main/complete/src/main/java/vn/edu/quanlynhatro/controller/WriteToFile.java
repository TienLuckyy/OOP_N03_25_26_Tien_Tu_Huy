package vn.edu.quanlynhatro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.quanlynhatro.model.Phong;
import vn.edu.quanlynhatro.model.SinhVien;
import vn.edu.quanlynhatro.model.BanQuanLy;
import vn.edu.quanlynhatro.repository.PhongRepository;
import vn.edu.quanlynhatro.repository.SinhVienRepository;
import vn.edu.quanlynhatro.repository.BanQuanLyRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class WriteToFile {

    @Autowired
    private PhongRepository phongRepository;

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Autowired
    private BanQuanLyRepository banQuanLyRepository;

    private final String folderPath =
            "C:\\Users\\duong\\OOP_N03_25_26_Tien_Tu_Huy\\springBoot-main\\gs-serving-web-content-main\\complete\\File";

    // === Phòng ===
    public void exportPhongData() {
        List<Phong> danhSachPhong = phongRepository.findAll();
        String filePath = folderPath + "\\phong.txt";
        createFolderIfNotExist();

        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(filePath, false), StandardCharsets.UTF_8)) {

            writer.write("\uFEFF"); // BOM UTF-8
            writer.write("===== DANH SÁCH PHÒNG =====\n\n");

            for (Phong phong : danhSachPhong) {
                writer.write(formatPhong(phong));
                writer.write("\n");
            }

            writer.write("\n===========================\n");
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
                "Số phòng: %s | Tòa: %s | Tiền nhà: %.2f | Hiện tại: %d | Tối đa: %d | Trạng thái: %s",
                phong.getSoPhong(),
                phong.getToa(),
                phong.getTienNha() != null ? phong.getTienNha() : 0.0,
                phong.getSinhViens() != null ? phong.getSinhViens().size() : 0,
                phong.getSoNguoiToiDa() != null ? phong.getSoNguoiToiDa() : 0,
                Boolean.TRUE.equals(phong.getTrangThai()) ? "Đang thuê" : "Trống"
        );
    }

    // === Sinh viên ===
    public void exportSinhVienData() {
        List<SinhVien> danhSachSinhVien = sinhVienRepository.findAll();
        String filePath = folderPath + "\\sinhvien.txt";
        createFolderIfNotExist();

        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(filePath, false), StandardCharsets.UTF_8)) {

            writer.write("\uFEFF");
            writer.write("===== DANH SÁCH SINH VIÊN =====\n\n");

            for (SinhVien sv : danhSachSinhVien) {
                writer.write(formatSinhVien(sv));
                writer.write("\n");
            }

            writer.write("\n===========================\n");
            writer.write("Cập nhật lần cuối: " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n");

            writer.flush();
            System.out.println("✅ Đã lưu danh sách sinh viên vào: " + filePath);
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi ghi file sinhvien.txt: " + e.getMessage());
        }
    }

private String formatSinhVien(SinhVien sv) {
    String phongInfo = (sv.getPhong() != null) ?
            sv.getPhong().getSoPhong() + " - " + sv.getPhong().getToa() :
            "Chưa có phòng";
    return String.format(
            "MSSV: %s | Họ tên: %s | Giới tính: %s | Ngày Sinh: %s | Lớp: %s | Ngành: %s | CCCD: %s | SĐT: %s | Quê quán: %s | Địa chỉ: %s | Phòng: %s",
            sv.getMssv(),
            sv.getHoTen(),
            sv.getGioiTinh(),
            sv.getNgaySinh(),
            sv.getLop(),
            sv.getNganhHoc(),
            sv.getCccd(),
            sv.getSoDienThoai(),
            sv.getQueQuan(),
            sv.getDiaChi(),
            phongInfo
    );
}

    // === Ban quản lý ===
    public void exportBanQuanLyData() {
        List<BanQuanLy> danhSachBan = banQuanLyRepository.findAll();
        String filePath = folderPath + "\\banquanly.txt";
        createFolderIfNotExist();

        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(filePath, false), StandardCharsets.UTF_8)) {

            writer.write("\uFEFF");
            writer.write("===== DANH SÁCH BAN QUẢN LÝ =====\n\n");

            for (BanQuanLy bql : danhSachBan) {
                writer.write(formatBanQuanLy(bql));
                writer.write("\n");
            }

            writer.write("\n===========================\n");
            writer.write("Cập nhật lần cuối: " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n");

            writer.flush();
            System.out.println("✅ Đã lưu danh sách Ban quản lý vào: " + filePath);
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi ghi file banquanly.txt: " + e.getMessage());
        }
    }

private String formatBanQuanLy(BanQuanLy bql) {
    return String.format(
            "ID: %d | Họ tên: %s | CCCD: %s | Ngày Sinh: %s | Mã NV: %s | Giới tính: %s | Địa chỉ: %s | SĐT: %s | Tòa phụ trách: %s",
            bql.getId(),
            bql.getHoTen(),
            bql.getCccd(),
            bql.getNgaySinh(),
            bql.getMaNhanVien(),
            bql.getGioiTinh(),
            bql.getDiaChi(),
            bql.getSoDienThoai(),
            bql.getToaPhuTrach()
    );
}

    // === Hỗ trợ tạo folder ===
    private void createFolderIfNotExist() {
        File folder = new File(folderPath);
        if (!folder.exists()) folder.mkdirs();
    }

    // === Xuất tất cả ===
    public void exportAll() {
        exportPhongData();
        exportSinhVienData();
        exportBanQuanLyData();
    }
}
