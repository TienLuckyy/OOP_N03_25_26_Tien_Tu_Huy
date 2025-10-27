package vn.edu.quanlynhatro.exception;

/**
 * Dùng khi không tìm thấy dữ liệu mà người dùng yêu cầu.(ví dụ: Phòng hoặc Sinh viên không tồn tại)
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
