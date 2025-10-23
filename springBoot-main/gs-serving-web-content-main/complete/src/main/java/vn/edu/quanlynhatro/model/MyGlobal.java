package vn.edu.quanlynhatro.model;

public class MyGlobal {
    public static int indexError;

    // ✅ Cho phép gọi ở class khác
    public MyGlobal() { }

    // ✅ Cho phép gọi bên ngoài
    public void workOnArray(double[] myArray, int otherInfo) {
        int i = 0; // mô phỏng tính toán chỉ số i

        // Kiểm tra giới hạn mảng
        if (i >= 0 && i < myArray.length) {
            myArray[i] = 3.14159;
        } else {
            MyGlobal.indexError = -1; // báo lỗi toàn cục
        }
    }
}

