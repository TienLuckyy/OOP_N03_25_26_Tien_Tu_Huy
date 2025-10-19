package vn.edu.quanlynhatro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.quanlynhatro.model.BanQuanLy;
import vn.edu.quanlynhatro.service.BanQuanLyService;

import java.util.List;

@Controller
@RequestMapping("/banquanly")
public class BanQuanLyController {

    @Autowired
    private BanQuanLyService banQuanLyService;

    // 🟢 Danh sách
    @GetMapping("/list")
    public String listBanQuanLy(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<BanQuanLy> list;
        if (keyword != null && !keyword.trim().isEmpty()) {
            list = banQuanLyService.searchByHoTen(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            list = banQuanLyService.getAll();
        }
        model.addAttribute("list", list);
        model.addAttribute("title", "Danh Sách Ban Quản Lý");
        return "banquanly/list";
    }

    // 🟢 Form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("banQuanLy", new BanQuanLy());
        model.addAttribute("title", "Thêm Ban Quản Lý");
        return "banquanly/add";
    }

    // 🟢 Lưu thêm mới
    @PostMapping("/add")
    public String addBanQuanLy(@ModelAttribute("banQuanLy") BanQuanLy bql) {
        banQuanLyService.save(bql);
        return "redirect:/banquanly/list?success=true";
    }

    // 🟢 Xem chi tiết
    @GetMapping("/detail/{id}")
    public String detailBanQuanLy(@PathVariable("id") Long id, Model model) {
        BanQuanLy bql = banQuanLyService.getById(id);
        model.addAttribute("banQuanLy", bql);
        model.addAttribute("title", "Chi Tiết Ban Quản Lý");
        return "banquanly/detail";
    }

    // 🟢 Sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        BanQuanLy bql = banQuanLyService.getById(id);
        model.addAttribute("banQuanLy", bql);
        model.addAttribute("title", "Sửa Thông Tin Ban Quản Lý");
        return "banquanly/edit";
    }

    @PostMapping("/update")
    public String updateBanQuanLy(@ModelAttribute("banQuanLy") BanQuanLy bql) {
        banQuanLyService.save(bql);
        return "redirect:/banquanly/list?updated=true";
    }

    // 🟢 Xóa
    @GetMapping("/delete/{id}")
    public String deleteBanQuanLy(@PathVariable("id") Long id) {
        banQuanLyService.deleteById(id);
        return "redirect:/banquanly/list?deleted=true";
    }
}
