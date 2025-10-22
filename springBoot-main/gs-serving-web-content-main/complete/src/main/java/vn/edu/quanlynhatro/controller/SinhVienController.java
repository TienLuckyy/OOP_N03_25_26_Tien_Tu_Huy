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

    boolean isEdit = sinhVien.getId() != null; // true nếu đang sửa

    // Kiểm tra MSSV trùng
    Optional<SinhVien> svMssv = sinhVienService.findByMssv(sinhVien.getMssv());
    if (svMssv.isPresent() && (!isEdit || !svMssv.get().getId().equals(sinhVien.getId()))) {
        ra.addFlashAttribute("error", "MSSV '" + sinhVien.getMssv() + "' đã tồn tại!");
        ra.addFlashAttribute("sinhvien", sinhVien); // giữ dữ liệu
        return isEdit ? "redirect:/sinhvien/edit/" + sinhVien.getId() : "redirect:/sinhvien/add";
    }

    // Kiểm tra CCCD trùng
    Optional<SinhVien> svCccd = sinhVienService.findByCccd(sinhVien.getCccd());
    if (svCccd.isPresent() && (!isEdit || !svCccd.get().getId().equals(sinhVien.getId()))) {
        ra.addFlashAttribute("error", "CCCD '" + sinhVien.getCccd() + "' đã tồn tại!");
        ra.addFlashAttribute("sinhvien", sinhVien);
        return isEdit ? "redirect:/sinhvien/edit/" + sinhVien.getId() : "redirect:/sinhvien/add";
    }

    // Kiểm tra SĐT trùng
    if (sinhVien.getSoDienThoai() != null && !sinhVien.getSoDienThoai().isEmpty()) {
        Optional<SinhVien> svSdt = sinhVienService.findBySoDienThoai(sinhVien.getSoDienThoai());
        if (svSdt.isPresent() && (!isEdit || !svSdt.get().getId().equals(sinhVien.getId()))) {
            ra.addFlashAttribute("error", "Số điện thoại '" + sinhVien.getSoDienThoai() + "' đã tồn tại!");
            ra.addFlashAttribute("sinhvien", sinhVien);
            return isEdit ? "redirect:/sinhvien/edit/" + sinhVien.getId() : "redirect:/sinhvien/add";
        }
    }

    // Lưu sinh viên nếu không trùng
    sinhVienService.save(sinhVien);
    ra.addFlashAttribute("success", isEdit ? "Cập nhật sinh viên thành công!" : "Thêm sinh viên thành công!");
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
