// package vn.edu.quanlynhatro.service;

// public class SecurityConfig(demo) {
//     @Bean
// public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//     UserDetails admin = User.builder()
//             .username("admin")
//             .password(passwordEncoder.encode("123456"))
//             .roles("ADMIN")
//             .build();

//     UserDetails manager = User.builder()
//             .username("manager")
//             .password(passwordEncoder.encode("123456"))
//             .roles("MANAGER")
//             .build();

//     UserDetails student = User.builder()
//             .username("student")
//             .password(passwordEncoder.encode("123456"))
//             .roles("STUDENT")
//             .build();

//     return new InMemoryUserDetailsManager(admin, manager, student);
// }

// }
