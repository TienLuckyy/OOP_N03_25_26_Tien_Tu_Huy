package vn.edu.quanlynhatro.controller;

import vn.edu.quanlynhatro.model.SinhVien;
import vn.edu.quanlynhatro.service.SinhVienService;
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
    private SinhVienService sinhVienService; // Đảm bảo sử dụng SinhVienService đã chuyển đổi sang JPA

    // ------------------- DANH SÁCH SINH VIÊN (READ ALL) -------------------
    @GetMapping({"/list", "/"})
    public String hienThiTatCaSinhVien(Model model) {
        List<SinhVien> danhSach = sinhVienService.getAllSinhVien();
        model.addAttribute("sinhviens", danhSach);
        model.addAttribute("title", "Danh Sách Sinh Viên");
        return "sinhvien/list";
    }

    // ------------------- FORM THÊM SINH VIÊN (CREATE UI) -------------------
    @GetMapping("/add")
    public String hienThiThemSVUI(Model model) {
        model.addAttribute("sinhvien", new SinhVien()); 
        model.addAttribute("title", "Thêm Sinh Viên");
        return "sinhvien/add";
    }

    // ------------------- LƯU SINH VIÊN (CREATE/UPDATE LOGIC) -------------------
    @PostMapping("/save")
    public String xuLyLuuSinhVien(@ModelAttribute("sinhvien") SinhVien sinhVien, RedirectAttributes ra) {
        // JPA save() xử lý cả thêm mới (id=null) và cập nhật (id tồn tại)
        sinhVienService.luuHoacSuaSinhVien(sinhVien); 
        ra.addFlashAttribute("message", "Lưu sinh viên thành công!");
        return "redirect:/sinhvien/list";
    }

    // ------------------- FORM SỬA SINH VIÊN (UPDATE UI - Dùng ID) -------------------
    @GetMapping("/edit/{id}")
    public String hienThiSuaSVUI(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        // Tìm kiếm theo ID khóa chính
        Optional<SinhVien> svOptional = sinhVienService.timKiemTheoId(id); 
        
        if (svOptional.isPresent()) {
            model.addAttribute("sinhvien", svOptional.get());
            model.addAttribute("title", "Chỉnh Sửa Sinh Viên");
            return "sinhvien/edit";
        }
        
        ra.addFlashAttribute("errorMessage", "Không tìm thấy sinh viên có ID: " + id);
        return "redirect:/sinhvien/list";
    }

    // ------------------- XÓA SINH VIÊN (DELETE - Dùng ID) -------------------
    @GetMapping("/delete/{id}")
    public String xoaSinhVien(@PathVariable("id") Long id, RedirectAttributes ra) {
        if (sinhVienService.xoaSinhVien(id)) {
            ra.addFlashAttribute("message", "Xóa sinh viên thành công!");
        } else {
            ra.addFlashAttribute("errorMessage", "Không tìm thấy sinh viên để xóa.");
        }
        return "redirect:/sinhvien/list";
    }

    // ------------------- TÌM KIẾM SINH VIÊN THEO MSSV (CUSTOM READ) -------------------
    @GetMapping("/search")
    public String timKiemSinhVienTheoMssv(@RequestParam(value = "mssv", required = false) String mssv, Model model) {
        
        if (mssv == null || mssv.trim().isEmpty()) {
            // Nếu không nhập MSSV, hiển thị tất cả
             return "redirect:/sinhvien/list"; 
        }

        SinhVien sv = sinhVienService.timKiemTheoMssv(mssv); // Cần phương thức findByMssv trong Service
        
        if (sv != null) {
            model.addAttribute("sinhviens", List.of(sv));
            model.addAttribute("title", "Kết quả tìm kiếm");
        } else {
            model.addAttribute("sinhviens", List.of());
            model.addAttribute("errorMessage", "Không tìm thấy sinh viên có MSSV: " + mssv);
        }
        return "sinhvien/list";
    }
}