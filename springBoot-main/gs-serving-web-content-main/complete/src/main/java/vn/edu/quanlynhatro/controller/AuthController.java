package vn.edu.quanlynhatro.controller;

import vn.edu.quanlynhatro.model.TaiKhoan;
import vn.edu.quanlynhatro.service.TaiKhoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final TaiKhoanService taiKhoanService;
    
    // Sử dụng constructor injection thay vì tự tạo instance
    public AuthController(TaiKhoanService taiKhoanService) {
        this.taiKhoanService = taiKhoanService;
    }

    // HIỂN THỊ FORM LOGIN (GET /login)
    @GetMapping("/login")
    public String showLoginForm(@RequestParam(required = false) String error,
                               @RequestParam(required = false) String logout,
                               Model model) {
        System.out.println("=== ĐANG TRUY CẬP TRANG LOGIN ===");
        
        if (error != null) {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
        }
        if (logout != null) {
            model.addAttribute("message", "Bạn đã đăng xuất thành công!");
        }
        
        return "login";
    }

    // XỬ LÝ ĐĂNG NHẬP (POST /login)
    @PostMapping("/login")
    public String login(@RequestParam String username,
                       @RequestParam String password,
                       @RequestParam String role, // Thêm role từ form
                       HttpSession session,
                       Model model) {
        
        System.out.println("=== ĐĂNG NHẬP ===");
        System.out.println("Username: " + username);
        System.out.println("Role selected: " + role);
        
        TaiKhoan taiKhoan = taiKhoanService.dangNhap(username, password);

        if (taiKhoan != null) {
            System.out.println("Đăng nhập thành công - Role: " + taiKhoan.getRole());
            
            // Lưu thông tin đăng nhập vào session
            session.setAttribute("currentUser", taiKhoan);
            session.setAttribute("username", taiKhoan.getUsername());
            session.setAttribute("role", taiKhoan.getRole());
            
            // Chuyển hướng theo vai trò
            switch (taiKhoan.getRole().toUpperCase()) {
                case "ADMIN":
                case "QUANLY":
                case "MANAGER":
                    return "redirect:/admin/sinhvien"; // Giữ mapping này để chuyển hướng quản trị
                case "SINHVIEN":
                case "STUDENT":
                    return "redirect:/student/dashboard";
                default:
                    return "redirect:/";
            }
        } else {
            System.out.println("Đăng nhập thất bại");
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
            return "login";
        }
        model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
        return "login";
    }

    // XỬ LÝ TRANG GỐC (GET /)
    // PHƯƠNG THỨC NÀY ĐÃ ĐƯỢC ĐỔI TÊN ĐỂ TRÁNH XUNG ĐỘT TRƯỚC VÀ ĐƯỢC DÙNG CHO NAVIGATION
    // Nếu bạn muốn AuthController xử lý trang gốc, hãy xóa nó khỏi NavigationController
    @GetMapping("/")
    public String homeRedirect() {
        return "redirect:/admin/sinhvien"; // Chuyển thẳng tới trang quản trị để thao tác
    }


    // XỬ LÝ ĐĂNG XUẤT (GET /logout)
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }
}
