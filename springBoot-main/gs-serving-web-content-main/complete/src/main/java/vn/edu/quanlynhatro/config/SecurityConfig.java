package vn.edu.quanlynhatro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .anyRequest().permitAll() // Cho phép tất cả truy cập
            )
            .csrf(csrf -> csrf.disable()); // ĐÃ DISABLE CSRF

        return http.build();
    }
}

// package vn.edu.quanlynhatro.config;

// // ... (Các import khác)
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     // (Giả định bạn có bean PasswordEncoder để mã hóa mật khẩu)
//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable()) // Tắt CSRF nếu cần thiết cho môi trường phát triển
//             .authorizeHttpRequests(authorize -> authorize
//                 // 1. Cho phép truy cập công khai (trang đăng nhập, tài nguyên tĩnh)
//                 .requestMatchers("/login", "/static/**", "/css/**", "/js/**").permitAll()

//                 // 2. PHÂN QUYỀN TRUY CẬP THEO ROLE (Đây là phần quan trọng nhất!)
//                 // Chỉ người dùng có Authority 'Admin' mới truy cập được /admin/**
//                 .requestMatchers("/admin/**").hasAuthority("Admin")

//                 // Chỉ người dùng có Authority 'QuanLy' mới truy cập được /manager/**
//                 .requestMatchers("/manager/**").hasAuthority("QuanLy")

//                 // Chỉ người dùng có Authority 'SinhVien' mới truy cập được /student/**
//                 .requestMatchers("/student/**").hasAuthority("SinhVien")

//                 // Yêu cầu xác thực cho tất cả các đường dẫn khác
//                 .anyRequest().authenticated()
//             )
//             .formLogin(form -> form
//                 .loginPage("/login") // Trang đăng nhập được định nghĩa trong controller
//                 .defaultSuccessUrl("/dashboard", true) // Chuyển hướng tới /dashboard sau khi đăng nhập thành công
//                 .permitAll()
//             )
//             .logout(logout -> logout
//                 .logoutUrl("/logout")
//                 .logoutSuccessUrl("/login?logout") // Chuyển về trang đăng nhập sau khi đăng xuất
//                 .permitAll()
//             );

//         return http.build();
//     }
// }