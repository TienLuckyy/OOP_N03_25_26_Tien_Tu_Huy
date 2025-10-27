package vn.edu.quanlynhatro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * ✅ GlobalExceptionHandler
 * Lớp này giúp xử lý lỗi chung cho toàn bộ project.
 * Khi bất kỳ Controller hoặc Service nào ném ra Exception,
 * lớp này sẽ bắt lại và trả về giao diện thông báo lỗi thân thiện."error.html"
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 🔹 Xử lý lỗi "Không tìm thấy tài nguyên"
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleResourceNotFound(ResourceNotFoundException ex) {
        ModelAndView mav = new ModelAndView("error"); // error.html
        mav.addObject("errorTitle", "Không tìm thấy dữ liệu");
        mav.addObject("errorMessage", ex.getMessage());
        mav.setStatus(HttpStatus.NOT_FOUND);
        return mav;
    }

    /**
     * 🔹 Xử lý lỗi "Tài nguyên đang được sử dụng" (không thể xóa)
     */
    @ExceptionHandler(ResourceInUseException.class)
    public ModelAndView handleResourceInUse(ResourceInUseException ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorTitle", "Dữ liệu đang được sử dụng");
        mav.addObject("errorMessage", ex.getMessage());
        mav.setStatus(HttpStatus.CONFLICT);
        return mav;
    }

    /**
     * 🔹 Xử lý tất cả các lỗi khác (RuntimeException, NullPointerException, ...)
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralException(Exception ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorTitle", "Đã xảy ra lỗi hệ thống");
        mav.addObject("errorMessage", ex.getMessage());
        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return mav;
    }
}
