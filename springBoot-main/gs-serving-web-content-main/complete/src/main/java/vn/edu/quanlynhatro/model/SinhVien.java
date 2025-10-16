package vn.edu.quanlynhatro.model; // Đổi package cho phù hợp

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.quanlynhatro.repository.SinhVienRepository;

@Controller
public class SinhVien {

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @GetMapping("/sinh-vien")
    public String listSinhVien(Model model) {
        // READ: Lấy tất cả sinh viên từ CSDL
        model.addAttribute("listSinhVien", sinhVienRepository.findAll());
        
        // Trả về tên file Thymeleaf (ví dụ: 'sinhvien_list.html')
        return "sinhvien_list"; 
    }
    
    // TODO: Thêm các method @GetMapping("/sinh-vien/new") để hiển thị form tạo mới
    // TODO: Thêm các method @PostMapping("/sinh-vien") để lưu sinh viên
}