package vn.edu.quanlynhatro.exception;

/*
Dùng khi dữ liệu bị trùng lặp — cố gắng tạo một tài nguyên đã tồn tại.  Ví dụ: Thêm phòng với số phòng và tòa đã có trong hệ thống
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException() {
        super();
    }

    public DuplicateResourceException(String message) {
        super(message);
    }

    public DuplicateResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateResourceException(Throwable cause) {
        super(cause);
    }
}