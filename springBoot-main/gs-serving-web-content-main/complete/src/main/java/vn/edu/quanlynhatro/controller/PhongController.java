package vn.edu.quanlynhatro.controller;

import vn.edu.quanlynhatro.model.Phong;
import vn.edu.quanlynhatro.model.SinhVien;
import vn.edu.quanlynhatro.service.PhongService;
import vn.edu.quanlynhatro.repository.SinhVienRepository;
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

    @Autowired
    private SinhVienRepository sinhVienRepository; // THÊM DÒNG NÀY

    // 🏠 Danh sách tất cả phòng
    @GetMapping("/list")
    public String hienThiTatCaPhong(Model model) {
        List<Phong> danhSach = phongService.getAllPhong();
        model.addAttribute("phongs", danhSach);
        model.addAttribute("title", "Danh Sách Tất Cả Phòng");
        return "phong/list";
    }

    // ➕ Hiển thị form thêm
    @GetMapping("/add")
    public String hienThiThemPhongUI(Model model) {
        model.addAttribute("phong", new Phong());
        model.addAttribute("title", "Thêm Phòng Mới");
        return "phong/add";
    }

    // 💾 Lưu phòng mới (có kiểm tra trùng)
    @PostMapping("/save")
    public String xuLyThemPhong(@ModelAttribute("phong") Phong phong, Model model) {
        phong.setTrangThai(phong.getSoNguoiHienTai() > 0);

        boolean themThanhCong = phongService.themPhong(phong);

        if (!themThanhCong) {
            model.addAttribute("errorMessage",
                    "❌ Phòng " + phong.getSoPhong() + " tại tòa " + phong.getToa() + " đã tồn tại!");
            model.addAttribute("phong", phong);
            model.addAttribute("title", "Thêm Phòng Mới");
            return "phong/add"; // ở lại form thêm
        }

        return "redirect:/phong/list?success=true";
    }

    // ✏️ Form sửa phòng
    @GetMapping("/edit/{soPhong}/{toa}")
    public String hienThiSuaPhongUI(@PathVariable("soPhong") String soPhong,
                                    @PathVariable("toa") String toa,
                                    Model model) {
        Optional<Phong> phong = phongService.timKiemTheoSoPhongVaToa(soPhong, toa);
        if (phong.isPresent()) {
            model.addAttribute("phong", phong.get());
            model.addAttribute("title", "Chỉnh Sửa Phòng");
            return "phong/edit";
        } else {
            return "redirect:/phong/list?notfound=true";
        }
    }

    // 🔁 Cập nhật phòng
    @PostMapping("/update")
    public String xuLySuaPhong(@ModelAttribute("phong") Phong phong) {
        phong.setTrangThai(phong.getSoNguoiHienTai() > 0);
        phongService.suaPhong(phong);
        return "redirect:/phong/list?updated=true";
    }

    // ❌ Xóa phòng
    @GetMapping("/delete/{soPhong}/{toa}")
    public String xoaPhong(@PathVariable("soPhong") String soPhong,
                           @PathVariable("toa") String toa) {
        phongService.xoaPhong(soPhong, toa);
        return "redirect:/phong/list?deleted=true";
    }

    // 🔍 Tìm kiếm phòng theo (soPhong, toa)
    @GetMapping("/search")
    public String hienThiTimKiemPhongUI(@RequestParam("soPhong") String soPhong,
                                        @RequestParam("toa") String toa,
                                        Model model) {
        Optional<Phong> phong = phongService.timKiemTheoSoPhongVaToa(soPhong, toa);
        if (phong.isPresent()) {
            model.addAttribute("phongs", List.of(phong.get()));
            model.addAttribute("title", "Kết quả tìm kiếm: " + soPhong + " - " + toa);
        } else {
            model.addAttribute("phongs", List.of());
            model.addAttribute("message", "Không tìm thấy phòng " + soPhong + " tại tòa " + toa);
        }
        return "phong/list";
    }

    // 🟩 Phòng còn trống
    @GetMapping("/available")
    public String hienThiPhongTrong(Model model) {
        List<Phong> danhSach = phongService.timKiemPhongTrong();
        model.addAttribute("phongs", danhSach);
        model.addAttribute("title", "Danh Sách Phòng Còn Trống");
        return "phong/list";
    }

    // 🟥 Phòng đang sử dụng
    @GetMapping("/occupied")
    public String hienThiPhongDangSuDung(Model model) {
        List<Phong> danhSach = phongService.getPhongTheoTrangThai(true);
        model.addAttribute("phongs", danhSach);
        model.addAttribute("title", "Danh Sách Phòng Đang Sử Dụng");
        return "phong/list";
    }

    // 🏢 Phòng theo tòa
    @GetMapping("/toa")
    public String hienThiPhongTheoToa(@RequestParam("toa") String toa, Model model) {
        List<Phong> danhSach = phongService.getPhongTheoToa(toa);
        model.addAttribute("phongs", danhSach);
        model.addAttribute("title", "Danh Sách Phòng Tòa " + toa);
        return "phong/list";
    }

    // 👥 Chi tiết phòng
    @GetMapping("/detail/{soPhong}/{toa}")
    public String xemChiTietPhong(@PathVariable("soPhong") String soPhong,
                                  @PathVariable("toa") String toa,
                                  Model model) {
        Phong phong = phongService.layPhongTheoSoPhongVaToa(soPhong, toa);
        if (phong == null) {
            return "redirect:/phong/list?notfound=true";
        }
        
        // Lấy danh sách sinh viên trong phòng
        model.addAttribute("phong", phong);
        model.addAttribute("sinhViens", phong.getSinhViens());
        
        // QUAN TRỌNG: Lấy danh sách sinh viên CHƯA CÓ PHÒNG
        List<SinhVien> sinhVienChuaCoPhong = sinhVienRepository.findByPhongIsNull();
        model.addAttribute("allSinhViens", sinhVienChuaCoPhong);
        
        model.addAttribute("title", "Chi tiết phòng " + soPhong + " - Tòa " + toa);
        return "phong/detail";
    }

    // 📝 Gán sinh viên vào phòng
    @PostMapping("/assignStudent")
    public String ganSinhVien(@RequestParam Long sinhVienId,
                              @RequestParam String soPhong,
                              @RequestParam String toa) {
        boolean result = phongService.ganSinhVienVaoPhong(sinhVienId, soPhong, toa);
        
        if (result) {
            return "redirect:/phong/detail/" + soPhong + "/" + toa + "?assigned=true";
        } else {
            return "redirect:/phong/detail/" + soPhong + "/" + toa + "?error=assign_failed";
        }
    }
}