package vn.edu.quanlynhatro.exception;

/**
Dùng khi tài nguyên đang được sử dụng, không thể xóa hay chỉnh sửa. 
* Ví dụ: Phòng đang có sinh viên ở, không thể xóa
 */
public class ResourceInUseException extends RuntimeException {

    public ResourceInUseException(String message) {
        super(message);
    }
}
