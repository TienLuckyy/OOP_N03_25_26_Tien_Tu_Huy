package vn.edu.quanlynhatro;

import java.io.Serializable;
import java.util.Date;

/**
 * Lớp trừu tượng GenericObject để làm cha cho các model trong hệ thống.
 * Chứa các thuộc tính cơ bản như id, ngày tạo, ngày cập nhật.
 */
public abstract class GenericObject implements Serializable {

    private String id;         // Mã định danh chung (UUID hoặc String)
    private Date createdAt;    // Ngày tạo
    private Date updatedAt;    // Ngày cập nhật

    public GenericObject() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public GenericObject(String id) {
        this.id = id;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    // Getter & Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Phương thức trừu tượng: các lớp con phải override để mô tả dữ liệu.
     */
    public abstract String toString();
}
