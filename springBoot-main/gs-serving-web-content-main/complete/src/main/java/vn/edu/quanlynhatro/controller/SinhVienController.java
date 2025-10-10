package vn.edu.quanlynhatro.controller;

import vn.edu.quanlynhatro.model.SinhVien;
import vn.edu.quanlynhatro.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/sinhvien")
public class SinhVienController {

    @Autowired
    private SinhVienService sinhVienService;

    // --- DANH SÁCH SINH VIÊN ---
    @GetMapping("/list")
    public String hienThiTatCaSinhVien(Model model) {
        List<SinhVien> danhSach = sinhVienService.getAllSinhVien();
        model.addAttribute("sinhviens", danhSach);
        model.addAttribute("title", "Danh Sách Sinh Viên");
        return "sinhvien/list";
    }

    // --- FORM THÊM SINH VIÊN ---
    @GetMapping("/add")
    public String hienThiThemSVUI(Model model) {
        model.addAttribute("sinhvien", new SinhVien()); // cần constructor rỗng trong SinhVien.java
        model.addAttribute("title", "Thêm Sinh Viên");
        return "sinhvien/add";
    }

    // --- LƯU SINH VIÊN ---
    @PostMapping("/save")
    public String xuLyThemSinhVien(@ModelAttribute("sinhvien") SinhVien sinhVien) {
        sinhVienService.themSinhVien(sinhVien);
        return "redirect:/sinhvien/list";
    }

    // --- FORM SỬA SINH VIÊN ---
    @GetMapping("/edit/{mssv}")
    public String hienThiSuaSVUI(@PathVariable("mssv") String mssv, Model model) {
        SinhVien sinhVien = sinhVienService.timKiemTheoMssv(mssv);
        if (sinhVien != null) {
            model.addAttribute("sinhvien", sinhVien);
            model.addAttribute("title", "Chỉnh Sửa Sinh Viên");
            return "sinhvien/edit";
        }
        model.addAttribute("message", "Không tìm thấy sinh viên có MSSV: " + mssv);
        return "redirect:/sinhvien/list";
    }

    // --- CẬP NHẬT SINH VIÊN ---
    @PostMapping("/update")
    public String xuLySuaSinhVien(@ModelAttribute("sinhvien") SinhVien sinhVien) {
        sinhVienService.suaSinhVien(sinhVien);
        return "redirect:/sinhvien/list";
    }

    // --- XÓA SINH VIÊN ---
    @GetMapping("/delete/{mssv}")
    public String xoaSinhVien(@PathVariable("mssv") String mssv) {
        sinhVienService.xoaSinhVien(mssv);
        return "redirect:/sinhvien/list";
    }

    // --- TÌM KIẾM SINH VIÊN THEO MSSV ---
    @GetMapping("/search")
    public String hienThiTimKiemSVUI(@RequestParam("mssv") String mssv, Model model) {
        SinhVien sv = sinhVienService.timKiemTheoMssv(mssv);
        if (sv != null) {
            model.addAttribute("sinhviens", List.of(sv));
            model.addAttribute("title", "Kết quả tìm kiếm");
        } else {
            model.addAttribute("sinhviens", List.of());
            model.addAttribute("message", "Không tìm thấy sinh viên có MSSV: " + mssv);
        }
        return "sinhvien/list";
    }
}
