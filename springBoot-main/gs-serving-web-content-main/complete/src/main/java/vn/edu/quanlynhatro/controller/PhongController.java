package vn.edu.quanlynhatro.controller;

import vn.edu.quanlynhatro.model.Phong;
import vn.edu.quanlynhatro.service.PhongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/phong")
public class PhongController {


@Autowired
private PhongService phongService;

// HIỂN THỊ DANH SÁCH PHÒNG
@GetMapping("/list")
public String hienThiTatCaPhong(Model model) {
    List<Phong> danhSach = phongService.getAllPhong();
    model.addAttribute("phongs", danhSach);
    model.addAttribute("title", "Danh Sách Tất Cả Phòng");
    return "phong/list";
}

// FORM THÊM PHÒNG
@GetMapping("/add")
public String hienThiThemPhongUI(Model model) {
    model.addAttribute("phong", new Phong());
    model.addAttribute("title", "Thêm Phòng Mới");
    return "phong/add";
}

// LƯU PHÒNG MỚI
@PostMapping("/save")
public String xuLyThemPhong(@ModelAttribute("phong") Phong phong) {
    phong.setTrangThai(phong.isTrangThai());
    phongService.themPhong(phong);
    return "redirect:/phong/list";
}

// FORM SỬA PHÒNG
@GetMapping("/edit/{soPhong}")
public String hienThiSuaPhongUI(@PathVariable("soPhong") String soPhong, Model model) {
    Optional<Phong> phong = phongService.timKiemTheoSoPhong(soPhong);
    if (phong.isPresent()) {
        model.addAttribute("phong", phong.get());
        model.addAttribute("title", "Chỉnh Sửa Phòng");
        return "phong/edit";
    }
    model.addAttribute("message", "Không tìm thấy phòng: " + soPhong);
    return "redirect:/phong/list";
}

// CẬP NHẬT PHÒNG
@PostMapping("/update")
public String xuLySuaPhong(@ModelAttribute("phong") Phong phong) {
    phongService.suaPhong(phong);
    return "redirect:/phong/list";
}

// XÓA PHÒNG
@GetMapping("/delete/{soPhong}")
public String xoaPhong(@PathVariable("soPhong") String soPhong) {
    phongService.xoaPhong(soPhong);
    return "redirect:/phong/list";
}

// TÌM KIẾM PHÒNG
@GetMapping("/search")
public String hienThiTimKiemPhongUI(@RequestParam("soPhong") String soPhong, Model model) {
    Optional<Phong> phong = phongService.timKiemTheoSoPhong(soPhong);
    if (phong.isPresent()) {
        model.addAttribute("phongs", List.of(phong.get()));
        model.addAttribute("title", "Kết quả tìm kiếm: " + soPhong);
    } else {
        model.addAttribute("phongs", List.of());
        model.addAttribute("message", "Không tìm thấy phòng số " + soPhong);
    }
    return "phong/list";
}

// DANH SÁCH PHÒNG TRỐNG
@GetMapping("/available")
public String hienThiPhongTrong(Model model) {
    List<Phong> danhSach = phongService.timKiemPhongTrong();
    model.addAttribute("phongs", danhSach);
    model.addAttribute("title", "Danh Sách Phòng Còn Trống");
    return "phong/list";
}


}
