package vn.edu.quanlynhatro.controller;

import vn.edu.quanlynhatro.model.BanQuanLy;
import vn.edu.quanlynhatro.model.Phong;
import vn.edu.quanlynhatro.model.SinhVien;
import vn.edu.quanlynhatro.service.BanQuanLyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/banquanly")
public class BanQuanLyController {
    
    @Autowired
    private BanQuanLyService banQuanLyService;
    @GetMapping("/list")
    public String listBanQuanLy(Model model, 
                               @RequestParam(required = false) String search,
                               @RequestParam(required = false) String toa) {
        List<BanQuanLy> banQuanLys;
        
        if (search != null && !search.isEmpty()) {
            banQuanLys = banQuanLyService.searchBanQuanLy(search);
        } else if (toa != null && !toa.isEmpty()) {
            banQuanLys = banQuanLyService.getNhanVienTheoToa(toa);
        } else {
            banQuanLys = banQuanLyService.getAllBanQuanLy();
        }
        
        model.addAttribute("banQuanLys", banQuanLys);
        model.addAttribute("search", search);
        model.addAttribute("toa", toa);
        model.addAttribute("thongKe", banQuanLyService.getThongKeTongQuanText());
        
        return "banquanly/list";
    }
    
    @GetMapping("/detail/{id}")
    public String chiTietBanQuanLy(@PathVariable Long id, Model model) {
        BanQuanLy banQuanLy = banQuanLyService.getBanQuanLyById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy ban quản lý"));
        
        Map<String, Object> thongKe = banQuanLyService.getThongKeNhanVien(id);
        List<Phong> phongQuanLy = banQuanLyService.getPhongQuanLyByNhanVien(id);
        List<SinhVien> sinhVienQuanLy = banQuanLyService.getSinhVienQuanLyByNhanVien(id);
        
        model.addAttribute("banQuanLy", banQuanLy);
        model.addAttribute("thongKe", thongKe);
        model.addAttribute("phongQuanLy", phongQuanLy);
        model.addAttribute("sinhVienQuanLy", sinhVienQuanLy);
        

        model.addAttribute("totalPhong", phongQuanLy.size());
        model.addAttribute("totalSinhVien", sinhVienQuanLy.size());
        return "banquanly/detail";
    }
    
    @GetMapping("/add")
    public String showThemBanQuanLyForm(Model model) {
        model.addAttribute("banQuanLy", new BanQuanLy());
        model.addAttribute("danhSachToa", List.of("Tòa A", "Tòa B", "Tòa C", "Tất cả"));
        return "banquanly/add";
    }
    
    @PostMapping("/add")
    public String themBanQuanLy(@ModelAttribute("banQuanLy") BanQuanLy banQuanLy,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        try {
            banQuanLyService.createBanQuanLy(banQuanLy);
            redirectAttributes.addFlashAttribute("success",
                    "Thêm ban quản lý " + banQuanLy.getHoTen() + " thành công!");
            return "redirect:/banquanly/list";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("danhSachToa", List.of("Tòa A", "Tòa B", "Tòa C", "Tất cả"));
            return "banquanly/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String showSuaBanQuanLyForm(@PathVariable Long id, Model model) {
        BanQuanLy banQuanLy = banQuanLyService.getBanQuanLyById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy ban quản lý"));
        
        model.addAttribute("banQuanLy", banQuanLy);
        model.addAttribute("danhSachToa", List.of("Tòa A", "Tòa B", "Tòa C", "Tất cả"));
        return "banquanly/edit";
    }
    
    @PostMapping("/edit/{id}")
    public String suaBanQuanLy(@PathVariable Long id, 
                              @ModelAttribute BanQuanLy banQuanLy, 
                              RedirectAttributes redirectAttributes) {
        try {
            BanQuanLy updated = banQuanLyService.updateBanQuanLy(id, banQuanLy);
            redirectAttributes.addFlashAttribute("success", 
                "Cập nhật ban quản lý " + updated.getHoTen() + " thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi: " + e.getMessage());
        }
        return "redirect:/banquanly/list";
    }
    
    @PostMapping("/delete/{id}")
    public String xoaBanQuanLy(@PathVariable Long id, 
                              RedirectAttributes redirectAttributes) {
        try {
            BanQuanLy banQuanLy = banQuanLyService.getBanQuanLyById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy ban quản lý"));
            
            banQuanLyService.deleteBanQuanLy(id);
            redirectAttributes.addFlashAttribute("success", 
                "Xóa ban quản lý " + banQuanLy.getHoTen() + " thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi: " + e.getMessage());
        }
        return "redirect:/banquanly/list";
    }
}
