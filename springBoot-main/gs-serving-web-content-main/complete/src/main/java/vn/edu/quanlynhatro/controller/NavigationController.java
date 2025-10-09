package vn.edu.quanlynhatro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    // Chỉ giữ lại các Endpoint công khai không liên quan đến Auth
    // Các Endpoint '/', '/login', '/logout' đã được chuyển hoàn toàn sang AuthController.java

    @GetMapping("/phong")
    public String showPublicRooms() {
        // Trả về templates/student/phong.html
        return "student/phong";
    }
}