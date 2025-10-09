package vn.edu.quanlynhatro.controller;

import vn.edu.quanlynhatro.service.SinhVienService;
import vn.edu.quanlynhatro.model.SinhVien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/sinhvien")
public class SinhVienController {

    private final SinhVienService sinhVienService;

    @Autowired
    public SinhVienController(SinhVienService sinhVienService) {
        this.sinhVienService = sinhVienService;
    }

    @GetMapping({"", "/"})
    public String hienThiTatCaSV(Model model) {
        List<SinhVien> danhSach = sinhVienService.getAllSinhVien();
        model.addAttribute("danhSachSinhVien", danhSach);
        return "admin/danhsachsinhvien"; 
    }

    @GetMapping("/them")
    public String hienThiFormThemSV(Model model) {
        model.addAttribute("sinhVien", new SinhVien()); 
        return "admin/formsinhvien";
    }

    @PostMapping("/them")
    public String themSinhVien(@ModelAttribute("sinhVien") SinhVien svMoi, RedirectAttributes redirectAttributes) {
        if (sinhVienService.timKiemTheoMssv(svMoi.getMssv()) != null) {
            redirectAttributes.addFlashAttribute("error", "Lỗi: MSSV đã tồn tại trong hệ thống.");
            return "redirect:/admin/sinhvien/them";
        }
        
        sinhVienService.themSinhVien(svMoi);
        redirectAttributes.addFlashAttribute("success", "Thêm sinh viên thành công!");
        return "redirect:/admin/sinhvien";
    }

    @GetMapping("/sua/{mssv}")
    public String hienThiFormSuaSV(@PathVariable("mssv") String mssv, Model model) {
        SinhVien svCanSua = sinhVienService.timKiemTheoMssv(mssv);
        
        if (svCanSua == null) {
            return "redirect:/admin/sinhvien"; 
        }

        model.addAttribute("sinhVien", svCanSua);
        return "admin/formsinhvien";
    }

    @PostMapping("/sua")
    public String suaSinhVien(@ModelAttribute("sinhVien") SinhVien svDaSua, RedirectAttributes redirectAttributes) {
        sinhVienService.suaSinhVien(svDaSua);
        redirectAttributes.addFlashAttribute("success", "Cập nhật thông tin thành công!");
        return "redirect:/admin/sinhvien"; 
    }

    @GetMapping("/xoa/{mssv}")
    public String xoaSinhVien(@PathVariable("mssv") String mssv, RedirectAttributes redirectAttributes) {
        if (sinhVienService.timKiemTheoMssv(mssv) != null) {
            sinhVienService.xoaSinhVien(mssv);
            redirectAttributes.addFlashAttribute("success", "Xóa sinh viên " + mssv + " thành công.");
        }
        
        return "redirect:/admin/sinhvien"; 
    }
    
    @GetMapping("/timkiem")
    public String timKiemSV(@RequestParam("mssv") String mssv, Model model) {
        SinhVien sv = sinhVienService.timKiemTheoMssv(mssv);
        
        model.addAttribute("sinhVienKetQua", sv);
        
        return "admin/ketquatimkiem"; 
    }
}