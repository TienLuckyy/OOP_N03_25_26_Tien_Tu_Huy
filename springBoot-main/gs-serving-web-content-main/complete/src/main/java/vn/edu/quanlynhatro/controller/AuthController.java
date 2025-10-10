package vn.edu.quanlynhatro.controller;

import vn.edu.quanlynhatro.model.TaiKhoan;
import vn.edu.quanlynhatro.service.TaiKhoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final TaiKhoanService taiKhoanService;

    public AuthController(TaiKhoanService taiKhoanService) {
        this.taiKhoanService = taiKhoanService;
    }

    // --- TRANG LOGIN (GET /login) ---
    @GetMapping("/login")
    public String showLoginForm(Model model,
                                @RequestParam(required = false) String error,
                                @RequestParam(required = false) String logout) {
        if (error != null) {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
        }
        if (logout != null) {
            model.addAttribute("message", "Bạn đã đăng xuất thành công!");
        }
        return "login"; // hiển thị trang login.html
    }

    // --- XỬ LÝ LOGIN (POST /login) ---
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        TaiKhoan taiKhoan = taiKhoanService.dangNhap(username, password);

        if (taiKhoan != null) {
            // Lưu thông tin người dùng vào session
            session.setAttribute("currentUser", taiKhoan);
            session.setAttribute("username", taiKhoan.getUsername());
            session.setAttribute("role", taiKhoan.getRole());

            // Chuyển hướng theo vai trò
            String role = taiKhoan.getRole().toUpperCase();
            switch (role) {
                case "ADMIN":
                case "QUANLY":
                case "MANAGER":
                    return "redirect:/phong/list";
                case "SINHVIEN":
                case "STUDENT":
                    return "redirect:/student/dashboard";
                default:
                    return "redirect:/";
            }
        }

        // Sai thông tin đăng nhập
        model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
        return "login";
    }

    // --- TRANG CHỦ (GET /) ---
    @GetMapping("/")
    public String home(HttpSession session) {
        Object currentUser = session.getAttribute("currentUser");
        if (currentUser != null) {
            String role = (String) session.getAttribute("role");
            if (role != null) {
                switch (role.toUpperCase()) {
                    case "ADMIN":
                    case "QUANLY":
                    case "MANAGER":
                        return "redirect:/phong/list";
                    case "SINHVIEN":
                    case "STUDENT":
                        return "redirect:/student/dashboard";
                }
            }
        }
        return "index"; // trang chủ mặc định
    }

    // --- ĐĂNG XUẤT (GET /logout) ---
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }
}
