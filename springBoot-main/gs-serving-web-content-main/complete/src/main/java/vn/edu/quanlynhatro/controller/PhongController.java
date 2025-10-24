package vn.edu.quanlynhatro.controller;

import vn.edu.quanlynhatro.model.Phong;
import vn.edu.quanlynhatro.service.PhongService;
import vn.edu.quanlynhatro.repository.SinhVienRepository;
import vn.edu.quanlynhatro.exception.ResourceInUseException;
import vn.edu.quanlynhatro.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/phong")
public class PhongController {

    private final PhongService phongService;
    private final SinhVienRepository sinhVienRepository;

    @Autowired
    public PhongController(PhongService phongService, SinhVienRepository sinhVienRepository) {
        this.phongService = phongService;
        this.sinhVienRepository = sinhVienRepository;
    }

    // === 📖 DANH SÁCH PHÒNG ===
    @GetMapping("/list")
    public String getAllRooms(Model model) {
        List<Phong> rooms = phongService.getAllPhong();
        model.addAttribute("phongs", rooms);
        model.addAttribute("title", "Danh Sách Phòng");
        return "phong/list";
    }

    // === ➕ THÊM PHÒNG ===
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("phong", new Phong());
        model.addAttribute("title", "Thêm Phòng Mới");
        return "phong/add";
    }

    @PostMapping("/save")
    public String saveRoom(@ModelAttribute("phong") Phong phong, Model model) {
        boolean success = phongService.createPhong(phong);

        if (!success) {
            model.addAttribute("errorMessage",
                    "❌ Phòng " + phong.getSoPhong() + " tại tòa " + phong.getToa() + " đã tồn tại!");
            model.addAttribute("title", "Thêm Phòng Mới");
            return "phong/add";
        }

        return "redirect:/phong/list?success=true";
    }

    // === ✏️ SỬA PHÒNG ===
    @GetMapping("/edit/{soPhong}/{toa}")
    public String showEditForm(@PathVariable String soPhong,
                               @PathVariable String toa,
                               Model model) {
        Optional<Phong> phong = phongService.getPhongById(soPhong, toa);
        if (phong.isEmpty()) {
            return "redirect:/phong/list?notfound=true";
        }

        model.addAttribute("phong", phong.get());
        model.addAttribute("title", "Chỉnh Sửa Phòng");
        return "phong/edit";
    }

    @PostMapping("/update")
    public String updateRoom(@ModelAttribute("phong") Phong phong) {
        phongService.updatePhong(phong);
        return "redirect:/phong/list?updated=true";
    }

    // === ❌ XÓA PHÒNG ===
@GetMapping("/delete/{soPhong}/{toa}")
public String deleteRoom(@PathVariable String soPhong,
                         @PathVariable String toa,
                         RedirectAttributes redirectAttributes) {
    try {
        phongService.deletePhong(soPhong, toa);
        redirectAttributes.addAttribute("deleted", true); // Thêm query param ?deleted=true
    } catch (ResourceInUseException | ResourceNotFoundException e) {
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi không xác định khi xóa phòng.");
    }
    return "redirect:/phong/list";
}



    // === 🔍 TÌM KIẾM PHÒNG ===
    @GetMapping("/search")
    public String searchRoom(@RequestParam String soPhong,
                             @RequestParam String toa,
                             Model model) {
        Optional<Phong> phong = phongService.getPhongById(soPhong, toa);

        if (phong.isPresent()) {
            model.addAttribute("phongs", List.of(phong.get()));
            model.addAttribute("title", "Kết quả tìm kiếm");
        } else {
            model.addAttribute("phongs", List.of());
            model.addAttribute("message", "Không tìm thấy phòng " + soPhong + " tại tòa " + toa);
        }

        return "phong/list";
    }

    // === 🟩 PHÒNG CÒN TRỐNG ===
    @GetMapping("/available")
    public String getAvailableRooms(Model model) {
        List<Phong> rooms = phongService.getPhongTheoTrangThai(false);
        model.addAttribute("phongs", rooms);
        model.addAttribute("title", "Danh Sách Phòng Còn Trống");
        return "phong/list";
    }

    // === 🟥 PHÒNG ĐANG SỬ DỤNG ===
    @GetMapping("/occupied")
    public String getOccupiedRooms(Model model) {
        List<Phong> rooms = phongService.getPhongTheoTrangThai(true);
        model.addAttribute("phongs", rooms);
        model.addAttribute("title", "Danh Sách Phòng Đang Sử Dụng");
        return "phong/list";
    }

    // === 🏢 PHÒNG THEO TÒA ===
    @GetMapping("/toa")
    public String getRoomsByBuilding(@RequestParam String toa, Model model) {
        List<Phong> rooms = phongService.getPhongTheoToa(toa);
        model.addAttribute("phongs", rooms);
        model.addAttribute("title", "Phòng thuộc tòa " + toa);
        return "phong/list";
    }

    // === 👥 CHI TIẾT PHÒNG ===
    @GetMapping("/detail/{soPhong}/{toa}")
    public String getRoomDetail(@PathVariable String soPhong,
                                @PathVariable String toa,
                                Model model) {
        Optional<Phong> phongOpt = phongService.getPhongById(soPhong, toa);
        if (phongOpt.isEmpty()) {
            return "redirect:/phong/list?notfound=true";
        }

        Phong phong = phongOpt.get();
        model.addAttribute("phong", phong);
        model.addAttribute("sinhViens", phong.getSinhViens());
        model.addAttribute("allSinhViens", sinhVienRepository.findByPhongIsNull());
        model.addAttribute("title", "Chi tiết phòng " + soPhong + " - Tòa " + toa);

        return "phong/detail";
    }

    // === 📝 GÁN SINH VIÊN VÀO PHÒNG ===
    @PostMapping("/assignStudent")
    public String assignStudent(@RequestParam Long sinhVienId,
                                @RequestParam String soPhong,
                                @RequestParam String toa) {
        boolean result = phongService.assignStudent(sinhVienId, soPhong, toa);

        if (result) {
            return "redirect:/phong/detail/" + soPhong + "/" + toa + "?assigned=true";
        } else {
            return "redirect:/phong/detail/" + soPhong + "/" + toa + "?error=assign_failed";
        }
    }

    // === 🗑️ XÓA SINH VIÊN KHỎI PHÒNG ===
    @PostMapping("/removeStudent")
    public String removeStudent(@RequestParam Long sinhVienId,
                                @RequestParam String soPhong,
                                @RequestParam String toa) {
        boolean result = phongService.removeStudent(sinhVienId, soPhong, toa);

        if (result) {
            return "redirect:/phong/detail/" + soPhong + "/" + toa + "?removed=true";
        } else {
            return "redirect:/phong/detail/" + soPhong + "/" + toa + "?error=remove_failed";
        }
    }
}
