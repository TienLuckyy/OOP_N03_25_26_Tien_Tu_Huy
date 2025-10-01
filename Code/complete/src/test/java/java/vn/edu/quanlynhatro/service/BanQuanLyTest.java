package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.BanQuanLy;

import java.util.ArrayList;
import java.util.Date;

public class BanQuanLyTest {

    public static void main(String[] args) {
        // Tạo các đối tượng BanQuanLy
        BanQuanLy bql1 = new BanQuanLy("123456789", "0987654321", "Nam", "Nguyen Van A", new Date(), "Hà Nội");
        BanQuanLy bql2 = new BanQuanLy("987654321", "0912345678", "Nữ", "Tran Thi B", new Date(), "TP.HCM");

        // Tạo danh sách quản lý
        ArrayList<BanQuanLy> listBQL = new ArrayList<>();
        listBQL.add(bql1);
        listBQL.add(bql2);

        // In ra thông tin
        for (BanQuanLy bql : listBQL) {
            System.out.println("Họ tên: " + bql.getHoTen());
            System.out.println("CCCD: " + bql.getCccd());
            System.out.println("SĐT: " + bql.getSoDienThoai());
            System.out.println("-----------");
        }
    }
    
}
