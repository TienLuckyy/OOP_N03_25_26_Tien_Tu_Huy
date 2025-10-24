package vn.edu.quanlynhatro.controller;

import vn.edu.quanlynhatro.model.SinhVien;
import vn.edu.quanlynhatro.service.SinhVienService;
import vn.edu.quanlynhatro.exception.ResourceNotFoundException; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sinhvien")
public class SinhVienController {

    @Autowired
    private SinhVienService sinhVienService;

    // ------------------- DANH SÁCH SINH VIÊN -------------------
    @GetMapping({"/list", "/"})
    public String hienThiTatCaSinhVien(Model model) {
        List<SinhVien> danhSach = sinhVienService.getAll();
        model.addAttribute("sinhviens", danhSach);
        model.addAttribute("title", "Danh Sách Sinh Viên");
        return "sinhvien/list";
    }

    // ------------------- FORM THÊM SINH VIÊN -------------------
    @GetMapping("/add")
    public String hienThiThemSVUI(Model model) {
        model.addAttribute("sinhvien", new SinhVien());
        model.addAttribute("title", "Thêm Sinh Viên");
        return "sinhvien/add";
    }

    // ------------------- LƯU SINH VIÊN -------------------
    @PostMapping("/save")
    public String xuLyLuuSinhVien(@ModelAttribute("sinhvien") SinhVien sinhVien,
                                 RedirectAttributes ra) {

        if (sinhVien.getId() != null) {
            Optional<SinhVien> existingSinhVien = sinhVienService.findById(sinhVien.getId());
            if (existingSinhVien.isPresent()) {
                SinhVien existing = existingSinhVien.get();
                if (sinhVien.getPhong() == null && existing.getPhong() != null) {
                    sinhVien.setPhong(existing.getPhong());
                }
            }
        }

        // Kiểm tra MSSV trùng 
        if (sinhVien.getId() == null) {
            Optional<SinhVien> svMssv = sinhVienService.findByMssv(sinhVien.getMssv());
            if (svMssv.isPresent()) {
                ra.addFlashAttribute("error", "MSSV '" + sinhVien.getMssv() + "' đã tồn tại!");
                return "redirect:/sinhvien/add";
            }
        }

        // Kiểm tra CCCD trùng 
        if (sinhVien.getId() == null) {
            Optional<SinhVien> svCccd = sinhVienService.findByCccd(sinhVien.getCccd());
            if (svCccd.isPresent()) {
                ra.addFlashAttribute("error", "CCCD '" + sinhVien.getCccd() + "' đã tồn tại!");
                return "redirect:/sinhvien/add";
            }
        }

        // Kiểm tra SĐT trùng 
        if (sinhVien.getId() == null && sinhVien.getSoDienThoai() != null && !sinhVien.getSoDienThoai().isEmpty()) {
            Optional<SinhVien> svSdt = sinhVienService.findBySoDienThoai(sinhVien.getSoDienThoai());
            if (svSdt.isPresent()) {
                ra.addFlashAttribute("error", "Số điện thoại '" + sinhVien.getSoDienThoai() + "' đã tồn tại!");
                return "redirect:/sinhvien/add";
            }
        }

        sinhVienService.save(sinhVien);
        ra.addFlashAttribute("success", "Lưu sinh viên thành công!");
        return "redirect:/sinhvien/list";
    }

    // ------------------- FORM SỬA SINH VIÊN -------------------
    @GetMapping("/edit/{id}")
    public String hienThiSuaSVUI(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        Optional<SinhVien> svOptional = sinhVienService.findById(id);
        if (svOptional.isPresent()) {
            model.addAttribute("sinhvien", svOptional.get());
            model.addAttribute("title", "Chỉnh Sửa Sinh Viên");
            return "sinhvien/edit";
        }
        ra.addFlashAttribute("error", "Không tìm thấy sinh viên có ID: " + id);
        return "redirect:/sinhvien/list";
    }

    @GetMapping("/detail/{id}")
    public String detailSinhVien(@PathVariable Long id, Model model, RedirectAttributes ra) {
        Optional<SinhVien> svOptional = sinhVienService.findById(id);
        if (svOptional.isEmpty()) {
            ra.addFlashAttribute("error", "Sinh viên không tồn tại.");
            return "redirect:/sinhvien/list";
        }
        model.addAttribute("sinhvien", svOptional.get());
        model.addAttribute("title", "Chi Tiết Sinh Viên");
        return "sinhvien/detail";
    }

    // ------------------- XÓA SINH VIÊN -------------------
    @GetMapping("/delete/{id}")
    public String xoaSinhVien(@PathVariable("id") Long id,
                              RedirectAttributes ra) {
        try {
            sinhVienService.delete(id);
            ra.addFlashAttribute("success", "Xóa sinh viên ID " + id + " thành công!");
        } catch (ResourceNotFoundException e) {
            // Bắt ngoại lệ nếu Service ném ra khi không tìm thấy ID để xóa
            ra.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            // Bắt các lỗi khác (ví dụ: lỗi ràng buộc khóa ngoại, lỗi DB)
            ra.addFlashAttribute("error", "Đã xảy ra lỗi không xác định khi xóa sinh viên: " + e.getMessage());
        }
        return "redirect:/sinhvien/list";
    }

    // ------------------- TÌM KIẾM SINH VIÊN THEO MSSV -------------------
    @GetMapping("/search")
    public String timKiemSinhVienTheoMssv(@RequestParam(value = "mssv", required = false) String mssv,
                                         Model model) {
        if (mssv == null || mssv.trim().isEmpty()) {
            return "redirect:/sinhvien/list";
        }

        Optional<SinhVien> svOptional = sinhVienService.findByMssv(mssv);

        if (svOptional.isPresent()) {
            model.addAttribute("sinhviens", List.of(svOptional.get()));
            model.addAttribute("title", "Kết quả tìm kiếm");
        } else {
            model.addAttribute("sinhviens", List.of());
            model.addAttribute("error", "Không tìm thấy sinh viên có MSSV: " + mssv);
        }

        return "sinhvien/list";
    }
}