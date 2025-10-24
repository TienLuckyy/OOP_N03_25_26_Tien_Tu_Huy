package vn.edu.quanlynhatro.exception;

/**
 * Ngoại lệ này được ném ra khi không tìm thấy tài nguyên (ví dụ: Phòng hoặc Sinh viên không tồn tại)
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
