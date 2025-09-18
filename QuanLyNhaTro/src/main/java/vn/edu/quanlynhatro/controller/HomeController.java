package vn.edu.quanlynhatro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Trả về tên file trong templates (index.html)
        return "index";
    }
}
