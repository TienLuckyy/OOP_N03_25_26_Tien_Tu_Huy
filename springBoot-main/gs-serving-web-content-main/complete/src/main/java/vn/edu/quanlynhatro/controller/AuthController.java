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
                                HttpSession session,
                                @RequestParam(required = false) String error,
                                @RequestParam(required = false) String logout) {

        // Nếu đã đăng nhập rồi, không cho quay lại trang login
        if (session.getAttribute("currentUser") != null) {
            return "redirect:/sinhvien/list";
        }

        if (error != null) {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
        }
        if (logout != null) {
            model.addAttribute("message", "Bạn đã đăng xuất thành công!");
        }

        return "login"; // hiển thị login.html
    }

    // --- XỬ LÝ LOGIN (POST /login) ---
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        TaiKhoan taiKhoan = taiKhoanService.dangNhap(username, password);

        if (taiKhoan == null) {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
            return "login";
        }

        // Lưu thông tin đăng nhập
        session.setAttribute("currentUser", taiKhoan);
        session.setAttribute("username", taiKhoan.getUsername());
        session.setAttribute("role", taiKhoan.getRole());

        // Điều hướng theo vai trò
        String role = taiKhoan.getRole().toUpperCase();
        return switch (role) {
            case "ADMIN", "QUANLY", "MANAGER" -> "redirect:/sinhvien/list";
            case "SINHVIEN", "STUDENT" -> "redirect:/sinhvien/list";
            default -> "redirect:/";
        };
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
                    case "SINHVIEN":
                    case "STUDENT":
                        return "redirect:/sinhvien/list";
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
