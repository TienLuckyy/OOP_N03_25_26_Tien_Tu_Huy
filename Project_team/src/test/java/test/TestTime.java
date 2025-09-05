package test;

import model.Time;

public class TestTime {
    public static void main(String[] args) {
        // Khởi tạo 2 đối tượng Time
        Time t1 = new Time();  // Thời gian mặc định là 00:00:00 AM
        Time t2 = new Time(20, 3, 45);  // Thời gian cụ thể là 20:03:45

        // Sử dụng phương thức set để thay đổi thời gian cho t1
        t1.setHour(7).setMinute(32).setSecond(23);
        
        // In ra kết quả
        System.out.println("Time 1: " + t1);  // Kết quả mong đợi: 7:32:23 AM
        System.out.println("Time 2: " + t2);  // Kết quả mong đợi: 8:03:45 PM
    }
}
