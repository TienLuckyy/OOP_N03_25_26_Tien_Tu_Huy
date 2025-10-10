package vn.edu.quanlynhatro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username == null) return "redirect:/login";
        if (!"Admin".equalsIgnoreCase(role) && !"QuanLy".equalsIgnoreCase(role))
            return "redirect:/access-denied";

        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "admin/dashboard";
    }

    @GetMapping("/manager/dashboard")
    public String managerDashboard(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username == null) return "redirect:/login";
        if (!"QuanLy".equalsIgnoreCase(role))
            return "redirect:/access-denied";

        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "manager/dashboard";
    }

    @GetMapping("/student/dashboard")
    public String studentDashboard(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username == null) return "redirect:/login";
        if (!"SinhVien".equalsIgnoreCase(role))
            return "redirect:/access-denied";

        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "student/dashboard";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}
