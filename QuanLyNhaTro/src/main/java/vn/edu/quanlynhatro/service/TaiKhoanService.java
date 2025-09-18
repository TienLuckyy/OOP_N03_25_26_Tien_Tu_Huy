package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.Role;
import vn.edu.quanlynhatro.model.TaiKhoan;
import vn.edu.quanlynhatro.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class TaiKhoanService implements UserDetailsService {

    @Autowired
    private TaiKhoanRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public TaiKhoan dangKy(String username, String password, Role role) {
        if (repository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username đã tồn tại");
        }
        TaiKhoan tk = new TaiKhoan(username, passwordEncoder.encode(password), role);
        return repository.save(tk);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoan tk = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy username: " + username));

        return new User(
                tk.getUsername(),
                tk.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + tk.getRole().name()))
        );
    }
}
