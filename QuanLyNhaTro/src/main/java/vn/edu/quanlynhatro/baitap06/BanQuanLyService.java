package vn.edu.quanlynhatro.baitap06;

public class BanQuanLyService implements QuanLy {
    @Override
    public void them() {
        System.out.println("Thêm thành viên BQL...");
    }

    @Override
    public void sua() {
        System.out.println("Sửa thông tin BQL...");
    }

    @Override
    public void xoa() {
        System.out.println("Xóa thành viên BQL...");
    }
}
