package model;

public class Recursion {

    // Phương thức tính giai thừa (factorial) sử dụng đệ quy
    public static long factorial(long number) {
        if (number <= 1) {  // Điều kiện dừng (base case)
            return 1;
        } else {
            return number * factorial(number - 1);  // Đệ quy gọi lại chính nó
        }
    }
}
