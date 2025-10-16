package vn.edu.quanlynhatro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
// KHÔNG CẦN CONSTRUCTOR VÌ KHÔNG CẦN TAIKHOANSERVICE NỮA
public class AuthController { 
    
    // --- TRANG CHỦ (GET /) ---
    // Xử lý đường dẫn gốc và trả về trang index.html
    @GetMapping("/")
    public String home() {
        // Loại bỏ mọi logic kiểm tra session, role, hay đăng nhập
        return "index"; 
    }

    // --- TRANG LOGIN (GET /login) ---
    // Giữ lại để truy cập được trang login.html (nếu bạn muốn xem giao diện)
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; 
    }
    
    // Các method @PostMapping("/login") và @GetMapping("/logout") đã được loại bỏ 
    // vì chúng cần TaiKhoanService và logic quản lý session/security.
}