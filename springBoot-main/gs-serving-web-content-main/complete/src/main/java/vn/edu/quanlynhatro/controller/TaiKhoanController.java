package vn.edu.quanlynhatro.controller;

import vn.edu.quanlynhatro.model.TaiKhoan;
import vn.edu.quanlynhatro.service.TaiKhoanService;
import org.springframework.security.access.prepost.PreAuthorize; // 💡 IMPORT QUAN TRỌNG
import org.springframework.security.core.Authentication; // Cần để lấy thông tin người dùng đang đăng nhập
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/dashboard/users")
public class TaiKhoanController {

    private final TaiKhoanService taiKhoanService;

    public TaiKhoanController(TaiKhoanService taiKhoanService) {
        this.taiKhoanService = taiKhoanService;
    }

    // =================== HIỂN THỊ DANH SÁCH NGƯỜI DÙNG ===================
    // CHỈ CHO PHÉP ADMIN HOẶC QUẢN LÝ
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('QuanLy')")
    @GetMapping
    public String listUsers(@RequestParam(required = false) String editUsername,
                            Model model) {

        // Không cần kiểm tra hasAccess(session, ...) nữa

        model.addAttribute("users", taiKhoanService.getAllTaiKhoan());
        model.addAttribute("roles", List.of("Admin", "QuanLy", "SinhVien"));

        if (editUsername != null) {
            TaiKhoan userToEdit = taiKhoanService.timKiemTheoUsername(editUsername);
            model.addAttribute("editUser", userToEdit);
        }

        return "admin/users";
    }

    // =================== THÊM NGƯỜI DÙNG MỚI ===================
    // CHỈ CHO PHÉP ADMIN HOẶC QUẢN LÝ
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('QuanLy')")
    @PostMapping("/add")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String role,
                          RedirectAttributes redirectAttrs) {

        // Không cần kiểm tra hasAccess(session, ...) nữa
        
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            redirectAttrs.addFlashAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
            return "redirect:/admin/dashboard/users";
        }

        if (taiKhoanService.timKiemTheoUsername(username) != null) {
            redirectAttrs.addFlashAttribute("error", "Tài khoản đã tồn tại!");
            return "redirect:/admin/dashboard/users";
        }

        TaiKhoan tkMoi = new TaiKhoan(username.trim(), password.trim(), role);
        boolean result = taiKhoanService.dangKy(tkMoi);

        if (result) {
            redirectAttrs.addFlashAttribute("success", "Đã thêm tài khoản mới!");
        } else {
            redirectAttrs.addFlashAttribute("error", "Không thể thêm tài khoản!");
        }

        return "redirect:/admin/dashboard/users";
    }

    // =================== XÓA NGƯỜI DÙNG ===================
    // CHỈ CHO PHÉP ADMIN
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/delete/{username}")
    public String deleteUser(@PathVariable String username,
                             Authentication authentication, // Lấy thông tin người dùng đang đăng nhập
                             RedirectAttributes redirectAttrs) {

        // Không cần kiểm tra hasAccess(session, ...) nữa
        
        // Dùng Spring Security để lấy tên người dùng đang đăng nhập
        String currentUser = authentication.getName(); 
        
        if (currentUser != null && currentUser.equals(username)) {
            redirectAttrs.addFlashAttribute("error", "Không thể xóa tài khoản đang đăng nhập!");
            return "redirect:/admin/dashboard/users";
        }

        taiKhoanService.deleteByUsername(username);
        redirectAttrs.addFlashAttribute("success", "Đã xóa tài khoản!");
        return "redirect:/admin/dashboard/users";
    }

    // =================== CẬP NHẬT NGƯỜI DÙNG ===================
    // CHỈ CHO PHÉP ADMIN HOẶC QUẢN LÝ
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('QuanLy')")
    @PostMapping("/edit/{username}")
    public String updateUser(@PathVariable String username,
                             @RequestParam(required = false) String password,
                             @RequestParam String role,
                             RedirectAttributes redirectAttrs) {

        // Không cần kiểm tra hasAccess(session, ...) nữa

        TaiKhoan existingUser = taiKhoanService.timKiemTheoUsername(username);
        if (existingUser == null) {
            redirectAttrs.addFlashAttribute("error", "Không tìm thấy tài khoản cần sửa!");
            return "redirect:/admin/dashboard/users";
        }

        boolean result = taiKhoanService.updateUser(username, password, role);

        if (result) {
            redirectAttrs.addFlashAttribute("success", "Đã cập nhật tài khoản!");
        } else {
            redirectAttrs.addFlashAttribute("error", "Không thể cập nhật tài khoản!");
        }

        return "redirect:/admin/dashboard/users";
    }

    // =================== HIỂN THỊ FORM SỬA ===================
    // CHỈ CHO PHÉP ADMIN HOẶC QUẢN LÝ
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('QuanLy')")
    @GetMapping("/edit")
    public String showEditForm(@RequestParam String username,
                               Model model) {
        
        // Dùng lại phương thức listUsers, truyền username để hiển thị form sửa
        return listUsers(username, model); 
    }

 }