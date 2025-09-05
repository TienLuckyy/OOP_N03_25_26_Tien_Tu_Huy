package util;

public class Recursion {
    // Phương thức đệ qui tính giai thừa
    public long calculateFactorial(int n) {
        // Điều kiện dừng
        if (n <= 1) {
            return 1;
        }
        // Bước đệ quy
        return n * calculateFactorial(n - 1);
    }
}