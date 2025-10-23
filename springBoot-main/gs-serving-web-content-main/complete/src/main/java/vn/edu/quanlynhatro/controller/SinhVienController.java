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
    private SinhVienService sinhVienService;

    // ------------------- DANH SÁCH SINH VIÊN (READ ALL) -------------------
    @GetMapping({"/list", "/"})
    public String hienThiTatCaSinhVien(Model model) {
        List<SinhVien> danhSach = sinhVienService.getAll();
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
public String xuLyLuuSinhVien(@ModelAttribute("sinhvien") SinhVien sinhVien,
                              RedirectAttributes ra) {

    // 🔥 QUAN TRỌNG: Giữ lại thông tin phòng khi update
    if (sinhVien.getId() != null) {
        // Nếu là update, lấy thông tin phòng hiện tại từ database
        Optional<SinhVien> existingSinhVien = sinhVienService.findById(sinhVien.getId());
        if (existingSinhVien.isPresent()) {
            SinhVien existing = existingSinhVien.get();
            // Giữ lại thông tin phòng nếu không thay đổi
            if (sinhVien.getPhong() == null && existing.getPhong() != null) {
                sinhVien.setPhong(existing.getPhong());
            }
        }
    }

    // Kiểm tra MSSV trùng (chỉ với sinh viên mới)
    if (sinhVien.getId() == null) {
        Optional<SinhVien> svMssv = sinhVienService.findByMssv(sinhVien.getMssv());
        if (svMssv.isPresent()) {
            ra.addFlashAttribute("error", "MSSV '" + sinhVien.getMssv() + "' đã tồn tại!");
            return "redirect:/sinhvien/add";
        }
    }

    // Kiểm tra CCCD trùng (chỉ với sinh viên mới)
    if (sinhVien.getId() == null) {
        Optional<SinhVien> svCccd = sinhVienService.findByCccd(sinhVien.getCccd());
        if (svCccd.isPresent()) {
            ra.addFlashAttribute("error", "CCCD '" + sinhVien.getCccd() + "' đã tồn tại!");
            return "redirect:/sinhvien/add";
        }
    }

    // Kiểm tra SĐT trùng (chỉ với sinh viên mới)
    if (sinhVien.getId() == null && sinhVien.getSoDienThoai() != null && !sinhVien.getSoDienThoai().isEmpty()) {
        Optional<SinhVien> svSdt = sinhVienService.findBySoDienThoai(sinhVien.getSoDienThoai());
        if (svSdt.isPresent()) {
            ra.addFlashAttribute("error", "Số điện thoại '" + sinhVien.getSoDienThoai() + "' đã tồn tại!");
            return "redirect:/sinhvien/add";
        }
    }

    // Lưu sinh viên
    sinhVienService.save(sinhVien);
    ra.addFlashAttribute("success", "Lưu sinh viên thành công!");
    return "redirect:/sinhvien/list";
}

    // ------------------- FORM SỬA SINH VIÊN (UPDATE UI - Dùng ID) -------------------
        @GetMapping("/edit/{id}")
        public String hienThiSuaSVUI(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
            Optional<SinhVien> svOptional = sinhVienService.findById(id);
            if (svOptional.isPresent()) {
                model.addAttribute("sinhvien", svOptional.get());
                model.addAttribute("title", "Chỉnh Sửa Sinh Viên");
                return "sinhvien/edit"; // đúng template
            }
            ra.addFlashAttribute("error", "Không tìm thấy sinh viên có ID: " + id);
            return "redirect:/sinhvien/list";
        }

@GetMapping("/detail/{id}")
public String detailSinhVien(@PathVariable Long id, Model model) {
    SinhVien sv = sinhVienService.findById(id).orElse(null);
    if (sv == null) {
        model.addAttribute("error", "Sinh viên không tồn tại");
        return "redirect:/sinhvien/list";
    }
    model.addAttribute("sinhvien", sv);
    model.addAttribute("title", "Chi Tiết Sinh Viên");
    return "sinhvien/detail";
}



    // ------------------- XÓA SINH VIÊN (DELETE - Dùng ID) -------------------
@GetMapping("/delete/{id}")
public String xoaSinhVien(@PathVariable("id") Long id,
                          RedirectAttributes ra) {
    if (sinhVienService.findById(id).isPresent()) {
        sinhVienService.delete(id);
        ra.addFlashAttribute("success", "Xóa sinh viên thành công!");
    } else {
        ra.addFlashAttribute("error", "Không tìm thấy sinh viên để xóa.");
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
