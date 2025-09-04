
public class Recursion {

    /**
     * Phương thức đệ quy để tính giai thừa của một số nguyên không âm.
     * Giai thừa của n (ký hiệu là n!) là tích của tất cả các số nguyên dương
     * từ 1 đến n.
     * Ví dụ: 5! = 5 * 4 * 3 * 2 * 1 = 120.
     *
     * @param n Số nguyên không âm để tính giai thừa.
     * @return Giai thừa của n.
     */
    public long calculateFactorial(int n) {
        // 1. Điều kiện dừng (Base Case): Nếu n = 0, giai thừa là 1.
        // Đây là điểm dừng của đệ quy, nếu không có nó, hàm sẽ chạy vô hạn.
        if (n == 0) {
            return 1;
        }
        // 2. Bước đệ quy (Recursive Step):
        // Hàm gọi lại chính nó với một giá trị nhỏ hơn (n - 1).
        // Kết quả là n nhân với giai thừa của (n-1).
        else {
            return n * calculateFactorial(n - 1);
        }
    }
}