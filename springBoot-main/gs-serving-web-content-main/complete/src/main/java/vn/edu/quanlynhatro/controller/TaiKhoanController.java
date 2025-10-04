package vn.edu.quanlynhatro.controller;

import vn.edu.quanlynhatro.model.TaiKhoan;
import vn.edu.quanlynhatro.service.TaiKhoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/dashboard/users")
public class TaiKhoanController {

    private final TaiKhoanService taiKhoanService;
    
    // Sử dụng constructor injection
    public TaiKhoanController(TaiKhoanService taiKhoanService) {
        this.taiKhoanService = taiKhoanService;
    }

    // Hiển thị danh sách tài khoản + form thêm/sửa
    @GetMapping
    public String listUsers(@RequestParam(required = false) String editUsername, 
                           HttpSession session,
                           Model model) {
        
        // Kiểm tra đăng nhập và quyền
        String role = (String) session.getAttribute("role");
        if (role == null || (!"Admin".equals(role) && !"QuanLy".equals(role))) {
            return "redirect:/access-denied";
        }
        
        model.addAttribute("users", taiKhoanService.getAllTaiKhoan());

        if (editUsername != null) {
            TaiKhoan userToEdit = taiKhoanService.timKiemTheoUsername(editUsername);
            model.addAttribute("editUser", userToEdit);
        }

        return "admin/dashboard/users";
    }

    // Xử lý thêm tài khoản
    @PostMapping("/add")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String role,
                          HttpSession session) {
        
        // Kiểm tra quyền
        String currentRole = (String) session.getAttribute("role");
        if (currentRole == null || (!"Admin".equals(currentRole) && !"QuanLy".equals(currentRole))) {
            return "redirect:/access-denied";
        }
        
        TaiKhoan tkMoi = new TaiKhoan(username, password, role);
        taiKhoanService.dangKy(tkMoi);
        return "redirect:/admin/dashboard/users";
    }

    // Xử lý xóa tài khoản
    @PostMapping("/delete/{username}")
    public String deleteUser(@PathVariable String username, HttpSession session) {
        
        // Kiểm tra quyền - chỉ Admin mới được xóa
        String role = (String) session.getAttribute("role");
        if (role == null || !"Admin".equals(role)) {
            return "redirect:/access-denied";
        }
        
        // Không cho xóa chính mình
        String currentUser = (String) session.getAttribute("username");
        if (currentUser != null && currentUser.equals(username)) {
            return "redirect:/admin/dashboard/users?error=Không thể xóa tài khoản đang đăng nhập";
        }
        
        taiKhoanService.deleteByUsername(username);
        return "redirect:/admin/dashboard/users";
    }

    // Xử lý cập nhật tài khoản (sửa)
    @PostMapping("/edit/{username}")
    public String updateUser(@PathVariable String username,
                            @RequestParam(required = false) String password,
                            @RequestParam String role,
                            HttpSession session) {
        
        // Kiểm tra quyền
        String currentRole = (String) session.getAttribute("role");
        if (currentRole == null || (!"Admin".equals(currentRole) && !"QuanLy".equals(currentRole))) {
            return "redirect:/access-denied";
        }
        
        taiKhoanService.updateUser(username, password, role);
        return "redirect:/admin/dashboard/users";
    }

    // Chuyển sang chế độ sửa (hiển thị form sửa trong cùng trang)
    @GetMapping("/edit")
    public String showEditForm(@RequestParam String username, 
                              HttpSession session,
                              Model model) {
        
        // Kiểm tra quyền
        String role = (String) session.getAttribute("role");
        if (role == null || (!"Admin".equals(role) && !"QuanLy".equals(role))) {
            return "redirect:/access-denied";
        }
        
        return listUsers(username, session, model);
    }
}