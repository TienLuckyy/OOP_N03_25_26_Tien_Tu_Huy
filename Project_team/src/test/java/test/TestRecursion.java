package test;

import model.Recursion;

public class TestRecursion {
    public static void main(String[] args) {
        // Kiểm tra phương thức tính giai thừa
        long number = 5;
        long result = Recursion.factorial(number);
        
        System.out.println("Factorial of " + number + " is: " + result);  // Kết quả mong đợi: 120
    }
}
