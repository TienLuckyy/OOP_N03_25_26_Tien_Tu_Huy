package vn.edu.quanlynhatro.service;

// ✅ import class MyGlobal từ package model
import vn.edu.quanlynhatro.model.MyGlobal;

public class TestMyGlobal {
    public static void main(String[] args) {
        // Tạo đối tượng MyGlobal
        MyGlobal myGlobal = new MyGlobal();

        // Tạo mảng để test
        double[] arr = {1.2, 3.4, 5.6};

        // Gọi phương thức làm việc
        myGlobal.workOnArray(arr, 3);

        // In kết quả kiểm tra
        System.out.println("Index error = " + MyGlobal.indexError);
    }
}
