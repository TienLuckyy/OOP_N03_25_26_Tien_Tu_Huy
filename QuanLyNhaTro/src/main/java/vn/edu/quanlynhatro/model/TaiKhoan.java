package vn.edu.quanlynhatro.model;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "tai_khoan")
public class TaiKhoan implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;  // email/login

    @Column(nullable = false)
    private String password;  // mật khẩu đã mã hóa

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;        // ADMIN, MANAGER, STUDENT

    public TaiKhoan() {}

    public TaiKhoan(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = Role.STUDENT;
    }

    public TaiKhoan(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    @Override
    public String toString() {
        return "TaiKhoan{ username='" + username + "', role=" + role + " }";
    }
}
