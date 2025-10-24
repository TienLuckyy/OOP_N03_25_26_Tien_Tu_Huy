package vn.edu.quanlynhatro.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Khi không tìm thấy tài nguyên
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleNotFound(ResourceNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error"; // chuyển đến trang error.html
    }

    // Khi tài nguyên đang được sử dụng
    @ExceptionHandler(ResourceInUseException.class)
    public String handleResourceInUse(ResourceInUseException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    // Các lỗi chung khác
    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Đã xảy ra lỗi: " + ex.getMessage());
        return "error";
    }
}
