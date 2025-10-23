package vn.edu.quanlynhatro.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Xử lý lỗi chung cho toàn bộ controller
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, RedirectAttributes redirectAttributes) {
        String message;

        if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
            message = ex.getMessage();
        } else {
            message = "Lỗi không xác định. Vui lòng thử lại sau!";
        }

        redirectAttributes.addFlashAttribute("error", message);
        return "redirect:/error-page"; // Hoặc redirect về trang list
    }
}
