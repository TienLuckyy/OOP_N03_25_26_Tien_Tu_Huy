package vn.edu.quanlynhatro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuanLyNhaTroApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuanLyNhaTroApplication.class, args);
        System.out.println("=== ỨNG DỤNG ĐÃ KHỞI ĐỘNG ===");
        System.out.println("Truy cập: http://localhost:8081");
    }
}