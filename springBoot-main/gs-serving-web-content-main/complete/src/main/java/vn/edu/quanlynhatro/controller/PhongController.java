package vn.edu.quanlynhatro.controller;

import vn.edu.quanlynhatro.model.Phong;
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

    private final PhongService phongService;
    private final SinhVienRepository sinhVienRepository;

    // Constructor Injection (Better OOP)
    @Autowired
    public PhongController(PhongService phongService, SinhVienRepository sinhVienRepository) {
        this.phongService = phongService;
        this.sinhVienRepository = sinhVienRepository;
    }

    // === CRUD OPERATIONS ===

    // 📖 Danh sách tất cả phòng
    @GetMapping("/list")
    public String getAllRooms(Model model) {
        List<Phong> rooms = phongService.getAllPhong();
        model.addAttribute("phongs", rooms);
        model.addAttribute("title", "Danh Sách Tất Cả Phòng");
        return "phong/list";
    }

    // ➕ Hiển thị form thêm phòng
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("phong", new Phong());
        model.addAttribute("title", "Thêm Phòng Mới");
        return "phong/add";
    }

    // 💾 Lưu phòng mới
    @PostMapping("/save")
    public String saveRoom(@ModelAttribute("phong") Phong phong, Model model) {
        boolean success = phongService.createPhong(phong);
        
        if (!success) {
            model.addAttribute("errorMessage",
                    "❌ Phòng " + phong.getSoPhong() + " tại tòa " + phong.getToa() + " đã tồn tại!");
            model.addAttribute("phong", phong);
            model.addAttribute("title", "Thêm Phòng Mới");
            return "phong/add";
        }
        return "redirect:/phong/list?success=true";
    }

    // ✏️ Hiển thị form sửa phòng
    @GetMapping("/edit/{soPhong}/{toa}")
    public String showEditForm(@PathVariable String soPhong,
                              @PathVariable String toa,
                              Model model) {
        Optional<Phong> phong = phongService.getPhongById(soPhong, toa);
        if (phong.isPresent()) {
            model.addAttribute("phong", phong.get());
            model.addAttribute("title", "Chỉnh Sửa Phòng");
            return "phong/edit";
        }
        return "redirect:/phong/list?notfound=true";
    }

    // 🔁 Cập nhật phòng
    @PostMapping("/update")
    public String updateRoom(@ModelAttribute Phong phong) {
        phongService.updatePhong(phong);
        return "redirect:/phong/list?updated=true";
    }

    // ❌ Xóa phòng
    @GetMapping("/delete/{soPhong}/{toa}")
    public String deleteRoom(@PathVariable String soPhong,
                            @PathVariable String toa) {
        phongService.deletePhong(soPhong, toa);
        return "redirect:/phong/list?deleted=true";
    }

    // === BUSINESS OPERATIONS ===

    // 🔍 Tìm kiếm phòng
    @GetMapping("/search")
    public String searchRoom(@RequestParam String soPhong,
                            @RequestParam String toa,
                            Model model) {
        Optional<Phong> phong = phongService.getPhongById(soPhong, toa);
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
    public String getAvailableRooms(Model model) {
        List<Phong> rooms = phongService.getEmptyRooms();
        model.addAttribute("phongs", rooms);
        model.addAttribute("title", "Danh Sách Phòng Còn Trống");
        return "phong/list";
    }

    // 🟥 Phòng đang sử dụng
    @GetMapping("/occupied")
    public String getOccupiedRooms(Model model) {
        List<Phong> rooms = phongService.getPhongTheoTrangThai(true);
        model.addAttribute("phongs", rooms);
        model.addAttribute("title", "Danh Sách Phòng Đang Sử Dụng");
        return "phong/list";
    }

    // 🏢 Phòng theo tòa
    @GetMapping("/toa")
    public String getRoomsByBuilding(@RequestParam String toa, Model model) {
        List<Phong> rooms = phongService.getPhongTheoToa(toa);
        model.addAttribute("phongs", rooms);
        model.addAttribute("title", "Danh Sách Phòng Tòa " + toa);
        return "phong/list";
    }

    // 👥 Chi tiết phòng
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

    // 📝 Gán sinh viên vào phòng
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

    // 🗑️ Xóa sinh viên khỏi phòng
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