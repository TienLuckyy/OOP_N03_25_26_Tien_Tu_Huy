package vn.edu.quanlynhatro.exception;

/**
 * Ngoại lệ này được ném ra khi tài nguyên đang được sử dụng và không thể xóa
 * Ví dụ: Phòng đang có sinh viên ở, không thể xóa
 */
public class ResourceInUseException extends RuntimeException {

    public ResourceInUseException(String message) {
        super(message);
    }
}
