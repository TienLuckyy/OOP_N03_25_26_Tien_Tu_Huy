package vn.edu.quanlynhatro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/dashboard")
public class AdminController {

    @GetMapping("/users")
    public String getUserManagementPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username == null) {
            return "redirect:/login";
        }
        if (!"Admin".equalsIgnoreCase(role)) {
            return "redirect:/access-denied";
        }

        model.addAttribute("username", username);
        model.addAttribute("role", role);
        
        return "admin/users"; 
    }

    // STUDENT - Quản lý sinh viên
    @GetMapping("/sinhvien")
    public String getStudentManagementPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username == null) {
            return "redirect:/login";
        }
        if (!"Admin".equalsIgnoreCase(role)) {
            return "redirect:/access-denied";
        }

        model.addAttribute("username", username);
        model.addAttribute("role", role);
        
        return "admin/sinhvien"; 
    }

    // PHONG - Quản lý phòng
    @GetMapping("/phong")
    public String getRoomManagementPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username == null) {
            return "redirect:/login";
        }
        if (!"Admin".equalsIgnoreCase(role)) {
            return "redirect:/access-denied";
        }

        model.addAttribute("username", username);
        model.addAttribute("role", role);
        
        return "admin/phong"; 
    }
}