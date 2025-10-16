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

    // Danh sách tất cả phòng
    @GetMapping("/list")
    public String hienThiTatCaPhong(Model model) {
        List<Phong> danhSach = phongService.getAllPhong();
        model.addAttribute("phongs", danhSach);
        model.addAttribute("title", "Danh Sách Tất Cả Phòng");
        return "phong/list";
    }

    // Form thêm phòng
    @GetMapping("/add")
    public String hienThiThemPhongUI(Model model) {
        model.addAttribute("phong", new Phong());
        model.addAttribute("title", "Thêm Phòng Mới");
        return "phong/add";
    }

    // Lưu phòng mới
    @PostMapping("/save")
    public String xuLyThemPhong(@ModelAttribute("phong") Phong phong) {
        phong.setTrangThai(phong.getSoNguoiHienTai() > 0);
        phongService.themPhong(phong);
        return "redirect:/phong/list";
    }

    // Form sửa phòng
    @GetMapping("/edit")
    public String hienThiSuaPhongUI(@RequestParam("soPhong") String soPhong, Model model) {
        Optional<Phong> phong = phongService.timKiemTheoSoPhong(soPhong);
        if (phong.isPresent()) {
            model.addAttribute("phong", phong.get());
            model.addAttribute("title", "Chỉnh Sửa Phòng");
            return "phong/edit";
        }
        model.addAttribute("message", "Không tìm thấy phòng: " + soPhong);
        return "redirect:/phong/list";
    }

    // Cập nhật phòng
    @PostMapping("/update")
    public String xuLySuaPhong(@ModelAttribute("phong") Phong phong) {
        phong.setTrangThai(phong.getSoNguoiHienTai() > 0);
        phongService.suaPhong(phong);
        return "redirect:/phong/list";
    }

    // Xóa phòng
    @GetMapping("/delete/{soPhong}")
    public String xoaPhong(@PathVariable("soPhong") String soPhong) {
        phongService.xoaPhong(soPhong);
        return "redirect:/phong/list";
    }

    // Tìm kiếm phòng theo số phòng
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

    // Phòng còn trống
    @GetMapping("/available")
    public String hienThiPhongTrong(Model model) {
        List<Phong> danhSach = phongService.timKiemPhongTrong();
        model.addAttribute("phongs", danhSach);
        model.addAttribute("title", "Danh Sách Phòng Còn Trống");
        return "phong/list";
    }

    // Phòng đang sử dụng
    @GetMapping("/occupied")
    public String hienThiPhongDangSuDung(Model model) {
        List<Phong> danhSach = phongService.getPhongTheoTrangThai(true);
        model.addAttribute("phongs", danhSach);
        model.addAttribute("title", "Danh Sách Phòng Đang Sử Dụng");
        return "phong/list";
    }

    // Tìm phòng theo tòa
    @GetMapping("/toa")
    public String hienThiPhongTheoToa(@RequestParam("toa") String toa, Model model) {
        List<Phong> danhSach = phongService.getPhongTheoToa(toa);
        model.addAttribute("phongs", danhSach);
        model.addAttribute("title", "Danh Sách Phòng Tòa " + toa);
        return "phong/list";
    }
}
